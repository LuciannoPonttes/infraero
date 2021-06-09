package br.gov.jfrj.siga.ex.pen;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Validator;
import br.gov.infraero.siga.pen.client.MotivoRecusaEnum;
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
import br.gov.jfrj.siga.ex.*;
import br.gov.jfrj.siga.ex.bl.Ex;
import br.gov.jfrj.siga.ex.bl.ExConfiguracaoBL;
import br.gov.jfrj.siga.ex.util.FuncoesEL;
import br.gov.jfrj.siga.hibernate.ExDao;
import br.gov.jfrj.siga.parser.PessoaLotacaoParser;
import br.gov.jfrj.siga.persistencia.ExMobilDaoFiltro;
import br.gov.jfrj.siga.vraptor.ExController;
import br.gov.jfrj.siga.vraptor.SigaObjects;
import br.gov.jfrj.siga.vraptor.Transacional;
import br.gov.jfrj.siga.vraptor.builder.ExMovimentacaoBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jboss.logging.Logger;

import javax.activation.DataHandler;
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


    private static final String CADASTRANTE_SISTEMA = PenProperties.getValue("pen.cadastrante_sistema");
    private static final String LOTACAO_DESTINATARIO = PenProperties.getValue("pen.lotacao_destinatario");
    private static final String PROCESSO_ADM_TIPO_DOC = PenProperties.getValue("pen.processoadm.tipodocumento");
    private static final String PROCESSO_ADM_FORMA = PenProperties.getValue("pen.processoadm.forma");
    private static final String PROCESSO_ADM_MODELO = PenProperties.getValue("pen.processoadm.modelo");
    private static final String PROCESSO_ADM_CLASSIF = PenProperties.getValue("pen.processoadm.classificacao");
    private static final String DOC_CAPTURADO_TIPO_DOC = PenProperties.getValue("pen.documentocapturado.tipodocumento");
    private static final String DOC_CAPTURADO_FORMA = PenProperties.getValue("pen.documentocapturado.forma");
    private static final String DOC_CAPTURADO_MODELO = PenProperties.getValue("pen.documentocapturado.modelo");
    private static final String DOC_CAPTURADO_CLASSIF = PenProperties.getValue("pen.documentocapturado.classificacao");



    protected  ExDao dao() {
        return ExDao.getInstance();
    }

    private static final Logger LOGGER = Logger
            .getLogger(ReceberDocumentoPen.class);

    private IntegracaoPen integracaoPen = new IntegracaoPen();
    private final BigInteger ID_ESTRUTURA = BigInteger.valueOf(1l);
    private final String NUMERO_IDENTIFICACAO = "5304";

    @Transacional
    @Post("public/app/pen/receberDocumentos")
    public void listarPendencias() throws Exception {
        FiltroDePendencias filtro = new FiltroDePendencias();
        filtro.setTodasAsPendencias(true);
        ListaDePendencias pendencias = integracaoPen.listarPendencias(filtro);
        if(pendencias != null && pendencias.getIDT() != null){
            for(ListaDePendencias.IDT idt : pendencias.getIDT()){
                TramitesEncontrados.Tramite tramite = getTramitePorIDT(idt.getValue());
                if(idt.getStatus().intValue() == StatusPen.ARQUIVOS_DIGITAIS_RECEBIDOS_SOLUCAO.getId() || idt.getStatus().intValue() == StatusPen.METADADOS_RECEBIDOS_DESTINATARIO.getId() || idt.getStatus().intValue() == StatusPen.ARQUIVOS_DIGITAIS_RECEBIDOS_DESTINATARIO.getId()){
                    solicitarMetadadosCriarDocumento(idt.getValue());
                }
                if(idt.getStatus().intValue() == StatusPen.RECIBO_CONCLUSAO_TRAMITE_RECEB_SOLUCAO.getId()){
                    ExDocumento exDocumento = consultarProcessoPorNRE(tramite.getNRE());
                    ConteudoDoReciboDeTramite reciboTramite = integracaoPen.downloadReciboTramite(idt.getValue());
                    if(exDocumento != null && reciboTramite != null){ //TODO fazer validacao se é processo ou doc para pegar o mob correto. UltimoVolume ou PrimeiraVia
                            String descMov = "Recibo tramite NRE: " + reciboTramite.getRecibo().getNRE() + " - IDT: " + reciboTramite.getRecibo().getIDT();
                            ExMobil mobil = null;
                            if(exDocumento.isProcesso()){
                                mobil = exDocumento.getUltimoVolume();
                            }else{
                                mobil = exDocumento.getPrimeiraVia();
                            }
                            Ex.getInstance().getBL().criarMovimentacaoPEN(exDocumento.getCadastrante(), exDocumento.getLotaCadastrante(), mobil, descMov, ExTipoMovimentacao.TIPO_MOVIMENTACAO_RECIBO_TRAMITE_PEN);
                    }
                }
                if(idt.getStatus().intValue() == StatusPen.AGUARDANDO_CIENCIA.getId()){ //TODO fazer validacao se é processo ou doc para pegar o mob correto. UltimoVolume ou PrimeiraVia
                    informarCienciaRecusa(idt.getValue());
                    ExDocumento exDocumento = consultarProcessoPorNRE(tramite.getNRE());
                    if(exDocumento != null){
                        String descMov = "Tramite recusado - Motivo: " + tramite.getMotivoDaRecusa() + " - Justificativa: " + tramite.getJustificativaDaRecusa();
                        ExMobil mobil = null;
                        if(exDocumento.isProcesso()){
                            mobil = exDocumento.getUltimoVolume();
                        }else{
                            mobil = exDocumento.getPrimeiraVia();
                        }
                        Ex.getInstance().getBL().criarMovimentacaoPEN(exDocumento.getCadastrante(), exDocumento.getLotaCadastrante(), mobil, descMov, ExTipoMovimentacao.TIPO_MOVIMENTACAO_RECUSA_TRAMITE_PEN);
                    }
                }
            }
        }
        jsonSuccess("sucesso");
    }

    private void informarCienciaRecusa(Long idt){
        try {
            integracaoPen.cienciaRecusa(idt);
        } catch (InteroperabilidadeException_Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
        }
    }

    private TramitesEncontrados.Tramite getTramitePorIDT(Long idt){
        FiltroDeConsultaDeTramites filtro = new FiltroDeConsultaDeTramites();
        filtro.setIDT(idt);
        TramitesEncontrados tramites = null;
        try {
            tramites = integracaoPen.consultarTramites(filtro);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage(), ex);
            return null;
        }
        return (tramites.getTramite() != null && !tramites.getTramite().isEmpty()) ? tramites.getTramite().get(0) : null;
    }

    private void solicitarMetadadosCriarDocumento(Long idt) throws Exception {
        TramitesEncontrados.Tramite tramite = getTramitePorIDT(idt);

        String nre = tramite.getNRE();
        try{
            Metadados metadados = integracaoPen.solicitarMetadados(idt);
            MotivoRecusaEnum motivoRecusa = validarMetadados(metadados);
            if(motivoRecusa == null){

                //PESQUISA REMETENTE PARA PEGAR O NOME
                EstruturasEncontradas.Estrutura estruturaRemetente = integracaoPen.consultarEstrutura(tramite.getRemetente().getIdentificacaoDoRepositorioDeEstruturas().longValue(), Long.valueOf(tramite.getRemetente().getNumeroDeIdentificacaoDaEstrutura()));

                ExDocumento exDocProcesso = consultarProcessoPorNRE(nre);
                Date dtServidor = ExDao.getInstance().consultarDataEHoraDoServidor();

                if(exDocProcesso == null){
                    Date dataProcesso =  metadados.getProcesso().getDataHoraDeRegistro() != null ? metadados.getProcesso().getDataHoraDeRegistro().toGregorianCalendar().getTime() : null;
                    Date dataProducao = metadados.getProcesso().getDataHoraDeProducao() != null ? metadados.getProcesso().getDataHoraDeProducao().toGregorianCalendar().getTime() : null;

                    Integer nivelSigilo = NivelAcessoEnum.valueOf(metadados.getProcesso().getNivelDeSigilo().intValue()).getIdSiga();

                    //CRIAR DOCUMENTO
                    LinkedHashMap<String, String> campos = new LinkedHashMap<>();
                    campos.put("tipo_processo", metadados.getProcesso().getProcessoDeNegocio());
                    campos.put("protocolo", metadados.getProcesso().getProtocolo());
                    campos.put("nre", nre);
                    campos.put("descricao_proc", metadados.getProcesso().getDescricao());
                    campos.put("nivel_sigilo", NivelAcessoEnum.valueOf(metadados.getProcesso().getNivelDeSigilo().intValue()).toString());
                    campos.put("nome_produtor", metadados.getProcesso().getProdutor().getNome());
                    campos.put("unidade_produtor", metadados.getProcesso().getProdutor().getUnidade() != null ? metadados.getProcesso().getProdutor().getUnidade().getNome() : "Não Informado");
                    campos.put("data_hora_producao", DateFormatUtils.format(dataProducao, "dd/MM/yyyy HH:mm"));
                    campos.put("data_hora_registro", DateFormatUtils.format(dataProcesso, "dd/MM/yyyy HH:mm"));
                    //NAO EXITE NO PEN campos.put("data_hora_encerramento", null);
                    String interessado = metadados.getProcesso().getInteressado() != null && !metadados.getProcesso().getInteressado().isEmpty() ? metadados.getProcesso().getInteressado().get(0).getNome() : "";
                    campos.put("nome_interessado", interessado);
                    String numDocNRE = nre;
                    exDocProcesso = criarDocumentoSiga(null, CADASTRANTE_SISTEMA, CADASTRANTE_SISTEMA, LOTACAO_DESTINATARIO,
                            null, PROCESSO_ADM_TIPO_DOC, PROCESSO_ADM_FORMA, PROCESSO_ADM_MODELO, PROCESSO_ADM_CLASSIF,
                            null, true, nivelSigilo, campos, null,
                            estruturaRemetente.getNome(), numDocNRE, dataProcesso, true, null);
                }else{
                    //Necessario para que possa ocorrer a juntada de novos documentos à um processo que voltou do PEN
                    ExMobil mobil = buscarMobil(exDocProcesso.getSigla());
                    if(mobil.isGeral()){
                        mobil = mobil.doc().getMobilDefaultParaReceberJuntada();
                    }
                    boolean isEmTransito = mobil.isEmTransito();
                    if(isEmTransito){
                        Ex.getInstance()
                                .getBL()
                                .receber(exDocProcesso.getCadastrante(), exDocProcesso.getLotaTitular(), mobil,
                                        dtServidor);
                    }
                }

                ExMobil mobilProcesso = null;

                boolean isProcesso = exDocProcesso.isProcesso();
                LOGGER.info("É PROCESSO [" +exDocProcesso.getSigla()+ "] ? ["+ isProcesso+ "]");
                if(isProcesso){
                    mobilProcesso = exDocProcesso.getUltimoVolume();
                }else{
                    mobilProcesso = exDocProcesso.getPrimeiraVia();
                }

                List<String> hashesComponentes = new ArrayList<>();

                for(DocumentoDoProcesso docProcesso : metadados.getProcesso().getDocumento()){

                    String hashComponenteDigital = docProcesso.getComponenteDigital().get(0).getHash().getValue();
                    DataHandler data = receberComponente(idt, hashComponenteDigital, tramite.getProtocolo());

                    if(!existeNoProcesso(exDocProcesso, docProcesso)){

                        Date dataProducaoDoc = metadados.getProcesso().getDataHoraDeProducao() != null ? metadados.getProcesso().getDataHoraDeProducao().toGregorianCalendar().getTime() : null;
                        Integer nivelSigiloDoc = NivelAcessoEnum.valueOf(metadados.getProcesso().getNivelDeSigilo().intValue()).getIdSiga();

                        byte[] conteudo = IOUtils.toByteArray(data.getInputStream());
                        //CRIAR DOCUMENTO
                        LinkedHashMap<String, String> camposDocCapturado = new LinkedHashMap<>();
                        camposDocCapturado.put("especie", docProcesso.getEspecie().getNomeNoProdutor());
                        String numDoc = docProcesso.getProtocoloDoDocumentoAnexado() != null ? docProcesso.getProtocoloDoDocumentoAnexado() : null;
                        if(numDoc == null){
                            numDoc = docProcesso.getProdutor() != null ? docProcesso.getProdutor().getNumeroDeIdentificacao() : null;
                        }
                        camposDocCapturado.put("numerodoc", numDoc);
                        Date dataRegistro = docProcesso.getDataHoraDeRegistro() != null ? docProcesso.getDataHoraDeRegistro().toGregorianCalendar().getTime() : null;
                        camposDocCapturado.put("DataOriginal", DateFormatUtils.format(dataRegistro, "dd/MM/yyyy HH:mm"));
                        camposDocCapturado.put("remetente", docProcesso.getProdutor().getNome());
                        camposDocCapturado.put("assunto", docProcesso.getDescricao());
                        //Nao possui camposDocCapturado.put("info_comp", docProcesso.getIdentificacao() != null ? docProcesso.getIdentificacao().getComplemento() : null);
                        ExDocumento docCapturado = criarDocumentoSiga(null, CADASTRANTE_SISTEMA, CADASTRANTE_SISTEMA, LOTACAO_DESTINATARIO,
                                null, DOC_CAPTURADO_TIPO_DOC, DOC_CAPTURADO_FORMA, DOC_CAPTURADO_MODELO, DOC_CAPTURADO_CLASSIF,
                                null, true, nivelSigiloDoc, camposDocCapturado, mobilProcesso.getSigla(),
                                estruturaRemetente.getNome(), null, dataProducaoDoc, true, conteudo);

                        Boolean juntado = juntar(docCapturado.getPrimeiraVia().getSigla(), mobilProcesso.getSigla(), docCapturado.getLotaDestinatario().getSiglaDePessoaEOuLotacao(), docCapturado.getLotaCadastrante().getSiglaDePessoaEOuLotacao());

                        LOGGER.info("Documento [" +docCapturado.getSigla()+ "] juntado? ["+ juntado+ "]");
                    }

                    hashesComponentes.add(hashComponenteDigital);
                }

                String descMov = "Processo Recibo PEN - Protocolo: " + metadados.getProcesso().getProtocolo() + " - NRE: " + nre + " - IDT: " + idt;
                Ex.getInstance().getBL().receberPEN(exDocProcesso.getCadastrante(), exDocProcesso.getLotaCadastrante(), mobilProcesso, dtServidor, descMov);

                /*Ex.getInstance()
                        .getBL().transferirAutomatico(exDocProcesso.getCadastrante(), exDocProcesso.getLotaCadastrante(), null, exDocProcesso.getLotaDestinatario(), exDocProcesso.getUltimoVolume());*/

                Collections.sort(hashesComponentes);
                integracaoPen.enviarReciboTramite(idt, nre, hashesComponentes);

            }else{
                //RECUSAR TRAMITE
                integracaoPen.recusarTramite(idt, motivoRecusa.getCodigo(), motivoRecusa.getDescricao());

            }

        }catch (Exception ex){
            LOGGER.error(ex.getMessage(), ex);
        }
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
                                          LinkedHashMap<String, String> campos, String siglaMobilPai, String obsOrgao, String numDoc, Date dtDocumento, Boolean finalizar, byte[] arquivo) throws Exception {

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

            // Insere PDF de documento capturado
            //
            if (arquivo != null) {

                Integer numBytes = null;
                try {

                    numBytes = arquivo.length;
                    if (numBytes > 10 * 1024 * 1024) {
                        throw new AplicacaoException(
                                "Não é permitida a anexação de arquivos com mais de 10MB.");
                    }
                    doc.setConteudoBlobPdf(arquivo);
                    doc.setConteudoBlobHtml(null);
                } catch (IOException e) {
                    throw new AplicacaoException("Falha ao manipular aquivo",
                            1, e);
                }

                Integer numPaginas = doc.getContarNumeroDePaginas();
                if (numPaginas == null || doc.getArquivoComStamp() == null) {
                    throw new AplicacaoException("O arquivo está corrompido.");
                }


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
                                    parametro = parametro.replace("\"", "");
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

            doc = Ex.getInstance()
                    .getBL().gravar(cadastrante, subscritor, subscritor.getLotacao(), doc);

            if(finalizar)
                Ex.getInstance().getBL().finalizar(cadastrante, cadastrante.getLotacao(), doc);


            return doc;
        } catch (Exception e) {
            throw e;
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

    public MotivoRecusaEnum validarMetadados(Metadados metadados){
        if(metadados.getProcesso() != null){
            for(DocumentoDoProcesso documento : metadados.getProcesso().getDocumento()){
                if(documento.getComponenteDigital() == null || documento.getComponenteDigital().isEmpty()){
                    return MotivoRecusaEnum.FALTA_COMPONENTE;
                }
                for(ComponenteDigital componenteDigital : documento.getComponenteDigital()){
                    if(!validarMimeTypes(componenteDigital.getMimeType().value())){
                        return MotivoRecusaEnum.FORMATO_NAO_SUPORTADO;
                    }

                    if(componenteDigital.getTamanhoEmBytes() < 1){
                        return MotivoRecusaEnum.COMPONENTE_CORROMPIDO;
                    }

                    if(!validarTamanhoArquivo(componenteDigital.getTamanhoEmBytes())){
                        return MotivoRecusaEnum.OUTRO;
                    }
                }
            }
        }
        return null;
    }

    private boolean validarTamanhoArquivo(long tamanho){
        Integer fileMaxSize = Integer.valueOf(PenProperties.getValue("pen.file.maxsize"));
        if(tamanho > fileMaxSize){
            return false;
        }else{
            return true;
        }
    }

    private boolean validarMimeTypes(String mimeType){
        String mimeTypes = PenProperties.getValue("pen.file.accepted_mimetypes");
        String[] mimeTypesArray = mimeTypes.split(",");
        for(String m : mimeTypesArray){
            if(m.equalsIgnoreCase(mimeType)){
                return true;
            }
        }
        return false;
    }

    private Boolean juntar(String codigoDocumentoViaFilho,
                          String codigoDocumentoViaPai, String siglaDestino,
                          String siglaCadastrante) throws Exception {
        try {
            ExMobil mobFilho = buscarMobil(codigoDocumentoViaFilho);
            ExMobil mobPai = buscarMobil(codigoDocumentoViaPai);

            PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(
                    siglaCadastrante);
            PessoaLotacaoParser destinoParser = new PessoaLotacaoParser(
                    siglaDestino);

            LOGGER.info("######### MOBIL PAI EM TRANSITO ##### "  + mobPai.isEmTransito());

            Ex.getInstance()
                    .getBL()
                    .juntarDocumento(cadastranteParser.getPessoa(),
                            cadastranteParser.getPessoa(),
                            cadastranteParser.getLotacao(), null, mobFilho,
                            mobPai, null, destinoParser.getPessoa(),
                            destinoParser.getPessoa(), "1");
            return true;
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new AplicacaoException("Erro ao juntar o documento");
        }
    }

    public boolean existeNoProcesso(ExDocumento doc, DocumentoDoProcesso documentoDoProcesso){
        boolean isProcesso = doc.isProcesso();
        Set<ExMobil> juntados = new HashSet<>();
        try{
            if(isProcesso){
                juntados = doc.getUltimoVolume().getJuntados();
            }else{
                juntados = doc.getPrimeiraVia().getJuntados();
            }
        }catch (Exception e){
            juntados = new HashSet<>();
        }
        //Set<ExDocumento> documentos = doc.getExDocumentoFilhoSet();
        for(ExMobil mobil : juntados){
            byte[] bytes = mobil.getExDocumento().getConteudoBlobPdf();
            try {
                String hash = IntegracaoPen.fileSha256ToBase64(bytes);
                if(documentoDoProcesso.getComponenteDigital().get(0).getHash().getValue().equals(hash)){
                    return true;
                }
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        return false;
    }

    private ExDocumento consultarProcessoPorNRE(String nre){
        final ExMobilDaoFiltro flt = new ExMobilDaoFiltro();
        flt.setNumExtDoc(nre);
        List<Object[]> mobils = dao().consultarPorFiltroOtimizado(flt);
        if(mobils != null && !mobils.isEmpty()){
            ExDocumento doc = (ExDocumento)mobils.get(0)[0];
            return doc;
        }
        return null;
    }

}
