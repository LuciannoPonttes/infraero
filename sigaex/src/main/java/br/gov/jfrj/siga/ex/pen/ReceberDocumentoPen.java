package br.gov.jfrj.siga.ex.pen;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.tasks.Task;
import br.com.caelum.vraptor.tasks.TaskContext;
import br.com.caelum.vraptor.tasks.scheduler.Scheduled;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.gov.infraero.siga.pen.client.NivelAcessoEnum;
import br.gov.infraero.siga.pen.client.StatusPen;
import br.gov.infraero.siga.pen.client.model.*;
import br.gov.infraero.siga.pen.client.util.PenProperties;
import br.gov.jfrj.siga.base.AplicacaoException;
import br.gov.jfrj.siga.cp.CpSituacaoConfiguracao;
import br.gov.jfrj.siga.cp.CpTipoConfiguracao;
import br.gov.jfrj.siga.dp.CpOrgao;
import br.gov.jfrj.siga.dp.DpLotacao;
import br.gov.jfrj.siga.dp.DpPessoa;
import br.gov.jfrj.siga.dp.dao.CpDao;
import br.gov.jfrj.siga.ex.*;
import br.gov.jfrj.siga.ex.bl.Ex;
import br.gov.jfrj.siga.ex.bl.ExConfiguracaoBL;
import br.gov.jfrj.siga.ex.util.FuncoesEL;
import br.gov.jfrj.siga.hibernate.ExDao;
import br.gov.jfrj.siga.persistencia.ExMobilDaoFiltro;
import br.gov.jfrj.siga.vraptor.ExController;
import br.gov.jfrj.siga.vraptor.SigaObjects;
import br.gov.jfrj.siga.vraptor.Transacional;
import br.gov.jfrj.siga.vraptor.builder.BuscaDocumentoBuilder;
import br.gov.jfrj.siga.vraptor.builder.ExMovimentacaoBuilder;
import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;

