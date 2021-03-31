package br.gov.infraero.siga.pen;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.rmi.RemoteException;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.gov.infraero.siga.pen.client.model.*;


public class MainGuiaUtilizacao {

	/**
	 * Alias do par de chaves dentro
	 * do KeyStore JKS.
	 */
	private static final String KEY_ALIAS = "key-alias";

	/**
	 * Caminho do WSDL do ambiente desejado.
	 */
	//private static final String WSDL_PATH = "https://homolog.api.processoeletronico.gov.br/interoperabilidade/soap/v1_1/?wsdl";
	private static final String WSDL_PATH = "https://homolog.api.processoeletronico.gov.br/interoperabilidade/soap/v2/?wsdl";




	/**
	 * Senha do keystore que cont�m o par
	 * de chaves autorizado a acessar o ambiente.
	 */
	//private static final String KEYSTORE_PASSWORD = "changeit";
	private static final String KEYSTORE_PASSWORD = "6zj0AB8nwsLoQyrZ";

	/**
	 * Caminho onde se encontra o keystore JKS
	 * que cont�m o par de chaves autorizado
	 * a acessar o ambiente. 
	 */
	//private static final String KEYSTORE_PATH = "caminho/absoluto/para/keystore.p12";
	private static final String KEYSTORE_PATH = "/Users/tiagomeneses/Downloads/certificado/EmpresaBrasileiradeInfraestruturaAeroporturia.p12";



	/**
	 * Caminho onde se encontra o truststore JKS
	 * que cont�m o certificado do servidor.
	 */
	private static final String TRUSTSTORE_PATH = "caminho/absoluto/para/truststore.jks";

	private InteroperabilidadePEN interoperabilidadePEN;

