package br.gov.infraero.siga.pen.client.model;

import br.gov.infraero.siga.pen.client.util.PenProperties;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class IntegracaoPen {

    /**
     * Alias do par de chaves dentro
     * do KeyStore JKS.
     */
    private static final String KEY_ALIAS = PenProperties.getValue("pen.key_alias");

    /**
     * Caminho do WSDL do ambiente desejado.
     */
    private static final String WSDL_PATH = PenProperties.getValue("pen.wsdl_path");

    /**
     * Senha do keystore que contem o par
     * de chaves autorizado a acessar o ambiente.
     */
    private static final String KEYSTORE_PASSWORD = PenProperties.getValue("pen.keystore_password");

    /**
     * Caminho onde se encontra o keystore JKS
     * que contem o par de chaves autorizado
     * a acessar o ambiente.
     */
    private static final String KEYSTORE_PATH = PenProperties.getValue("pen.keystore_path");
    private static final String TRUSTSTORE_PATH = PenProperties.getValue("pen.truststore_path");
    private static final String TRUSTSTORE_PASSWORD = PenProperties.getValue("pen.truststore_password");

    private static final String PEN_SERVICE_QNAME = PenProperties.getValue("pen.service.qname");
    private static final String PEN_SERVICE_NAME = PenProperties.getValue("pen.service.name");
    public static final String ID_REPOSITORIO = PenProperties.getValue("pen.estrutura.id_repositorio");
    public static final String NUM_ESTRUTURA = PenProperties.getValue("pen.estrutura.num_id_estrutura");
    public static final String NOME_PRODUTOR = PenProperties.getValue("pen.produtor.nome");
    public static final String TIPO_PRODUTOR = PenProperties.getValue("pen.produtor.tipo");



    private InteroperabilidadePEN interoperabilidadePEN;

    public static void main(String args[]){
        IntegracaoPen integracaoPen = new IntegracaoPen();
        List<RepositoriosEncontrados.Repositorio> estruturas = integracaoPen.listarRepositorioEstruturas();
        for(RepositoriosEncontrados.Repositorio respositorio : estruturas){
            System.out.println(respositorio.getNome());
        }
    }

    private InteroperabilidadePEN getInteroperabilidadePEN(){

        if(this.interoperabilidadePEN == null){
            System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
            System.setProperty("javax.net.ssl.keyStore", KEYSTORE_PATH);
            System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
            System.setProperty("javax.net.ssl.trustStore", TRUSTSTORE_PATH);
            System.setProperty("javax.net.ssl.trustStorePassword", TRUSTSTORE_PASSWORD);
            System.setProperty("jdk.tls.client.protocols", "TLSv1");
            System.setProperty("https.protocols", "TLSv1");

            try {

                URL url = new URL(WSDL_PATH);

                QName qname = new QName(PEN_SERVICE_QNAME, PEN_SERVICE_NAME);

                Service service = Service.create(url, qname);
                this.interoperabilidadePEN = service.getPort(InteroperabilidadePEN.class);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return this.interoperabilidadePEN;
    }

    public List<RepositoriosEncontrados.Repositorio> listarRepositorioEstruturas(){
        RepositoriosEncontrados repositorios = null;
        FiltroDeConsultaDeRepositoriosDeEstrutura filtro = new FiltroDeConsultaDeRepositoriosDeEstrutura();
        filtro.setAtivos(true);
        try {
            repositorios = getInteroperabilidadePEN().consultarRepositoriosDeEstruturas(filtro);
        } catch (InteroperabilidadeException_Exception e) {
            System.out.println("Código do erro: " + e.getFaultInfo().getCodigoErro());
            System.out.println("Mensagem de erro: " + e.getFaultInfo().getMensagem());
        }
        return repositorios.getRepositorio();
    }

    public List<EstruturasEncontradas.Estrutura> consultarEstruturas(Long idRepositorioEstrutura){
        EstruturasEncontradas estruturas = null;
        FiltroDeEstruturas filtro = new FiltroDeEstruturas();
        filtro.setIdentificacaoDoRepositorioDeEstruturas(BigInteger.valueOf(idRepositorioEstrutura));
        filtro.setApenasAtivas(true);
        try {
            estruturas = getInteroperabilidadePEN().consultarEstruturas(filtro);
        } catch (InteroperabilidadeException_Exception e) {
            System.out.println("Código do erro: " + e.getFaultInfo().getCodigoErro());
            System.out.println("Mensagem de erro: " + e.getFaultInfo().getMensagem());
        }
        return estruturas.estrutura;
    }

    public EstruturasEncontradas consultarEstruturas(FiltroDeEstruturas filtro){
        EstruturasEncontradas estruturas = null;
        try {
            estruturas = getInteroperabilidadePEN().consultarEstruturas(filtro);
        } catch (InteroperabilidadeException_Exception e) {
            System.out.println("Código do erro: " + e.getFaultInfo().getCodigoErro());
            System.out.println("Mensagem de erro: " + e.getFaultInfo().getMensagem());
        }
        return estruturas;
    }

    public EstruturasEncontradas.Estrutura consultarEstrutura(Long idRepositorioEstrutura, Long idEstrutura){
        EstruturasEncontradas estruturas = null;
        FiltroDeEstruturas filtro = new FiltroDeEstruturas();
        filtro.setIdentificacaoDoRepositorioDeEstruturas(BigInteger.valueOf(idRepositorioEstrutura));
        filtro.setNumeroDeIdentificacaoDaEstrutura(String.valueOf(idEstrutura));
        filtro.setApenasAtivas(true);
        try {
            estruturas = getInteroperabilidadePEN().consultarEstruturas(filtro);
        } catch (InteroperabilidadeException_Exception e) {
            System.out.println("Código do erro: " + e.getFaultInfo().getCodigoErro());
            System.out.println("Mensagem de erro: " + e.getFaultInfo().getMensagem());
        }
        return estruturas.estrutura.isEmpty() ? null : estruturas.estrutura.get(0);
    }

    public ListaDePendencias listarPendencias(FiltroDePendencias filtro) throws Exception {
        ListaDePendencias pendencias = getInteroperabilidadePEN().listarPendencias(filtro);
        return pendencias;
    }

    public TramitesEncontrados consultarTramites(FiltroDeConsultaDeTramites filtro) throws Exception {
        TramitesEncontrados tramites = getInteroperabilidadePEN().consultarTramites(filtro);
        return tramites;
    }

    public Metadados solicitarMetadados(long idt) throws Exception {
        Metadados metadados = getInteroperabilidadePEN().solicitarMetadados(idt);
        return metadados;
    }

    public void recusarTramite(Long idt, String motivo, String justificativa) throws Exception {
        RecusaDeTramite recusa = new RecusaDeTramite();
        recusa.setIDT(idt);
        recusa.setMotivo(motivo);
        recusa.setJustificativa(justificativa);
        getInteroperabilidadePEN().recusarTramite(recusa);
    }

    public void cancelarEnvioTramite(long idt) throws Exception {
        getInteroperabilidadePEN().cancelarEnvioDeTramite(idt);
    }

    public DataHandler receberComponenteDigital(ParametrosParaRecebimentoDeComponenteDigital parametros) throws Exception {
        return getInteroperabilidadePEN().receberComponenteDigital(parametros);
    }

    public void cienciaRecusa(Long idt) throws InteroperabilidadeException_Exception {
        getInteroperabilidadePEN().cienciaRecusa(idt);
    }

    public void enviarReciboTramite( long idt, String nre, List<String> hashesComponenteDigital) throws Exception {

        Calendar agora = Calendar.getInstance();

        DadosDoReciboDeTramite dadosEnvio = new DadosDoReciboDeTramite();

        dadosEnvio.setIDT(idt);
        dadosEnvio.setDataDeRecebimento(DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) agora));

        Signature signature = Signature.getInstance("SHA256withRSA");

        KeyStore keystore = KeyStore.getInstance("PKCS12");
        keystore.load(new FileInputStream(KEYSTORE_PATH), KEYSTORE_PASSWORD.toCharArray());
        PrivateKey key = (PrivateKey) keystore.getKey(KEY_ALIAS, KEYSTORE_PASSWORD.toCharArray());
        signature.initSign(key);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        StringBuilder conteudoAsString = new StringBuilder();
        conteudoAsString.append("<recibo><IDT>");
        conteudoAsString.append(idt);
        conteudoAsString.append("</IDT><NRE>");
        conteudoAsString.append(nre);
        conteudoAsString.append("</NRE><dataDeRecebimento>");
        conteudoAsString.append(format.format(agora.getTime()));
        conteudoAsString.append("</dataDeRecebimento>");

        for(String hash: hashesComponenteDigital){
            conteudoAsString.append("<hashDoComponenteDigital>");
            conteudoAsString.append(hash);
            conteudoAsString.append("</hashDoComponenteDigital>");
        }

        conteudoAsString.append("</recibo>");
        byte[] conteudo = conteudoAsString.toString().getBytes();

        signature.update(conteudo);
        byte[] assinatura = signature.sign();
        dadosEnvio.setHashDaAssinatura(org.apache.axis.encoding.Base64.encode(assinatura));

        getInteroperabilidadePEN().enviarReciboDeTramite(dadosEnvio);

    }

    public ConteudoDoReciboDeTramite downloadReciboTramite(long idt) throws Exception {
        ConteudoDoReciboDeTramite recibo = getInteroperabilidadePEN().receberReciboDeTramite(idt);
        return recibo;
    }

    public ConteudoDoReciboDeEnvio downloadReciboEnvio(long idt) throws InteroperabilidadeException_Exception {
        ConteudoDoReciboDeEnvio reciboDeEnvio = getInteroperabilidadePEN().receberReciboDeEnvio(idt);
        return reciboDeEnvio;
    }

    public void uploadArquivoBinario(long ticket, byte[] bytes, MimeType mimeType, String protocolo) throws IOException, NoSuchAlgorithmException, InteroperabilidadeException_Exception {
        DadosDoComponenteDigital dadosUpload = new DadosDoComponenteDigital();
        dadosUpload.setTicketParaEnvioDeComponentesDigitais(ticket);
        dadosUpload.setHashDoComponenteDigital(fileSha256ToBase64(bytes));
        dadosUpload.setProtocolo(protocolo);
        dadosUpload.setConteudoDoComponenteDigital(new DataHandler(new ByteArrayDataSource(bytes, mimeType.value())));
        getInteroperabilidadePEN().enviarComponenteDigital(dadosUpload);
    }

    public DadosTramiteDeProcessoCriado enviarProcessoAdministrativo(String nre, EstruturaOrganizacional orgaoRemetente, EstruturaOrganizacional orgaoDestinatario, Integer nivelSigilo, String nuProtocolo, Produtor produtor, String descricao, List<DocumentoDoProcesso> documentos, List<Interessado> interessados) throws DatatypeConfigurationException, InteroperabilidadeException_Exception {
        NovoTramiteDeProcesso dadosEnvio = new NovoTramiteDeProcesso();
        NovoTramiteDeProcesso.Cabecalho cabecalho = new NovoTramiteDeProcesso.Cabecalho();
        cabecalho.setNRE(nre);
        cabecalho.setObrigarEnvioDeTodosOsComponentesDigitais(true);
        cabecalho.setRemetente(orgaoRemetente);
        cabecalho.setDestinatario(orgaoDestinatario);
        dadosEnvio.setCabecalho(cabecalho);
        Processo processo = new Processo();
        processo.setProtocolo(nuProtocolo);
        processo.setDataHoraDeProducao(DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) Calendar.getInstance()));
        processo.setDataHoraDeRegistro(DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) Calendar.getInstance()));
        processo.setNivelDeSigilo(BigInteger.valueOf(nivelSigilo));
        processo.setProdutor(produtor);
        processo.setDescricao(descricao);
        processo.getDocumento().addAll(documentos);
        if(interessados != null){
            for(Interessado interessado : interessados){
                Interessado interessado1 = new Interessado();
                interessado1.setNome(interessado.getNome());
                interessado1.setTipo(interessado.getTipo());
                processo.getInteressado().add(interessado1);
            }
        }else{
            Interessado interessado1 = new Interessado();
            interessado1.setNome(produtor.getNome());
            interessado1.setTipo("fisica");
            processo.getInteressado().add(interessado1);
        }
        dadosEnvio.setProcesso(processo);
        return getInteroperabilidadePEN().enviarProcesso(dadosEnvio);
    }



    public static String fileSha256ToBase64(byte[] data) throws NoSuchAlgorithmException, IOException {
        //byte[] data = Files.readAllBytes(file.toPath());
        MessageDigest digester = MessageDigest.getInstance("SHA-256");
        digester.update(data);
        return java.util.Base64.getEncoder().encodeToString(digester.digest());
    }
}