import javax.activation.DataHandler;
import javax.ejb.*;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ReceberDocumentoPen extends ExController {

    /**
     * @deprecated CDI eyes only
     */
    public ReceberDocumentoPen() {
        super();
    }

    @Inject
    public ReceberDocumentoPen(HttpServletRequest request, HttpServletResponse response, ServletContext context,
                            Result result, SigaObjects so, EntityManager em, Validator validator) {

        super(request, response, context, result, ExDao.getInstance(), so, em);
    }


    /**
     * Tempo de execução do schedule de receber documentos. Em milissegundos
     *
     */
    private static final String TIMER_SCHEDULE_RECEBER_PEN = PenProperties.getValue("pen.schedule.receber_processo");
    private static final String CADASTRANTE_SISTEMA = PenProperties.getValue("pen.cadastrante_sistema");
    private static final String LOTACAO_DESTINATARIO = PenProperties.getValue("pen.lotacao_destinatario");


    protected  ExDao dao() {
        return ExDao.getInstance();
    }

    private static final Logger LOGGER = Logger
            .getLogger(ReceberDocumentoPen.class);

    private IntegracaoPen integracaoPen = new IntegracaoPen();
    private final BigInteger ID_ESTRUTURA = BigInteger.valueOf(1l);
    private final String NUMERO_IDENTIFICACAO = "5304";

    public void listarPendencias() throws Exception {
        ListaDePendencias pendencias = integracaoPen.listarPendencias(null);
        if(pendencias != null && pendencias.getIDT() != null){
            for(ListaDePendencias.IDT idt : pendencias.getIDT()){
                //idt.getStatus().intValue() == StatusPen.
            }
        }
    }

    @Transacional
    @Post("public/app/schedule")
    @Scheduled(fixedRate = 180000)
    public void receberDocumentosPen(){
        FiltroDeConsultaDeTramites filtro = new FiltroDeConsultaDeTramites();
        EstruturaOrganizacional destinatario = new EstruturaOrganizacional();
        destinatario.setIdentificacaoDoRepositorioDeEstruturas(ID_ESTRUTURA);
        destinatario.setNumeroDeIdentificacaoDaEstrutura(NUMERO_IDENTIFICACAO);
        filtro.setDestinatario(destinatario);
        filtro.setSituacaoAtual(BigInteger.valueOf(StatusPen.ARQUIVOS_DIGITAIS_RECEBIDOS_SOLUCAO.getId().longValue()));
        try{
            TramitesEncontrados tramites = integracaoPen.consultarTramites(filtro);
            if(tramites.getTotalDeRegistros() > 0l){
                for(TramitesEncontrados.Tramite tramite : tramites.getTramite()){
                    Long idt = tramite.getIDT();
                    String nre = tramite.getNRE();
                    try{
                        Metadados metadados = integracaoPen.solicitarMetadados(idt);
                        if(validateMetadados(metadados)){
                            //PESQUISA REMETENTE PARA PEGAR O NOME
                            EstruturasEncontradas.Estrutura estruturaRemetente = integracaoPen.consultarEstrutura(tramite.getRemetente().getIdentificacaoDoRepositorioDeEstruturas().longValue(), Long.valueOf(tramite.getRemetente().getNumeroDeIdentificacaoDaEstrutura()));

                            Date dataProcesso =  metadados.getProcesso().getDataHoraDeRegistro() != null ? metadados.getProcesso().getDataHoraDeRegistro().toGregorianCalendar().getTime() : null;

                            Integer nivelSigilo = NivelAcessoEnum.valueOf(metadados.getProcesso().getNivelDeSigilo().intValue()).getIdSiga();

                            //CRIAR DOCUMENTO
                            ExDocumento doc = criarDocumentoSiga(null, CADASTRANTE_SISTEMA, CADASTRANTE_SISTEMA, LOTACAO_DESTINATARIO,
                                    null, "Externo Folha de Rosto", "Externo", "Documento Externo", "Auditoria externa",
                                    null, true, nivelSigilo, null, null,
                                    estruturaRemetente.getNome(), metadados.getProcesso().getProtocolo(), dataProcesso, true);

                            List<String> hashesComponentes = new ArrayList<>();

                            for(DocumentoDoProcesso docProcesso : metadados.getProcesso().getDocumento()){
                                //TODO VERIFICAR QUANDO O PROCESSO TIVER MAIS DE UM COMPONENTE DIGIGAL
                                String hashComponenteDigital = docProcesso.getComponenteDigital().get(0).getHash().getValue();
                                DataHandler data = receberComponente(idt, hashComponenteDigital, tramite.getProtocolo());
                                byte[] conteudo = IOUtils.toByteArray(data.getInputStream());
                                anexarArquivoAuxiliar(doc.getSigla(), doc.getCadastrante(), docProcesso.getComponenteDigital().get(0), conteudo);

                                hashesComponentes.add(hashComponenteDigital);
                            }

                            Collections.sort(hashesComponentes);
                            integracaoPen.enviarReciboTramite(idt, nre, hashesComponentes);

                        }else{
                            //CANCELAR TRAMITE - por quanto nao existe cancelamento automatico
                        }

                    }catch (Exception ex){
                        LOGGER.error(ex.getMessage(), ex);
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);
        }
        jsonSuccess("sucesso");
    }

    //RECEBER COMPONENTE DIGITAL
    private DataHandler receberComponente(long idt, String hash, String protocolo) throws Exception {
        ParametrosParaRecebimentoDeComponenteDigital parametros = new ParametrosParaRecebimentoDeComponenteDigital();
        IdentificacaoDoComponenteDigital identificacaoDoComponenteDigital = new IdentificacaoDoComponenteDigital();
        identificacaoDoComponenteDigital.setHashDoComponenteDigital(hash);
        identificacaoDoComponenteDigital.setIDT(idt);
        identificacaoDoComponenteDigital.setProtocolo(protocolo);
        parametros.setIdentificacaoDoComponenteDigital(identificacaoDoComponenteDigital);
        DataHandler dataHandler = integracaoPen.receberComponenteDigital(parametros);
        return dataHandler;
    }

    private ExMobil buscarMobil(String codigoDocumentoVia) {
        try {
            ExMobil mob = null;
            {
                final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
                filter.setSigla(codigoDocumentoVia);
                mob = (ExMobil) dao().consultarPorSigla(filter);
            }
            return mob;
        } catch (Exception e) {
            throw e;
        }
    }

    //Verificar o criar documento externo do siga
    public ExDocumento criarDocumentoSiga(String sigla, String cadastranteStr, String subscritorStr,
                                          String destinatarioStr, String destinatarioCampoExtraStr,
                                          String descricaoTipoDeDocumento, String nomeForma,
                                          String nomeModelo, String classificacaoStr, String descricaoStr,
                                          Boolean eletronico, Integer idNivelDeAcesso,
                                          LinkedHashMap<String, String> campos, String siglaMobilPai, String obsOrgao, String numDoc, Date dtDocumento, Boolean finalizar) throws Exception {

        try {
            DpPessoa cadastrante = null;
            DpPessoa subscritor = null;
            ExModelo modelo = null;
            ExFormaDocumento forma = null;
            ExTipoDocumento tipoDocumento = null;
            ExClassificacao classificacao = null;
            ExNivelAcesso nivelDeAcesso = null;
            DpLotacao destinatarioLotacao = null;
            DpPessoa destinatarioPessoa = null;
            CpOrgao destinatarioOrgaoExterno = null;

            ExDocumento doc;
            ExMobil mob;

            if(sigla == null || sigla.isEmpty()){
                doc = new ExDocumento();
                mob = new ExMobil();
            }else{
                mob = buscarMobil(sigla);
                doc = mob.getDoc();
                if(doc.isAssinadoDigitalmente()){
                    throw new AplicacaoException("O documento já foi assinado e não pode ser atualizado.");

                }
            }

            if(cadastranteStr == null || cadastranteStr.isEmpty())
                throw new AplicacaoException("A matrícula do cadastrante não foi informada.");

            if(subscritorStr == null || subscritorStr.isEmpty())
                throw new AplicacaoException("A matrícula do subscritor não foi informada.");

            cadastrante = dao().getPessoaFromSigla(cadastranteStr);

            if(cadastrante == null)
                throw new AplicacaoException("Não foi possível encontrar um cadastrante com a matrícula informada.");

            if(cadastrante.isFechada())
                throw new AplicacaoException("O cadastrante não está mais ativo.");

            subscritor = dao().getPessoaFromSigla(subscritorStr);

            if(subscritor == null)
                throw new AplicacaoException("Não foi possível encontrar um subscritor com a matrícula informada.");

            if(subscritor.isFechada())
                throw new AplicacaoException("O subscritor não está mais ativo.");

            if(descricaoTipoDeDocumento == null)
                tipoDocumento = (dao().consultar(ExTipoDocumento.TIPO_DOCUMENTO_INTERNO, ExTipoDocumento.class,
                        false));
            else
                tipoDocumento = dao().consultarExTipoDocumento(descricaoTipoDeDocumento);

            if(tipoDocumento == null)
                throw new AplicacaoException("Não foi possível encontrar o Tipo de Documento. Os Tipos de Documentos aceitos são: 1-Interno Produzido, 2-Interno Importado, 3-Externo");

            if(nomeForma == null)
                throw new AplicacaoException("O Tipo não foi informado.");

            if(nomeModelo == null)
                throw new AplicacaoException("O modelo não foi informado.");

            modelo = dao().consultarExModelo(nomeForma, nomeModelo);

            if(modelo == null)
                throw new AplicacaoException("Não foi possível encontrar um modelo com os dados informados.");
            else
                modelo = modelo.getModeloAtual();

            forma = modelo.getExFormaDocumento();

            if(!forma.podeSerDoTipo(tipoDocumento))
                throw new AplicacaoException("O documento do tipo " + forma.getDescricao() + " não pode ser " + tipoDocumento.getDescricao());

            if((classificacaoStr == null || classificacaoStr.isEmpty()) && !modelo.isClassificacaoAutomatica())
                throw new AplicacaoException("A Classificação não foi informada.");

            if(modelo.isClassificacaoAutomatica())
                classificacao = modelo.getExClassificacao();
            else
                classificacao =  dao().consultarExClassificacao(classificacaoStr);

            if(classificacao == null)
                throw new AplicacaoException("Não foi possível encontrar uma classificação com o código informado.");
            else
                classificacao = classificacao.getClassificacaoAtual();

            if(eletronico == null)
                eletronico = true;

            Long idSit = Ex
                    .getInstance()
                    .getConf()
                    .buscaSituacao(modelo, tipoDocumento,cadastrante, cadastrante.getLotacao(),
                            CpTipoConfiguracao.TIPO_CONFIG_ELETRONICO)
                    .getIdSitConfiguracao();

            if (idSit == ExSituacaoConfiguracao.SITUACAO_OBRIGATORIO) {
                eletronico = true;
            } else if (idSit == ExSituacaoConfiguracao.SITUACAO_PROIBIDO) {
                eletronico = false;
            }

            if(idNivelDeAcesso == null) {

                Date dt = ExDao.getInstance().consultarDataEHoraDoServidor();

                ExConfiguracao config = new ExConfiguracao();
                CpTipoConfiguracao exTpConfig = new CpTipoConfiguracao();
                CpSituacaoConfiguracao exStConfig = new CpSituacaoConfiguracao();
                config.setDpPessoa(cadastrante);
                config.setLotacao(cadastrante.getLotacao());
                config.setExTipoDocumento(tipoDocumento);
                config.setExFormaDocumento(forma);
                config.setExModelo(modelo);
                config.setExClassificacao(classificacao);

                exTpConfig
                        .setIdTpConfiguracao(CpTipoConfiguracao.TIPO_CONFIG_NIVELACESSO);
                config.setCpTipoConfiguracao(exTpConfig);
                exStConfig
                        .setIdSitConfiguracao(CpSituacaoConfiguracao.SITUACAO_DEFAULT);
                config.setCpSituacaoConfiguracao(exStConfig);

                ExConfiguracao exConfig = ((ExConfiguracao) Ex
                        .getInstance()
                        .getConf()
                        .buscaConfiguracao(config,
                                new int[] { ExConfiguracaoBL.NIVEL_ACESSO }, dt));

                if(exConfig != null)
                    nivelDeAcesso = exConfig.getExNivelAcesso();
            } else {
                nivelDeAcesso= dao().consultar(Long.valueOf(idNivelDeAcesso), ExNivelAcesso.class, false);
            }

            if(nivelDeAcesso == null)
                nivelDeAcesso = dao().consultar(6L, ExNivelAcesso.class, false);

            List<ExNivelAcesso> listaNiveis = ExDao.getInstance().listarOrdemNivel();
            ArrayList<ExNivelAcesso> niveisFinal = new ArrayList<ExNivelAcesso>();
            Date dt = ExDao.getInstance().consultarDataEHoraDoServidor();

            ExConfiguracao config = new ExConfiguracao();
            CpTipoConfiguracao exTpConfig = new CpTipoConfiguracao();
            config.setDpPessoa(cadastrante);
            config.setLotacao(cadastrante.getLotacao());
            config.setExTipoDocumento(tipoDocumento);
            config.setExFormaDocumento(forma);
            config.setExModelo(modelo);
            config.setExClassificacao(classificacao);
            exTpConfig
                    .setIdTpConfiguracao(CpTipoConfiguracao.TIPO_CONFIG_NIVEL_ACESSO_MINIMO);
            config.setCpTipoConfiguracao(exTpConfig);
            int nivelMinimo = ((ExConfiguracao) Ex
                    .getInstance()
                    .getConf()
                    .buscaConfiguracao(config,
                            new int[] { ExConfiguracaoBL.NIVEL_ACESSO }, dt))
                    .getExNivelAcesso().getGrauNivelAcesso();
            exTpConfig
                    .setIdTpConfiguracao(CpTipoConfiguracao.TIPO_CONFIG_NIVEL_ACESSO_MAXIMO);
            config.setCpTipoConfiguracao(exTpConfig);
            int nivelMaximo = ((ExConfiguracao) Ex
                    .getInstance()
                    .getConf()
                    .buscaConfiguracao(config,
                            new int[] { ExConfiguracaoBL.NIVEL_ACESSO }, dt))
                    .getExNivelAcesso().getGrauNivelAcesso();

            for (ExNivelAcesso nivelAcesso : listaNiveis) {
                if (nivelAcesso.getGrauNivelAcesso() >= nivelMinimo
                        && nivelAcesso.getGrauNivelAcesso() <= nivelMaximo)
                    niveisFinal.add(nivelAcesso);
            }

            if(niveisFinal != null && !niveisFinal.isEmpty() & !niveisFinal.contains(nivelDeAcesso))
                nivelDeAcesso = niveisFinal.get(0);

            doc.setCadastrante(cadastrante);
            doc.setLotaCadastrante(cadastrante.getLotacao());
            doc.setTitular(subscritor);
            doc.setLotaTitular(subscritor.getLotacao());

            if(destinatarioStr != null) {
                try {
                    destinatarioLotacao = dao().getLotacaoFromSigla(destinatarioStr);

                    if(destinatarioLotacao != null)
                        doc.setLotaDestinatario(destinatarioLotacao);
                } catch (Exception e) {
                }
            }

            if(destinatarioStr != null && destinatarioLotacao == null) {
                try {
                    destinatarioPessoa = dao().getPessoaFromSigla(destinatarioStr);

                    if(destinatarioPessoa != null)
                        doc.setDestinatario(destinatarioPessoa);
                } catch (Exception e) {
                }
            }

            if(destinatarioStr != null && destinatarioLotacao == null && destinatarioPessoa == null) {
                try {
                    destinatarioOrgaoExterno = dao().getOrgaoFromSiglaExata(destinatarioStr);

                    if(destinatarioOrgaoExterno != null) {
                        doc.setOrgaoExternoDestinatario(destinatarioOrgaoExterno);
                        doc.setNmOrgaoExterno(destinatarioCampoExtraStr);
                    }
                } catch (Exception e) {
                }
            }

            if(destinatarioStr != null && destinatarioLotacao == null && destinatarioPessoa == null && destinatarioOrgaoExterno == null) {
                doc.setNmDestinatario(destinatarioStr);
            }

            doc.setSubscritor(subscritor);
            doc.setLotaSubscritor(subscritor.getLotacao());
            doc.setOrgaoUsuario(subscritor.getOrgaoUsuario());
            doc.setExTipoDocumento(tipoDocumento);
            doc.setExFormaDocumento(forma);
            doc.setExModelo(modelo);
            doc.setObsOrgao(obsOrgao);
            doc.setNumExtDoc(numDoc);
            doc.setDtDocOriginal(dtDocumento);


            if(!modelo.isDescricaoAutomatica())
                doc.setDescrDocumento(descricaoStr);

            doc.setExClassificacao(classificacao);
            if(eletronico)
                doc.setFgEletronico("S");
            else
                doc.setFgEletronico("N");

            doc.setExNivelAcesso(nivelDeAcesso);


            mob.setExTipoMobil(dao().consultar(ExTipoMobil.TIPO_MOBIL_GERAL,
                    ExTipoMobil.class, false));
            mob.setNumSequencia(1);
            mob.setExMovimentacaoSet(new TreeSet<ExMovimentacao>());
            mob.setExDocumento(doc);

            if(siglaMobilPai != null && !siglaMobilPai.isEmpty()) {
                final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
                filter.setSigla(siglaMobilPai);
                ExMobil mobPai = (ExMobil) dao().consultarPorSigla(filter);
                if (mobPai != null) {
                    ExDocumento docPai = mobPai.getExDocumento();

                    if(!docPai.isFinalizado())
                        throw new AplicacaoException("Não foi possível criar o documento pois o documento pai (" + docPai.getSigla() + ") ainda não foi finalizado.");


                    doc.setExMobilPai(mobPai);
                }
            }

            doc.setExMobilSet(new TreeSet<ExMobil>());
            doc.getExMobilSet().add(mob);


            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                final String marcacoes[] = { "<!-- INICIO NUMERO -->",
                        "<!-- FIM NUMERO -->", "<!-- INICIO NUMERO",
                        "FIM NUMERO -->", "<!-- INICIO TITULO", "FIM TITULO -->",
                        "<!-- INICIO MIOLO -->", "<!-- FIM MIOLO -->",
                        "<!-- INICIO CORPO -->", "<!-- FIM CORPO -->",
                        "<!-- INICIO CORPO", "FIM CORPO -->",
                        "<!-- INICIO ASSINATURA -->", "<!-- FIM ASSINATURA -->",
                        "<!-- INICIO ABERTURA -->", "<!-- FIM ABERTURA -->",
                        "<!-- INICIO ABERTURA", "FIM ABERTURA -->",
                        "<!-- INICIO FECHO -->", "<!-- FIM FECHO -->" };

                if(campos!= null && campos.size() > 0){
                    for(Map.Entry<String, String> campo : campos.entrySet()){
                        if (baos.size() > 0)
                            baos.write('&');
                        baos.write(campo.getKey().getBytes());
                        baos.write('=');
                        if (campo.getValue() != null) {
                            String parametro = campo.getValue();
                            for (final String m : marcacoes) {
                                if (parametro.contains(m))
                                    parametro = parametro.replaceAll(m, "");
                            }
                            if (!FuncoesEL.contemTagHTML(parametro)) {
                                if (parametro.contains("\"")) {
                                    parametro = parametro.replace("\"", "&quot;");
                                }
                            }

                            baos.write(URLEncoder.encode(parametro, "iso-8859-1")
                                    .getBytes());
                        }
                    }
                    doc.setConteudoTpDoc("application/zip");
                    doc.setConteudoBlobForm(baos.toByteArray());
                }
            }

            String conteudo = null;

            if (conteudo == null)
                conteudo = "";
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                baos.write(conteudo.getBytes());

                doc.setConteudoTpDoc("application/zip");
                doc.setConteudoBlobForm(baos.toByteArray());
            }

            doc = Ex.getInstance()
                    .getBL().gravar(cadastrante, subscritor, subscritor.getLotacao(), doc);

            if(finalizar)
                Ex.getInstance().getBL().finalizar(cadastrante, cadastrante.getLotacao(), doc);

            return doc;
        } catch (Exception e) {
            throw e;
        }
    }

    public void anexarDocumentoSiga(String sigla, ComponenteDigital componenteDigital, byte[] conteudo){

        final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
        filter.setSigla(sigla);

        ExMobil mob = dao().consultarPorSigla(filter);

        final ExMovimentacaoBuilder movimentacaoBuilder = ExMovimentacaoBuilder
                .novaInstancia().setMob(mob)
                .setSubstituicao(false).setSubscritorSel(null)
                .setTitularSel(null).setDtMovString(null)
                .setDescrMov(null).setContentType(componenteDigital.getMimeType().value())
                .setFileName(componenteDigital.getNome());

        final ExMovimentacao mov = movimentacaoBuilder.construir(dao());
        mov.setSubscritor(null);
        mov.setTitular(null);


        Integer numBytes = 0;
        final byte[] baArquivo = conteudo;

        numBytes = baArquivo.length;
        if (numBytes > 10 * 1024 * 1024) {
            throw new AplicacaoException("Não é permitida a anexação de arquivos com mais de 10MB.");
        }
        mov.setConteudoBlobMov2(baArquivo);

        Integer numPaginas = mov.getContarNumeroDePaginas();
        if (mov.getContarNumeroDePaginas() == null
                || mov.getArquivoComStamp() == null) {
            throw new AplicacaoException(
                    MessageFormat
                            .format("O arquivo {0} está corrompido. Favor gera-lo novamente antes de anexar.",
                                    componenteDigital.getNome()));
        }

        if (mob.isVolumeEncerrado()) {
            throw new AplicacaoException(
                    "Não é possível anexar arquivo em volume encerrado.");
        }

        if (!componenteDigital.getMimeType().value().equals("application/pdf")) {
            throw new AplicacaoException(
                    "Somente é permitido anexar arquivo PDF.");
        }

        try {
            final byte[] ab = mov.getNmArqMov().getBytes();
            for (int i = 0; i < ab.length; i++) {
                if (ab[i] == -29) {
                    ab[i] = -61;
                }
            }
            final String sNmArqMov = new String(ab, "utf-8");

            Ex.getInstance()
                    .getBL()
                    .anexarArquivo(null, null, mob,
                            mov.getDtMov(), mov.getSubscritor(), sNmArqMov,
                            mov.getTitular(), mov.getLotaTitular(),
                            mov.getConteudoBlobMov2(), mov.getConteudoTpMov(),
                            movimentacaoBuilder.getDescrMov(), null);
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }

    }

    public void anexarArquivoAuxiliar(String sigla, DpPessoa cadastrante, ComponenteDigital componenteDigital, byte[] conteudo){

        final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
        filter.setSigla(sigla);

        ExMobil mob = dao().consultarPorSigla(filter);

        if (mob != null && !mob.isGeral())
            mob = mob.doc().getMobilGeral();

        final ExMovimentacaoBuilder movimentacaoBuilder = ExMovimentacaoBuilder
                .novaInstancia().setMob(mob).setContentType(componenteDigital.getMimeType().value())
                .setFileName(componenteDigital.getNome());

        final ExMovimentacao mov = movimentacaoBuilder.construir(dao());
        mov.setSubscritor(cadastrante);
        mov.setTitular(cadastrante);

        String fileExtension = componenteDigital.getNome().substring(componenteDigital.getNome().lastIndexOf("."));

        if (fileExtension.equals(".bat") || fileExtension.equals(".exe") || fileExtension.equals(".sh") || fileExtension.equals(".dll") ) {
            throw new AplicacaoException(
                    "Extensão " + fileExtension + " inválida para inclusão do arquivo.");
        }

        Integer numBytes = 0;
        final byte[] baArquivo = conteudo;
        numBytes = baArquivo.length;
        if (numBytes > 10 * 1024 * 1024) {
            throw new AplicacaoException("Não é permitida a anexação de arquivos com mais de 10MB.");
        }
        mov.setConteudoBlobMov2(baArquivo);

        try {
            final byte[] ab = mov.getNmArqMov().getBytes();
            for (int i = 0; i < ab.length; i++) {
                if (ab[i] == -29) {
                    ab[i] = -61;
                }
            }
            final String sNmArqMov = new String(ab, "utf-8");

            Ex.getInstance().getBL()
                    .anexarArquivoAuxiliar(null, null, mob,
                            mov.getDtMov(), mov.getSubscritor(), sNmArqMov,
                            mov.getTitular(), mov.getLotaTitular(),
                            mov.getConteudoBlobMov2(), mov.getConteudoTpMov());
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    //TODO por enquanto nao existe nenhuma regra de validacao ao receber
    public boolean validateMetadados(Metadados metadados){
        if(metadados.getProcesso() != null){
        }
        return true;
    }


    // Enviar o recibo de tramite para o remetente
    //@Transacional
    //@Post("public/app/pen/enviar_recibo_tramite")
    //@Scheduled(fixedRate = 50000)
    public void enviarReciboTramite(){
        //TODO SE A ESTRUTURA HIERARQUICA DA INFRAERO TIVER ESTRUTURAS FILHAS, ESTE METODO TERA QUE SER REAVALIADO
        FiltroDeConsultaDeTramites filtro = new FiltroDeConsultaDeTramites();
        EstruturaOrganizacional destinatario = new EstruturaOrganizacional();
        destinatario.setIdentificacaoDoRepositorioDeEstruturas(ID_ESTRUTURA);
        destinatario.setNumeroDeIdentificacaoDaEstrutura(NUMERO_IDENTIFICACAO);
        filtro.setDestinatario(destinatario);
        filtro.setSituacaoAtual(BigInteger.valueOf(StatusPen.ARQUIVOS_DIGITAIS_RECEBIDOS_DESTINATARIO.getId().longValue()));

        try{
            TramitesEncontrados tramites = integracaoPen.consultarTramites(filtro);
            if(tramites.getTotalDeRegistros() > 0l){
                for(TramitesEncontrados.Tramite tramite : tramites.getTramite()){
                    Long idt = tramite.getIDT();
                    String nre = tramite.getNRE();
                    List<String> hashes = tramite.getComponenteDigitalPendenteDeRecebimento();
                    try{
                        integracaoPen.enviarReciboTramite(idt, nre, hashes);
                    }catch (Exception ex){
                        LOGGER.error(ex.getMessage(), ex);
                    }
                }
            }
        }catch (Exception e){
            LOGGER.error(e.getMessage(), e);

        }
    }

}