	private InteroperabilidadePEN getInteroperabilidadePEN(){

		if(this.interoperabilidadePEN == null){
			System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
			System.setProperty("javax.net.ssl.keyStore", KEYSTORE_PATH);
			System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);

			try {

				URL url = new URL(WSDL_PATH);

				QName qname = new QName("http://pen.planejamento.gov.br/interoperabilidade/soap/v2", "InteroperabilidadePENService");

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

	public static void main(String[] args) throws NoSuchAlgorithmException {


		//System.setProperty("javax.net.ssl.trustStore", TRUSTSTORE_PATH);
		
		// java.net.SocketException: Software caused connection abort: recv failed
		// significa que o aplicativo n�o est� enviando sua pr�pria chave!
		System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
		System.setProperty("javax.net.ssl.keyStore", KEYSTORE_PATH);
		System.setProperty("javax.net.ssl.keyStorePassword", KEYSTORE_PASSWORD);
		
		try {
			
			URL url = new URL(WSDL_PATH);
			
			QName qname = new QName("http://pen.planejamento.gov.br/interoperabilidade/soap/v2", "InteroperabilidadePENService");
			
			Service service = Service.create(url, qname);
			InteroperabilidadePEN ws = service.getPort(InteroperabilidadePEN.class);

			//FiltroDeConsultaDeRepositoriosDeEstrutura filtro = new FiltroDeConsultaDeRepositoriosDeEstrutura();
			//filtro.setAtivos(true);
			//RepositoriosEncontrados repositorios = ws.consultarRepositoriosDeEstruturas(filtro);
			//FiltroDeEstruturas filtroEstrutura = new FiltroDeEstruturas();
			//filtroEstrutura.setIdentificacaoDoRepositorioDeEstruturas(new BigInteger("31"));
			//EstruturasEncontradas estruturasEncontradas = ws.consultarEstruturas(filtroEstrutura);
			//FiltroDeEstruturasPorEstruturaPai filtroPai = new FiltroDeEstruturasPorEstruturaPai();
			//filtroPai.setIdentificacaoDoRepositorioDeEstruturas(new BigInteger("31"));
			//EstruturasEncontradasNoFiltroPorEstruturaPai estruturaFilho = ws.consultarEstruturasPorEstruturaPai(filtroPai);
			//System.out.println(repositorios);
			//System.out.println(estruturasEncontradas);
			//System.out.println(estruturaFilho);

			DadosTramiteDeProcessoCriado respostaEnvio = envioProcessoAdministrativo(ws);

			long ticket = respostaEnvio.getTicketParaEnvioDeComponentesDigitais();
			long idt = respostaEnvio.getIDT();
			String protocolo = respostaEnvio.getComponentesDigitaisSolicitados().getProcesso().get(0).getProtocolo();

			uploadArquivoBinario(ws, ticket);
			downloadReciboEnvio(ws, idt);
			//String nre = solicitaMetadados(ws, idt);
			//downloadBinario(ws, idt);
			//enviaReciboTramite(ws, idt, nre);
			//downloadReciboTramite(ws, idt);
			
		} catch (InteroperabilidadeException_Exception e) {
			System.out.println("C�digo do erro: " + e.getFaultInfo().getCodigoErro());
			System.out.println("Mensagem de erro: " + e.getFaultInfo().getMensagem());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private static void downloadReciboEnvio(InteroperabilidadePEN ws, long idt) throws InteroperabilidadeException_Exception {
		
		ConteudoDoReciboDeEnvio reciboDeEnvio = ws.receberReciboDeEnvio(idt);
		
		System.out.println("Data do recibo de envio: " + reciboDeEnvio.getReciboDeEnvio().getDataDeRecebimentoDoUltimoComponenteDigital());
		
	}

	private static void downloadReciboTramite(InteroperabilidadePEN ws, long idt) throws InteroperabilidadeException_Exception {
		
		ConteudoDoReciboDeTramite recibo = ws.receberReciboDeTramite(idt);
		
		System.out.println("Download do recibo efetuado com sucesso! Data de recebimento: " + recibo.getRecibo().getDataDeRecebimento());
		
	}

	private static void enviaReciboTramite(InteroperabilidadePEN ws, long idt, String nre) throws Exception {

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
		StringBuilder conteudoAsString = new StringBuilder();
		conteudoAsString.append("<recibo><IDT>");
		conteudoAsString.append(idt);
		conteudoAsString.append("</IDT><NRE>");
		conteudoAsString.append(nre);
		conteudoAsString.append("</NRE><dataDeRecebimento>");
		conteudoAsString.append(format.format(agora.getTime()));
		conteudoAsString.append("</dataDeRecebimento>");
		
		//conteudoAsString.append("<hashDoComponenteDigital>l9G0iRAn39p9HM9idu/C8tiZMV5LmmLOoS8N/0SbNHs=</hashDoComponenteDigital>");
		conteudoAsString.append("<hashDoComponenteDigital>WZAbPFL8xC5mdzJyh3BwXqPjeqeYgfxC6Ubc5ylaW3M=</hashDoComponenteDigital>");


		conteudoAsString.append("</recibo>");
		byte[] conteudo = conteudoAsString.toString().getBytes();
		
		signature.update(conteudo);
		byte[] assinatura = signature.sign();
		dadosEnvio.setHashDaAssinatura(assinatura.toString());
		
		ws.enviarReciboDeTramite(dadosEnvio);
		
		System.out.println("Recibo de tramite enviado com sucesso!");
		
	}

	private static void downloadBinario(InteroperabilidadePEN ws, long idt) throws InteroperabilidadeException_Exception, IOException {

		ParametrosParaRecebimentoDeComponenteDigital para = new ParametrosParaRecebimentoDeComponenteDigital();


		IdentificacaoDoComponenteDigital dadosDownload = new IdentificacaoDoComponenteDigital();
		dadosDownload.setIDT(idt);
		dadosDownload.setProtocolo("15900032647");
		
		//dadosDownload.setHashDoComponenteDigital(Base64.decode("l9G0iRAn39p9HM9idu/C8tiZMV5LmmLOoS8N/0SbNHs=").toString());
		dadosDownload.setHashDoComponenteDigital("WZAbPFL8xC5mdzJyh3BwXqPjeqeYgfxC6Ubc5ylaW3M=");


		para.setIdentificacaoDoComponenteDigital(dadosDownload);
		
		DataHandler arquivoBinario = ws.receberComponenteDigital(para);
		System.out.print("Conteúdo do download: \"");
		arquivoBinario.writeTo(System.out);
		System.out.println("\"");
		
	}

	private static String solicitaMetadados(InteroperabilidadePEN ws,
			long idt) throws InteroperabilidadeException_Exception, RemoteException {
		
		Metadados metadados = ws.solicitarMetadados(idt);
		
		System.out.println("Protocolo retornado nos metadados: " + metadados.getProcesso().getProtocolo());
		
		return metadados.getNRE();
		
	}

	private static void uploadArquivoBinario(InteroperabilidadePEN ws, long ticket) throws InteroperabilidadeException_Exception, IOException, NoSuchAlgorithmException {

		File file = new File("/Users/tiagomeneses/Downloads/Apresentacao_Barramento_Evento_SEI.pdf");
		byte[] bytes = Files.readAllBytes(file.toPath());
		
		DadosDoComponenteDigital dadosUpload = new DadosDoComponenteDigital();
		dadosUpload.setTicketParaEnvioDeComponentesDigitais(ticket);
		dadosUpload.setHashDoComponenteDigital(fileSha256ToBase64(bytes));
		//dadosUpload.setHashDoComponenteDigital(Base64.decode("l9G0iRAn39p9HM9idu/C8tiZMV5LmmLOoS8N/0SbNHs=").toString());
		dadosUpload.setProtocolo("15900032649");
		dadosUpload.setConteudoDoComponenteDigital(new DataHandler(new ByteArrayDataSource(bytes, MimeType.APPLICATION_PDF.value())));
		
		ws.enviarComponenteDigital(dadosUpload);
		
		System.out.println("Upload executado com sucesso!");
		
	}

	private static DadosTramiteDeProcessoCriado envioProcessoAdministrativo(InteroperabilidadePEN ws)
			throws IOException, InteroperabilidadeException_Exception, DatatypeConfigurationException, NoSuchAlgorithmException {
		
		NovoTramiteDeProcesso dadosEnvio = new NovoTramiteDeProcesso();
		
		NovoTramiteDeProcesso.Cabecalho cabecalho = new NovoTramiteDeProcesso.Cabecalho();
		
//		use esta chave para desligar o tratamento
//		da solu��o que verifica se um ou mais
//		componentes digitais j� foram enviados
//		para o SPE de destino.
		cabecalho.setObrigarEnvioDeTodosOsComponentesDigitais(true);
		
//		use este valor para tramitar um
//		processo ou documento que j� 
//		passou alguma vez pela solu��o
//		cabecalho.setNRE("0000000142722014");
		
		EstruturaOrganizacional orgaoRemetente = new EstruturaOrganizacional();
		orgaoRemetente.setIdentificacaoDoRepositorioDeEstruturas(new BigInteger("001"));
		orgaoRemetente.setNumeroDeIdentificacaoDaEstrutura("5304");
		cabecalho.setRemetente(orgaoRemetente);
		
		EstruturaOrganizacional orgaoDestinatario = new EstruturaOrganizacional();
		orgaoDestinatario.setIdentificacaoDoRepositorioDeEstruturas(new BigInteger("001"));
		orgaoDestinatario.setNumeroDeIdentificacaoDaEstrutura("30682");
		cabecalho.setDestinatario(orgaoDestinatario);

		dadosEnvio.setCabecalho(cabecalho);
		
		Processo processo = new Processo();
		dadosEnvio.setProcesso(processo);

		processo.setProtocolo("15900032649");
		processo.setDataHoraDeProducao(DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) Calendar.getInstance()));
		processo.setDataHoraDeRegistro(DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) Calendar.getInstance()));
		processo.setNivelDeSigilo(BigInteger.ONE);
		
		Produtor produtor = new Produtor();
		produtor.setTipo("orgaopublico");
		produtor.setNome("Infraero");
		processo.setProdutor(produtor);
		processo.setDescricao("Processo de teste Infraero");

		ComponenteDigital arquivo1 = new ComponenteDigital();
		arquivo1.setNome("Apresentacao_Barramento_Evento_SEI.pdf");

		File file = new File("/Users/tiagomeneses/Downloads/Apresentacao_Barramento_Evento_SEI.pdf");
		byte[] bytes = Files.readAllBytes(file.toPath());
		//byte[] data = Files.readAllBytes(file.toPath());
		
		Hash hash = new Hash();
		//hash.setValue(Base64.decode("l9G0iRAn39p9HM9idu/C8tiZMV5LmmLOoS8N/0SbNHs=").toString());
		hash.setValue(fileSha256ToBase64(bytes));
		hash.setAlgoritmo("SHA256");
		arquivo1.setHash(hash);
		arquivo1.setTipoDeConteudo(TipoDeConteudo.TXT);
		arquivo1.setMimeType(MimeType.APPLICATION_PDF);
		arquivo1.setTamanhoEmBytes(bytes.length);
		arquivo1.setOrdem(BigInteger.ONE);
		
		DocumentoDoProcesso documento1 = new DocumentoDoProcesso();
		
		documento1.getComponenteDigital().add(arquivo1);
		
		documento1.setDescricao("Documento teste infraero");
		documento1.setRetirado(false);
		documento1.setOrdem(BigInteger.ONE);
		documento1.setNivelDeSigilo(BigInteger.ONE);
		
		documento1.setProdutor(produtor);
		
		documento1.setDataHoraDeProducao(DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) Calendar.getInstance()));
		documento1.setDataHoraDeRegistro(DatatypeFactory.newInstance().newXMLGregorianCalendar((GregorianCalendar) Calendar.getInstance()));
		
		Especie especie = new Especie();
		especie.setCodigo("1");
		especie.setNomeNoProdutor("Infraero");
		documento1.setEspecie(especie);
		
		processo.getDocumento().add(documento1);
		
		Interessado interessado1 = new Interessado();
		interessado1.setNome("João Carlos");
		interessado1.setTipo("fisica");
		processo.getInteressado().add(interessado1);
		
		DadosTramiteDeProcessoCriado resposta = ws.enviarProcesso(dadosEnvio);
		
		System.out.println("Tramite criado com sucesso. IDT: " + resposta.getIDT());
		
		return resposta;
	}


	public static String fileSha256ToBase64(byte[] data) throws NoSuchAlgorithmException, IOException {
		//byte[] data = Files.readAllBytes(file.toPath());
		MessageDigest digester = MessageDigest.getInstance("SHA-256");
		digester.update(data);
		return java.util.Base64.getEncoder().encodeToString(digester.digest());
	}
	
}
