
/*******************************************************************************
 * Copyright (c) 2006 - 2011 SJRJ.
 * 
 *     This file is part of SIGA.
 * 
 *     SIGA is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     SIGA is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with SIGA.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package br.gov.jfrj.siga.ex.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.jboss.logging.Logger;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;

import br.gov.jfrj.itextpdf.Documento;
import br.gov.jfrj.siga.base.AplicacaoException;
import br.gov.jfrj.siga.base.Prop;
import br.gov.jfrj.siga.base.SigaMessages;
import br.gov.jfrj.siga.base.log.RequestLoggerFilter;
import br.gov.jfrj.siga.cp.CpSituacaoConfiguracao;
import br.gov.jfrj.siga.cp.CpTipoConfiguracao;
import br.gov.jfrj.siga.cp.CpToken;
import br.gov.jfrj.siga.cp.util.SigaUtil;
import br.gov.jfrj.siga.cp.model.DpLotacaoSelecao;
import br.gov.jfrj.siga.cp.model.DpPessoaSelecao;
import br.gov.jfrj.siga.dp.CpOrgao;
import br.gov.jfrj.siga.dp.CpOrgaoUsuario;
import br.gov.jfrj.siga.dp.DpLotacao;
import br.gov.jfrj.siga.dp.DpPessoa;
import br.gov.jfrj.siga.dp.DpResponsavel;
import br.gov.jfrj.siga.dp.dao.CpDao;
import br.gov.jfrj.siga.ex.ExArquivoNumerado;
import br.gov.jfrj.siga.ex.ExClassificacao;
import br.gov.jfrj.siga.ex.ExConfiguracao;
import br.gov.jfrj.siga.ex.ExDocumento;
import br.gov.jfrj.siga.ex.ExDocumentoNumeracao;
import br.gov.jfrj.siga.ex.ExFormaDocumento;
import br.gov.jfrj.siga.ex.ExMobil;
import br.gov.jfrj.siga.ex.ExModelo;
import br.gov.jfrj.siga.ex.ExMovimentacao;
import br.gov.jfrj.siga.ex.ExNivelAcesso;
import br.gov.jfrj.siga.ex.ExPapel;
import br.gov.jfrj.siga.ex.ExSequencia;
import br.gov.jfrj.siga.ex.ExSituacaoConfiguracao;
import br.gov.jfrj.siga.ex.ExTipoDocumento;
import br.gov.jfrj.siga.ex.ExTipoMobil;
import br.gov.jfrj.siga.ex.bl.CurrentRequest;
import br.gov.jfrj.siga.ex.ExTipoMovimentacao;
import br.gov.jfrj.siga.ex.bl.Ex;
import br.gov.jfrj.siga.ex.bl.ExCompetenciaBL;
import br.gov.jfrj.siga.ex.bl.ExConfiguracaoBL;
import br.gov.jfrj.siga.ex.bl.RequestInfo;
import br.gov.jfrj.siga.ex.service.ExService;
import br.gov.jfrj.siga.ex.util.FuncoesEL;
import br.gov.jfrj.siga.hibernate.ExDao;
import br.gov.jfrj.siga.hibernate.ExStarter;
import br.gov.jfrj.siga.model.ContextoPersistencia;
import br.gov.jfrj.siga.model.dao.ModeloDao;
import br.gov.jfrj.siga.parser.PessoaLotacaoParser;
import br.gov.jfrj.siga.parser.SiglaParser;
import br.gov.jfrj.siga.persistencia.ExClassificacaoDaoFiltro;
import br.gov.jfrj.siga.persistencia.ExMobilDaoFiltro;
import br.gov.jfrj.siga.vraptor.ExDocumentoDTO;
import br.gov.jfrj.siga.vraptor.ExMobilSelecao;
import br.gov.jfrj.siga.vraptor.builder.BuscaDocumentoBuilder;
import br.gov.jfrj.siga.vraptor.builder.ExMovimentacaoBuilder;

@WebService(serviceName = "ExService", endpointInterface = "br.gov.jfrj.siga.ex.service.ExService", targetNamespace = "http://impl.service.ex.siga.jfrj.gov.br/")
public class ExServiceImpl implements ExService {
	private final static Logger log = Logger.getLogger(ExService.class);

	private class SoapContext implements Closeable {
		EntityManager em;
		boolean transacional;
		long inicio = System.currentTimeMillis();

		public SoapContext(boolean transacional) {
			this.transacional = transacional;
			em = ExStarter.emf.createEntityManager();
			ContextoPersistencia.setEntityManager(em);

			ServletContext ctx = (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
			HttpServletRequest request = (HttpServletRequest) context.getMessageContext()
					.get(MessageContext.SERVLET_REQUEST);
			HttpServletResponse response = (HttpServletResponse) context.getMessageContext()
					.get(MessageContext.SERVLET_RESPONSE);
			CurrentRequest.set(new RequestInfo(ctx, request, response));

			ModeloDao.freeInstance();
			ExDao.getInstance();
			try {
				Ex.getInstance().getConf().limparCacheSeNecessario();
			} catch (Exception e1) {
				throw new RuntimeException("Não foi possível atualizar o cache de configurações", e1);
			}
			if (this.transacional)
				em.getTransaction().begin();
		}

		public void rollback(Exception e) {
			if (em.getTransaction().isActive())
				em.getTransaction().rollback();
			if (!RequestLoggerFilter.isAplicacaoException(e)) {
				RequestLoggerFilter.logException(null, inicio, e);
			}
		}

		@Override
		public void close() throws IOException {
			try {
				if (this.transacional)
					em.getTransaction().commit();
			} catch (Exception e) {
				if (em.getTransaction().isActive())
					em.getTransaction().rollback();
				throw new RuntimeException(e);
			} finally {
				em.close();
				ContextoPersistencia.setEntityManager(null);
				ContextoPersistencia.setDt(null);
			}
		}
	}		

	@Resource
	private WebServiceContext context;

	private ExDao dao() {
		return ExDao.getInstance();
	}

	public Boolean transferir(String codigoDocumentoVia, String siglaDestino, String siglaCadastrante,
			Boolean forcarTransferencia) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				if (codigoDocumentoVia == null)
					return false;
				ExMobil mob = buscarMobil(codigoDocumentoVia);
				if (mob.doc().isProcesso()) {
					mob = mob.doc().getUltimoVolume();
				} else if (contemApenasUmaVia(mob)) {
					mob = mob.doc().getPrimeiraVia();
				}
				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaCadastrante);
				PessoaLotacaoParser destinoParser = new PessoaLotacaoParser(siglaDestino);
				if (destinoParser.getLotacao() == null && destinoParser.getPessoa() == null)
					return false;
				if (destinoParser.getLotacao() == null)
					destinoParser.setLotacao(destinoParser.getPessoa().getLotacao());
				if (mob.getUltimaMovimentacaoNaoCancelada() != null && ((destinoParser.getLotacao() == null
						|| !destinoParser.getLotacao().equivale(mob.getUltimaMovimentacaoNaoCancelada().getLotaResp()))
						|| (destinoParser.getPessoa() != null && !destinoParser.getPessoa()
								.equivale(mob.getUltimaMovimentacaoNaoCancelada().getResp())))) {
					Ex.getInstance().getBL().transferir(null, null, cadastranteParser.getPessoa(),
							cadastranteParser.getLotacao(), mob, null, null, null, destinoParser.getLotacao(),
							destinoParser.getPessoa(), null, null, null, null, null, false, null, null, null,
							forcarTransferencia, false);
				}
				return true;
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	private ExMobil buscarMobil(String codigoDocumentoVia)
			throws Exception, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		ExMobil mob = null;
		final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
		filter.setSigla(codigoDocumentoVia);
		mob = (ExMobil) dao().consultarPorSigla(filter);
		return mob;
	}

	public Boolean arquivarCorrente(String codigoDocumentoVia, String siglaDestino, String siglaCadastrante)
			throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobil mob = buscarMobil(codigoDocumentoVia);
				if (mob.doc().isProcesso()) {
					mob = mob.doc().getUltimoVolume();
				} else if (contemApenasUmaVia(mob)) {
					mob = mob.doc().getPrimeiraVia();
				}
				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaCadastrante);
				PessoaLotacaoParser destinoParser = new PessoaLotacaoParser(siglaDestino);
				Ex.getInstance().getBL().arquivarCorrente(cadastranteParser.getPessoa(), cadastranteParser.getLotacao(),
						mob, null, null, destinoParser.getPessoa(), false);
				return true;
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public Boolean juntar(String codigoDocumentoViaFilho, String codigoDocumentoViaPai, String siglaDestino,
			String siglaCadastrante) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobil mobFilho = buscarMobil(codigoDocumentoViaFilho);
				ExMobil mobPai = buscarMobil(codigoDocumentoViaPai);

				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaCadastrante);
				PessoaLotacaoParser destinoParser = new PessoaLotacaoParser(siglaDestino);

				Ex.getInstance().getBL().juntarDocumento(cadastranteParser.getPessoa(), cadastranteParser.getPessoa(),
						cadastranteParser.getLotacao(), null, mobFilho, mobPai, null, destinoParser.getPessoa(),
						destinoParser.getPessoa(), "1");
				return true;
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public Boolean isAssinado(String codigoDocumento, String siglaCadastrante) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobil mob = buscarMobil(codigoDocumento);
				return !mob.getExDocumento().isPendenteDeAssinatura();
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public Boolean isSemEfeito(String codigoDocumento) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobil mob = buscarMobil(codigoDocumento);
				if (mob == null)
					return null;
				return mob.getExDocumento().isSemEfeito();
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public Boolean podeMovimentar(String codigoDocumento, String siglaCadastrante) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaCadastrante);
				ExMobil mob = buscarMobil(codigoDocumento);
				return Ex.getInstance().getComp().podeMovimentar(cadastranteParser.getPessoa(),
						cadastranteParser.getLotacao() == null ? cadastranteParser.getPessoa().getLotacao()
								: cadastranteParser.getLotacao(),
						mob);
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public Boolean podeTransferir(String codigoDocumento, String siglaCadastrante, Boolean forcarTransferencia)
			throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaCadastrante);
				ExMobil mob = buscarMobil(codigoDocumento);
				if (mob.doc().isProcesso()) {
					mob = mob.doc().getUltimoVolume();
				} else if (contemApenasUmaVia(mob)) {
					mob = mob.doc().getPrimeiraVia();
				}
				if (forcarTransferencia)
					return Ex.getInstance().getComp().podeSerTransferido(mob);
				else
					return Ex.getInstance().getComp().podeTransferir(cadastranteParser.getPessoa(),
							cadastranteParser.getLotacao(), mob);
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	/**
	 * Verifica se o móbil contém 1 e apenas 1 via. Se houver mais de uma via não há
	 * como determinar qual via deve ser transferida.
	 * 
	 * @param mob
	 * @return
	 */
	private boolean contemApenasUmaVia(ExMobil mob) {
		return mob.doc().getPrimeiraVia() != null && mob.doc().getSetVias().size() == 1;
	}

	public Boolean isAtendente(String codigoDocumento, String siglaTitular) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaTitular);
				ExMobil mob = buscarMobil(codigoDocumento);
				return ExCompetenciaBL.isAtendente(cadastranteParser.getPessoa(), cadastranteParser.getLotacao(), mob);
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String getAtendente(String codigoDocumento, String siglaTitular) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaTitular);
				ExMobil mob = buscarMobil(codigoDocumento);

				if (mob.getDoc().isProcesso())
					mob = mob.getDoc().getUltimoVolume();

				DpResponsavel resp = ExCompetenciaBL.getAtendente(mob);
				if (resp == null)
					return null;

				if (resp instanceof DpPessoa) {
					return resp.getSiglaCompleta() + "@" + ((DpPessoa) resp).getLotacao().getSiglaCompleta();
				} else {
					return "@" + resp.getSiglaCompleta();
				}
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public byte[] obterPdfPorNumeroAssinatura(String num) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				return Ex.getInstance().getBL().obterPdfPorNumeroAssinatura(num);
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String buscarPorCodigo(String codigo) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobilSelecao sel = new ExMobilSelecao();
				sel.setSigla(codigo);
				sel.buscarPorSigla();
				String s = sel.getSigla();
				if (s != null && s.length() > 0)
					return s;
				return null;
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String criarVia(String codigoDocumento, String siglaCadastrante) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				if (codigoDocumento == null)
					return null;
				ExMobil mob = buscarMobil(codigoDocumento);
				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaCadastrante);
				if (cadastranteParser.getLotacao() == null && cadastranteParser.getPessoa() == null)
					return null;
				Ex.getInstance().getBL().criarVia(cadastranteParser.getPessoa(),
						cadastranteParser.getLotacaoOuLotacaoPrincipalDaPessoa(), mob.doc());
				return mob.doc().getUltimaVia().getSigla();
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String form(String codigoDocumento, String variavel) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				if (codigoDocumento == null)
					return null;
				ExMobil mob = buscarMobil(codigoDocumento);
				return mob.doc().getForm().get(variavel);
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	@Override
	public Boolean exigirAnexo(String codigoDocumentoVia, String siglaCadastrante, String descricaoDoAnexo)
			throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobil mob = buscarMobil(codigoDocumentoVia);

				PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(siglaCadastrante);
				Ex.getInstance().getBL().exigirAnexo(cadastranteParser.getPessoa(), cadastranteParser.getLotacao(), mob,
						descricaoDoAnexo);
				return true;
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String toJSON(String codigo) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobil mob = null;
				{
					final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
					filter.setSigla(codigo);
					mob = (ExMobil) dao().consultarPorSigla(filter);
					return Ex.getInstance().getBL().toJSON(mob);
					//return "";
				}
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String criarDocumento(String cadastranteStr, String subscritorStr, String destinatarioStr,
			String destinatarioCampoExtraStr, String descricaoTipoDeDocumento, String nomeForma, String nomeModelo,
			String classificacaoStr, String descricaoStr, Boolean eletronico, String nomeNivelDeAcesso, String conteudo,
			String siglaMobilPai, Boolean finalizar) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
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

				ExDocumento doc = new ExDocumento();

				if (cadastranteStr == null || cadastranteStr.isEmpty())
					throw new AplicacaoException("A matrícula do cadastrante não foi informada.");

				if (subscritorStr == null || subscritorStr.isEmpty())
					throw new AplicacaoException("A matrícula do subscritor não foi informada.");

				cadastrante = dao().getPessoaFromSigla(cadastranteStr);

				if (cadastrante == null)
					throw new AplicacaoException(
							"Não foi possível encontrar um cadastrante com a matrícula informada.");

				if (cadastrante.isFechada())
					throw new AplicacaoException("O cadastrante não está mais ativo.");

				subscritor = dao().getPessoaFromSigla(subscritorStr);

				if (subscritor == null)
					throw new AplicacaoException("Não foi possível encontrar um subscritor com a matrícula informada.");

				if (subscritor.isFechada())
					throw new AplicacaoException("O subscritor não está mais ativo.");

				if (descricaoTipoDeDocumento == null)
					tipoDocumento = (dao().consultar(ExTipoDocumento.TIPO_DOCUMENTO_INTERNO, ExTipoDocumento.class,
							false));
				else
					tipoDocumento = dao().consultarExTipoDocumento(descricaoTipoDeDocumento);

				if (tipoDocumento == null)
					throw new AplicacaoException(
							"Não foi possível encontrar o Tipo de Documento. Os Tipos de Documentos aceitos são: 1-Interno Produzido, 2-Interno Importado, 3-Externo");

				if (nomeForma == null)
					throw new AplicacaoException("O Tipo não foi informado.");

				if (nomeModelo == null)
					throw new AplicacaoException("O modelo não foi informado.");

				modelo = dao().consultarExModelo(nomeForma, nomeModelo);

				if (modelo == null)
					throw new AplicacaoException("Não foi possível encontrar um modelo com os dados informados.");
				else
					modelo = modelo.getModeloAtual();

				forma = modelo.getExFormaDocumento();

				if (!forma.podeSerDoTipo(tipoDocumento))
					throw new AplicacaoException("O documento do tipo " + forma.getDescricao() + " não pode ser "
							+ tipoDocumento.getDescricao());

				if ((classificacaoStr == null || classificacaoStr.isEmpty()) && !modelo.isClassificacaoAutomatica())
					throw new AplicacaoException("A Classificação não foi informada.");

				if (modelo.isClassificacaoAutomatica())
					classificacao = modelo.getExClassificacao();
				else
					classificacao = dao().consultarExClassificacao(classificacaoStr);

				if (classificacao == null)
					throw new AplicacaoException(
							"Não foi possível encontrar uma classificação com o código informado.");
				else
					classificacao = classificacao.getClassificacaoAtual();

				if (eletronico == null)
					eletronico = true;

				Long idSit = Ex
						.getInstance().getConf().buscaSituacao(modelo, tipoDocumento, cadastrante,
								cadastrante.getLotacao(), CpTipoConfiguracao.TIPO_CONFIG_ELETRONICO)
						.getIdSitConfiguracao();

				if (idSit == ExSituacaoConfiguracao.SITUACAO_OBRIGATORIO) {
					eletronico = true;
				} else if (idSit == ExSituacaoConfiguracao.SITUACAO_PROIBIDO) {
					eletronico = false;
				}

				if (nomeNivelDeAcesso == null) {

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
					exTpConfig.setIdTpConfiguracao(CpTipoConfiguracao.TIPO_CONFIG_NIVELACESSO);
					config.setCpTipoConfiguracao(exTpConfig);
					exStConfig.setIdSitConfiguracao(CpSituacaoConfiguracao.SITUACAO_DEFAULT);
					config.setCpSituacaoConfiguracao(exStConfig);

					ExConfiguracao exConfig = ((ExConfiguracao) Ex.getInstance().getConf().buscaConfiguracao(config,
							new int[] { ExConfiguracaoBL.NIVEL_ACESSO }, dt));

					if (exConfig != null)
						nivelDeAcesso = exConfig.getExNivelAcesso();
				} else {
					nivelDeAcesso = dao().consultarExNidelAcesso(nomeNivelDeAcesso);
				}

				if (nivelDeAcesso == null)
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
				exTpConfig.setIdTpConfiguracao(CpTipoConfiguracao.TIPO_CONFIG_NIVEL_ACESSO_MINIMO);
				config.setCpTipoConfiguracao(exTpConfig);
				int nivelMinimo = ((ExConfiguracao) Ex.getInstance().getConf().buscaConfiguracao(config,
						new int[] { ExConfiguracaoBL.NIVEL_ACESSO }, dt)).getExNivelAcesso().getGrauNivelAcesso();
				exTpConfig.setIdTpConfiguracao(CpTipoConfiguracao.TIPO_CONFIG_NIVEL_ACESSO_MAXIMO);
				config.setCpTipoConfiguracao(exTpConfig);
				int nivelMaximo = ((ExConfiguracao) Ex.getInstance().getConf().buscaConfiguracao(config,
						new int[] { ExConfiguracaoBL.NIVEL_ACESSO }, dt)).getExNivelAcesso().getGrauNivelAcesso();

				for (ExNivelAcesso nivelAcesso : listaNiveis) {
					if (nivelAcesso.getGrauNivelAcesso() >= nivelMinimo
							&& nivelAcesso.getGrauNivelAcesso() <= nivelMaximo)
						niveisFinal.add(nivelAcesso);
				}

				if (niveisFinal != null && !niveisFinal.isEmpty() & !niveisFinal.contains(nivelDeAcesso))
					nivelDeAcesso = niveisFinal.get(0);

				doc.setCadastrante(cadastrante);
				doc.setLotaCadastrante(cadastrante.getLotacao());
				doc.setTitular(cadastrante);
				doc.setLotaTitular(cadastrante.getLotacao());

				if (destinatarioStr != null) {
					try {
						destinatarioLotacao = dao().getLotacaoFromSigla(destinatarioStr);

						if (destinatarioLotacao != null)
							doc.setLotaDestinatario(destinatarioLotacao);
					} catch (Exception e) {
					}
				}

				if (destinatarioStr != null && destinatarioLotacao == null) {
					try {
						destinatarioPessoa = dao().getPessoaFromSigla(destinatarioStr);

						if (destinatarioPessoa != null)
							doc.setDestinatario(destinatarioPessoa);
					} catch (Exception e) {
					}
				}

				if (destinatarioStr != null && destinatarioLotacao == null && destinatarioPessoa == null) {
					try {
						destinatarioOrgaoExterno = dao().getOrgaoFromSiglaExata(destinatarioStr);

						if (destinatarioOrgaoExterno != null) {
							doc.setOrgaoExternoDestinatario(destinatarioOrgaoExterno);
							doc.setNmOrgaoExterno(destinatarioCampoExtraStr);
						}
					} catch (Exception e) {
					}
				}

				if (destinatarioStr != null && destinatarioLotacao == null && destinatarioPessoa == null
						&& destinatarioOrgaoExterno == null) {
					doc.setNmDestinatario(destinatarioStr);
				}

				doc.setSubscritor(subscritor);
				doc.setLotaSubscritor(subscritor.getLotacao());
				doc.setOrgaoUsuario(subscritor.getOrgaoUsuario());
				doc.setExTipoDocumento(tipoDocumento);
				doc.setExFormaDocumento(forma);
				doc.setExModelo(modelo);

				if (!modelo.isDescricaoAutomatica())
					doc.setDescrDocumento(descricaoStr);

				doc.setExClassificacao(classificacao);
				if (eletronico)
					doc.setFgEletronico("S");
				else
					doc.setFgEletronico("N");

				doc.setExNivelAcesso(nivelDeAcesso);

				ExMobil mob = new ExMobil();
				mob.setExTipoMobil(dao().consultar(ExTipoMobil.TIPO_MOBIL_GERAL, ExTipoMobil.class, false));
				mob.setNumSequencia(1);
				mob.setExMovimentacaoSet(new TreeSet<ExMovimentacao>());
				mob.setExDocumento(doc);

				if (siglaMobilPai != null && !siglaMobilPai.isEmpty()) {
					final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
					filter.setSigla(siglaMobilPai);
					ExMobil mobPai = (ExMobil) dao().consultarPorSigla(filter);
					if (mobPai != null) {
						ExDocumento docPai = mobPai.getExDocumento();

						if (docPai.getExMobilPai() != null)
							throw new AplicacaoException("Não foi possível criar o documento pois o documento pai ("
									+ docPai.getSigla() + ") já é documento filho.");

						if (docPai.isPendenteDeAssinatura())
							throw new AplicacaoException("Não foi possível criar o documento pois o documento pai ("
									+ docPai.getSigla() + ") ainda não foi assinado.");

						doc.setExMobilPai(mobPai);
					}
				}

				doc.setExMobilSet(new TreeSet<ExMobil>());
				doc.getExMobilSet().add(mob);

				if (conteudo == null)
					conteudo = "";
				try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
					baos.write(conteudo.getBytes());

					doc.setConteudoTpDoc("application/zip");
					doc.setConteudoBlobForm(baos.toByteArray());
				}

				ServletContext servletContext = (ServletContext) context.getMessageContext()
						.get(MessageContext.SERVLET_CONTEXT);

				doc = Ex.getInstance().getBL().gravar(cadastrante, cadastrante, cadastrante.getLotacao(), doc);

				if (finalizar)
					Ex.getInstance().getBL().finalizar(cadastrante, cadastrante.getLotacao(), doc);

				return doc.getSigla();
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String cadastrante(String codigoDocumentoVia) throws Exception {
		try (SoapContext ctx = new SoapContext(false)) {
			ExMobil mob = buscarMobil(codigoDocumentoVia);
			return SiglaParser.makeSigla(mob.doc().getCadastrante(), mob.doc().getLotaCadastrante());
		}
	}

	public String titular(String codigoDocumentoVia) throws Exception {
		try (SoapContext ctx = new SoapContext(false)) {
			ExMobil mob = buscarMobil(codigoDocumentoVia);
			return SiglaParser.makeSigla(mob.doc().getTitular(), mob.doc().getLotaTitular());
		}
	}

	public String subscritor(String codigoDocumentoVia) throws Exception {
		try (SoapContext ctx = new SoapContext(false)) {
			ExMobil mob = buscarMobil(codigoDocumentoVia);
			return SiglaParser.makeSigla(mob.doc().getSubscritor(), mob.doc().getLotaSubscritor());
		}
	}

	public String destinatario(String codigoDocumentoVia) throws Exception {
		try (SoapContext ctx = new SoapContext(false)) {
			ExMobil mob = buscarMobil(codigoDocumentoVia);
			return SiglaParser.makeSigla(mob.doc().getDestinatario(), mob.doc().getLotaDestinatario());
		}
	}

	public String gestor(String codigoDocumentoVia) throws Exception {
		return obterPrimeiroResponsavelPorIdPapel(codigoDocumentoVia, ExPapel.PAPEL_GESTOR);
	}

	public String fiscalTecnico(String codigoDocumentoVia) throws Exception {
		return obterPrimeiroResponsavelPorIdPapel(codigoDocumentoVia, ExPapel.PAPEL_FISCAL_TECNICO);
	}

	public String fiscalAdministrativo(String codigoDocumentoVia) throws Exception {
		return obterPrimeiroResponsavelPorIdPapel(codigoDocumentoVia, ExPapel.PAPEL_FISCAL_ADMINISTRATIVO);
	}

	public String interessado(String codigoDocumentoVia) throws Exception {
		return obterPrimeiroResponsavelPorIdPapel(codigoDocumentoVia, ExPapel.PAPEL_INTERESSADO);
	}

	public String autorizador(String codigoDocumentoVia) throws Exception {
		return obterPrimeiroResponsavelPorIdPapel(codigoDocumentoVia, ExPapel.PAPEL_AUTORIZADOR);
	}

	public String revisor(String codigoDocumentoVia) throws Exception {
		return obterPrimeiroResponsavelPorIdPapel(codigoDocumentoVia, ExPapel.PAPEL_REVISOR);
	}

	public String liquidante(String codigoDocumentoVia) throws Exception {
		return obterPrimeiroResponsavelPorIdPapel(codigoDocumentoVia, ExPapel.PAPEL_LIQUIDANTE);
	}

	private String obterPrimeiroResponsavelPorIdPapel(String codigoDocumentoVia, long papel)
			throws Exception, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
		try (SoapContext ctx = new SoapContext(false)) {
			ExMobil mob = buscarMobil(codigoDocumentoVia);
			List<DpResponsavel> l = mob.doc().getResponsaveisPorPapel(dao().consultar(papel, ExPapel.class, false));
			if (l == null || l.size() == 0)
				return null;
			DpResponsavel r = l.get(0);
			return r.getSiglaDePessoaEOuLotacao();
		}
	}

	public Boolean isModeloIncluso(String codigoDocumento, Long idModelo) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				ExMobil mob = buscarMobil(codigoDocumento);
				if (mob.isGeral())
					mob = mob.getDoc().getPrimeiroMobil();
				return mob.isModeloIncluso(idModelo);
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw ex;
			}
		}
	}

	public String obterNumeracaoExpediente(Long idOrgaoUsu, Long idFormaDoc, Long anoEmissao) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				Long idDocNumeracao = null;
				Long nrDocumento = 0L;
				ContextoPersistencia.flushTransaction();
				
				//Verifica se Range atual existe
				ExDocumentoNumeracao docNumeracao = dao().obterNumeroDocumento(idOrgaoUsu, idFormaDoc, anoEmissao, false);
	
				if (docNumeracao == null) {
					CpOrgaoUsuario orgaoUsuario = new CpOrgaoUsuario();
					ExFormaDocumento formaDocumento = new ExFormaDocumento();
					orgaoUsuario.setIdOrgaoUsu(idOrgaoUsu);
					formaDocumento.setIdFormaDoc(idFormaDoc);
					
					orgaoUsuario = dao().consultarPorId(orgaoUsuario);
					formaDocumento = dao().consultarExFormaPorId(idFormaDoc);
					
					idDocNumeracao = dao().existeRangeNumeroDocumento(idOrgaoUsu, idFormaDoc);
					
					if ( (idDocNumeracao != null) && !Ex.getInstance().getComp().podeReiniciarNumeracao(orgaoUsuario, formaDocumento)) { //Existe Range Anterior e Não pode Resetar numeracao
						dao().updateMantemRangeNumeroDocumento(idDocNumeracao);
			
					} else { //Não existe ou deve resetar numeração
						ExDocumentoNumeracao documentoNumeracao = new ExDocumentoNumeracao();
						
						documentoNumeracao.setIdOrgaoUsu(idOrgaoUsu);
						documentoNumeracao.setIdFormaDoc(idFormaDoc);
						documentoNumeracao.setFlAtivo("1");
						documentoNumeracao.setAnoEmissao(anoEmissao);
	
						nrDocumento = 1L;
						documentoNumeracao.setNrDocumento(nrDocumento);
						documentoNumeracao.setNrInicial(nrDocumento);
						
						dao().gravar(documentoNumeracao);
	
						documentoNumeracao = null;
					}
					
					orgaoUsuario = null;
					formaDocumento = null;
	
				} else { //Range vigente. Só incrementa
					idDocNumeracao = docNumeracao.getIdDocumentoNumeracao();
					dao().incrementNumeroDocumento(idDocNumeracao);
				}
	
		
				if (nrDocumento != 1L) { //Obtém Número Gerado antes de liberar registro
					nrDocumento = dao().obterNumeroGerado(idOrgaoUsu, idFormaDoc, anoEmissao);
				}
	
				ContextoPersistencia.flushTransaction();
				
				//Retorno em String para WS
				return nrDocumento.toString();			
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw new Exception("Ocorreu um problema na obtenção do número do documento definitivo: "
						+ ex.getMessage(), ex);
			}
		}
	}
	
	public String obterSequencia(Integer tipoSequencia, Long anoEmissao, String zerarInicioAno) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				Long idSeq = null;
				Long numero = 0L;
				ContextoPersistencia.flushTransaction();
				
				//Verifica se Range atual existe
				ExSequencia sequencia = dao().obterSequencia(tipoSequencia, anoEmissao, true);
	
				if (sequencia == null) {
	
					sequencia = dao().existeRangeSequencia(tipoSequencia);
					
					if (sequencia != null && "0".equals(sequencia.getZerarInicioAno())) { //Existe Range Anterior e Não pode Resetar numeracao
						dao().updateMantemRangeSequencia(sequencia.getIdSequencia());
			
					} else { //Não existe ou deve resetar numeração
						ExSequencia exSequencia = new ExSequencia();
						
						exSequencia.setTipoSequencia(tipoSequencia);
						exSequencia.setFlAtivo("1");
						exSequencia.setAnoEmissao(anoEmissao);
						if(zerarInicioAno != null && !"".equals(zerarInicioAno)) {
							exSequencia.setZerarInicioAno(zerarInicioAno);
						} else {
							exSequencia.setZerarInicioAno("1");
						}
	
						numero = 1L;
						exSequencia.setNumero(numero);
						exSequencia.setNrInicial(numero);
						
						dao().gravar(exSequencia);
	
						exSequencia = null;
					}
	
				} else { //Range vigente. Só incrementa
					idSeq = sequencia.getIdSequencia();
					dao().incrementNumero(idSeq);
				}
	
		
				if (numero != 1L) { //Obtém Número Gerado antes de liberar registro
					numero = dao().obterNumeroGerado(tipoSequencia, anoEmissao);
				}
	
				ContextoPersistencia.flushTransaction();
				
				//Retorno em String para WS
				return numero.toString();			
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw new Exception("Ocorreu um problema na obtenção do número: "
						+ ex.getMessage(), ex);
			}
		}	
	}
	
	public String obterSiglaMobilPorIdDoc(Long idDoc)  throws Exception {
		try (SoapContext ctx = new SoapContext(false)) {
			ExDocumento doc = ExDao.getInstance().consultar(idDoc, ExDocumento.class, false);
			if (doc != null) {
				return doc.getPrimeiroMobil().getSigla();
			} else
				return "";
		}
	}
	
	
	public String obterMetadadosDocumento(String siglaDocumento, String token) throws Exception {
		try (SoapContext ctx = new SoapContext(false)) {
			if(Prop.getBool("/siga.ws.seguranca.token.jwt"))
				SigaUtil.getInstance().validarToken(token);
			
			
			final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
			filter.setSigla(siglaDocumento);
			
			ExMobil mob = dao().consultarPorSigla(filter);
			if (mob != null){
				return Ex.getInstance().getBL().documentoToJSON(mob.getDoc());
			}
		} catch (Exception e) {
			throw new Exception("Ocorreu um problema na obtenção dos Metadados do Documento: "
					+ e.getMessage(), e);
		}
		return "Ocorreu um problema na obtenção dos Metadados do Documento";
	}
	
	public String obterMarcadores(String token) throws Exception {
		try (SoapContext ctx = new SoapContext(false)) {
			if(Prop.getBool("/siga.ws.seguranca.token.jwt"))
				SigaUtil.getInstance().validarToken(token);
			
			return Ex.getInstance().getBL().marcadoresGeraisTaxonomiaAdministradaToJSON();
			
		} catch (Exception e) {
			throw new Exception("Ocorreu um problema na obtenção da lista de Marcadores: "
					+ e.getMessage(), e);
		}
	}
	public String publicarDocumentoPortal(String siglaDocumento, String cadastranteStr, String marcadoresStr, String token) throws Exception {
		try (SoapContext ctx = new SoapContext(true)) {
			try {
				if(Prop.getBool("/siga.ws.seguranca.token.jwt"))
					SigaUtil.getInstance().validarToken(token);
				
				DpPessoa cadastrante = null;
				
	    		cadastrante = dao().getPessoaFromSigla(cadastranteStr);
	    		
	    		if(cadastrante == null)
	    			throw new AplicacaoException("Não foi possível encontrar um cadastrante com a matrícula informada.");
	    		
	    		if(cadastrante.isFechada())
	    			throw new AplicacaoException("O cadastrante não está mais ativo.");
				
				final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
				filter.setSigla(siglaDocumento);
				
				ExMobil mob = dao().consultarPorSigla(filter);
				
				if (mob != null){
					
					/* Valida se usuário WS pode movimentar */
					DpPessoa cadastranteWS = null;
					cadastranteWS = dao().getPessoaFromSigla(SigaUtil.getInstance().parseTokenJwt(token).get("sub").toString());
		    		if (!Ex.getInstance().getComp().podePublicarPortalTransparenciaWS(cadastranteWS, cadastranteWS.getLotacao(),mob)) {
		    			throw new AplicacaoException(
		    					"Não é possível " + SigaMessages.getMessage("documento.publicar.portaltransparencia"));
		    		}
					/* Fim da Validação */
		    		String[] listaMarcadores = null;	    		
		    		if (!"".equals(marcadoresStr)) {
		    			listaMarcadores = marcadoresStr.split(",");
		    		}
		    		
					CpToken sigaUrlPermanente = new CpToken();
					sigaUrlPermanente = Ex.getInstance().getBL().publicarTransparencia(mob, cadastrante, cadastrante.getLotacao(),listaMarcadores,true);
					String url = System.getProperty("siga.ex.enderecoAutenticidadeDocs").replace("/sigaex/public/app/autenticar", "");
					String caminho = url + "/siga/public/app/sigalink/1/" + sigaUrlPermanente.getToken();
					
					return "Documento "+ siglaDocumento +" enviado para publicação. Gerado para acesso externo ao documento: "+ caminho; 
				}
			} catch (Exception ex) {
				ctx.rollback(ex);
				throw new Exception("Ocorreu um problema na publicação de documento em Portal: "
						+ ex.getMessage(), ex);
			}
			return "Ocorreu um problema na publicação de documento em Portal.";
		}
	}
	
	public String autenticarDocumento(String sigla, final Boolean juntar, final Boolean tramitar, String nomeUsuarioSubscritor,
			String senhaUsuarioSubscritor, String siglaCadastrante) throws Exception {

		ExMobil mob = buscarMobil(sigla);
		ExDocumento doc = mob.getExDocumento();
		final ExMovimentacaoBuilder movimentacaoBuilder = ExMovimentacaoBuilder
				.novaInstancia().setMob(mob);
		final ExMovimentacao mov = movimentacaoBuilder.construir(dao());
		
		PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(
				siglaCadastrante);
		
		String resultado = null;

		try {
			resultado = Ex.getInstance()
					.getBL()
					.assinarDocumentoComSenha(cadastranteParser.getPessoa(),
							cadastranteParser.getLotacao(), doc, mov.getDtMov(),
							nomeUsuarioSubscritor, senhaUsuarioSubscritor, false, true,
							mov.getTitular(), true, juntar, tramitar, false);
		} catch (Exception e) {
			throw e;
			
		}
		return resultado;

	}
	
	public String criarDocumentoCapturado(String cadastranteStr, String destinatarioStr, String destinatarioCampoExtraStr, String descricaoTipoDeDocumento, String nomeForma ,String nomeModelo, String classificacaoStr, 
			String descricaoStr, Boolean eletronico, String nomeNivelDeAcesso, String conteudo, String siglaMobilPai, Boolean finalizar, byte[] arquivo) throws Exception {
    	try {
    		DpPessoa cadastrante = null;
    		ExModelo modelo = null;
    		ExFormaDocumento forma = null;
    		ExTipoDocumento tipoDocumento = null;
    		ExClassificacao classificacao = null;
    		ExNivelAcesso nivelDeAcesso = null;
    		DpLotacao destinatarioLotacao = null;
    		DpPessoa destinatarioPessoa = null;
    		CpOrgao destinatarioOrgaoExterno = null;
    		
    		ExDocumento doc = new ExDocumento();
    		
    		if(cadastranteStr == null || cadastranteStr.isEmpty())
    			throw new AplicacaoException("A matrícula do cadastrante não foi informada.");

    		cadastrante = dao().getPessoaFromSigla(cadastranteStr);
    		
    		if(cadastrante == null)
    			throw new AplicacaoException("Não foi possível encontrar um cadastrante com a matrícula informada.");
    		
    		if(cadastrante.isFechada())
    			throw new AplicacaoException("O cadastrante não está mais ativo.");
    	
    		
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


			}else{
    			throw new AplicacaoException("Documento capturado não pode ser gravado sem que seja informado o arquivo PDF.");
			}
    		
    		
    		if(nomeNivelDeAcesso == null) {
    			
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
    			nivelDeAcesso = dao().consultarExNidelAcesso(nomeNivelDeAcesso);
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
    		doc.setTitular(cadastrante);
    		doc.setLotaTitular(cadastrante.getLotacao());
    		
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
    		
    		doc.setOrgaoUsuario(cadastrante.getOrgaoUsuario());
    		doc.setExTipoDocumento(tipoDocumento);
    		doc.setExFormaDocumento(forma);
    		doc.setExModelo(modelo);
    		
    		if(!modelo.isDescricaoAutomatica())
    			doc.setDescrDocumento(descricaoStr);
    		
    		doc.setExClassificacao(classificacao);
    		if(eletronico)
    			doc.setFgEletronico("S");
    		else
    			doc.setFgEletronico("N");
    			
    		doc.setExNivelAcesso(nivelDeAcesso);
    		
    		ExMobil mob = new ExMobil();
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
			
			if (conteudo == null)
				conteudo = "";
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				baos.write(conteudo.getBytes());
			
				doc.setConteudoTpDoc("application/zip");
				doc.setConteudoBlobForm(baos.toByteArray());
			}
			
			ServletContext servletContext =
				    (ServletContext) context.getMessageContext().get(MessageContext.SERVLET_CONTEXT);
			
    		doc = Ex.getInstance()
			       .getBL().gravar(cadastrante, cadastrante, cadastrante.getLotacao(), doc);
    		
    		if(finalizar)
    			Ex.getInstance().getBL().finalizar(cadastrante, cadastrante.getLotacao(), doc);

    		return doc.getSigla();
		} catch (Exception e) {
			throw e;
		}	
    }
	
	public String assinarSenhaGravar(String sigla, final Boolean copia, final Boolean juntar, final Boolean tramitar, String nomeUsuarioSubscritor,
			String senhaUsuarioSubscritor, String siglaCadastrante) throws Exception {

		ExMobil mob = buscarMobil(sigla);
		ExDocumento doc = mob.getExDocumento();
		final ExMovimentacaoBuilder movimentacaoBuilder = ExMovimentacaoBuilder
				.novaInstancia().setMob(mob);
		final ExMovimentacao mov = movimentacaoBuilder.construir(dao());
		
		PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(
				siglaCadastrante);
		
		String resultado = null;

		try {
			resultado = Ex.getInstance()
					.getBL()
					.assinarDocumentoComSenha(cadastranteParser.getPessoa(),
							cadastranteParser.getLotacao(), doc, mov.getDtMov(),
							nomeUsuarioSubscritor, senhaUsuarioSubscritor, false, true,
							mov.getTitular(), copia, juntar, tramitar,false);
		} catch (Exception e) {
			throw e;
			
		}
		return resultado;

	}
	
	
	public byte[] getArquivo5(String sigla, String tipoArquivo){
		try {
			
			final boolean isPdf = tipoArquivo.equalsIgnoreCase("pdf");
			final boolean isHtml = tipoArquivo.equalsIgnoreCase("html");
			ExMobil mob = buscarMobil(sigla);
			
			if (mob == null) {
				throw new AplicacaoException("A sigla informada não corresponde a um documento da base de dados.");
			}

			final ExMovimentacao mov = Documento.getMov(mob, sigla);
			final boolean isArquivoAuxiliar = mov != null && mov.getExTipoMovimentacao().getId().equals(ExTipoMovimentacao.TIPO_MOVIMENTACAO_ANEXACAO_DE_ARQUIVO_AUXILIAR);
			byte ab[] = null;
			
			if (isArquivoAuxiliar) {
				ab = mov.getConteudoBlobMov2();
			}
			if (isPdf) {
				ExDocumento doc = mob.getExDocumento();
				ab = doc.getConteudoBlobPdf();
				if (ab == null) {
					throw new AplicacaoException("PDF inválido!");
				}

			}
			if (isHtml) {
				if (mov != null) {
					ab = mov.getConteudoBlobHtml();
				} 
				ExDocumento doc = mob.getExDocumento();
				ab = doc.getConteudoBlobHtml();
				if (ab == null) {
					throw new AplicacaoException("HTML inválido!");
				}
			}

			return ab;
		} catch (Exception e) {
			throw new RuntimeException("erro na geração do documento.", e);
		}
	}
	
	public byte[] preverPdf(ExDocumentoDTO exDocumentoDTO, String siglaCadastrante, String[] vars) throws IOException, IllegalAccessException,
			InvocationTargetException {

		final boolean isDocNovo = (exDocumentoDTO == null);
		buscarDocumento(true, exDocumentoDTO, siglaCadastrante);
		if (exDocumentoDTO.getDoc() != null) {
			if (isDocNovo) {
				//escreverForm(exDocumentoDTO);
			} else {
				lerForm(exDocumentoDTO, vars, siglaCadastrante);
			}
		} else {
			exDocumentoDTO.setDoc(new ExDocumento());
			lerForm(exDocumentoDTO, vars, siglaCadastrante);
		}


		if (exDocumentoDTO.getIdMob() != null) {
			exDocumentoDTO.setModelo(dao().consultar(exDocumentoDTO.getIdMob(),
					ExModelo.class, false));
		}

		Ex.getInstance().getBL()
				.processar(exDocumentoDTO.getDoc(), false, false);

		return exDocumentoDTO.getDoc().getConteudoBlobPdf();
	}
	
	private void buscarDocumento(boolean fPodeNaoExistir, ExDocumentoDTO exDocumentoDto, String siglaCadastrante) {
		if (exDocumentoDto.getMob() == null
				&& exDocumentoDto.getSigla() != null
				&& exDocumentoDto.getSigla().length() != 0) {
			final ExMobilDaoFiltro filter = new ExMobilDaoFiltro();
			filter.setSigla(exDocumentoDto.getSigla());
			exDocumentoDto.setMob((ExMobil) dao().consultarPorSigla(filter));
			if (exDocumentoDto.getMob() != null) {
				exDocumentoDto.setDoc(exDocumentoDto.getMob().getExDocumento());
			}
		} else if (exDocumentoDto.getMob() == null
				&& exDocumentoDto.getDocumentoViaSel().getId() != null) {
			exDocumentoDto
					.setIdMob(exDocumentoDto.getDocumentoViaSel().getId());
			exDocumentoDto.setMob(dao().consultar(exDocumentoDto.getIdMob(),
					ExMobil.class, false));
		} else if (exDocumentoDto.getMob() == null
				&& exDocumentoDto.getIdMob() != null
				&& exDocumentoDto.getIdMob() != 0) {
			exDocumentoDto.setMob(dao().consultar(exDocumentoDto.getIdMob(),
					ExMobil.class, false));
		}
		if (exDocumentoDto.getMob() != null) {
			exDocumentoDto.setDoc(exDocumentoDto.getMob().doc());
		}

		if (exDocumentoDto.getDoc() != null && exDocumentoDto.getMob() == null) {
			exDocumentoDto.setMob(exDocumentoDto.getDoc().getMobilGeral());
		}

		if (!fPodeNaoExistir && exDocumentoDto.getDoc() == null) {
			throw new AplicacaoException("Documento não informado");
		}
		
		if (exDocumentoDto.getDoc() == null) {
			exDocumentoDto.setDoc(new ExDocumento());
			exDocumentoDto.getDoc().setExTipoDocumento(
					dao().consultar(ExTipoDocumento.TIPO_DOCUMENTO_INTERNO,
							ExTipoDocumento.class, false));
			exDocumentoDto.setMob(new ExMobil());
			exDocumentoDto.getMob().setExTipoMobil(
					dao().consultar(ExTipoMobil.TIPO_MOBIL_GERAL,
							ExTipoMobil.class, false));
			exDocumentoDto.getMob().setNumSequencia(1);
			exDocumentoDto.getMob().setExDocumento(exDocumentoDto.getDoc());

			exDocumentoDto.getDoc().setExMobilSet(new TreeSet<ExMobil>());
			exDocumentoDto.getDoc().getExMobilSet()
					.add(exDocumentoDto.getMob());
		}

	}
	
	private void lerForm(ExDocumentoDTO exDocumentoDTO, String[] vars, String siglaCadastrante) throws IOException {
		ExDocumento doc = exDocumentoDTO.getDoc();

		if (exDocumentoDTO.getAnexar()) {
			doc.setConteudoTpDoc(exDocumentoDTO.getConteudoTpDoc());
			doc.setNmArqDoc(exDocumentoDTO.getNmArqDoc());
		}

		doc.setDescrDocumento(exDocumentoDTO.getDescrDocumento());
		doc.setNmSubscritorExt(exDocumentoDTO.getNmSubscritorExt());
		doc.setNmFuncaoSubscritor(exDocumentoDTO.getNmFuncaoSubscritor());
		doc.setNumExtDoc(exDocumentoDTO.getNumExtDoc());
		doc.setNumAntigoDoc(exDocumentoDTO.getNumAntigoDoc());
		doc.setObsOrgao(exDocumentoDTO.getObsOrgao());
		doc.setEletronico(exDocumentoDTO.getEletronico() == 1 ? true : false);
		doc.setNmOrgaoExterno(exDocumentoDTO.getNmOrgaoExterno());
		doc.setDescrClassifNovo(exDocumentoDTO.getDescrClassifNovo());
		doc.setExNivelAcesso(dao().consultar(exDocumentoDTO.getNivelAcesso(),
				ExNivelAcesso.class, false));
		doc.setExTipoDocumento(dao().consultar(exDocumentoDTO.getIdTpDoc(),
				ExTipoDocumento.class, false));

		doc.setNmDestinatario(exDocumentoDTO.getNmDestinatario());

		doc.setExModelo(null);
		if (exDocumentoDTO.getIdMod() != 0) {
			ExModelo modelo = dao().consultar(exDocumentoDTO.getIdMod(),
					ExModelo.class, false);
			if (modelo != null) {
				doc.setExModelo(modelo.getModeloAtual());
				if (!doc.isFinalizado()) {
					doc.setExFormaDocumento(modelo.getExFormaDocumento());
				}
			}
		}

		if (exDocumentoDTO.getClassificacaoSel().getId() != null
				&& exDocumentoDTO.getClassificacaoSel().getId() != 0) {

			final ExClassificacao classificacao = dao().consultar(
					exDocumentoDTO.getClassificacaoSel().getId(),
					ExClassificacao.class, false);

			if (classificacao != null) {
				ExClassificacao cAtual = classificacao.getAtual();
				doc.setExClassificacao(cAtual);
			} else {
				doc.setExClassificacao(null);
				exDocumentoDTO.getClassificacaoSel().apagar();
			}

		} else {
			doc.setExClassificacao(null);
		}
		if (exDocumentoDTO.getCpOrgaoSel().getId() != null) {
			doc.setOrgaoExterno(dao().consultar(
					exDocumentoDTO.getCpOrgaoSel().getId(), CpOrgao.class,
					false));
		} else {
			doc.setOrgaoExterno(null);
		}

		if (doc.getCadastrante() == null && siglaCadastrante != null) {
			PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(
					siglaCadastrante);
			doc.setCadastrante(cadastranteParser.getPessoa());
			doc.setLotaCadastrante(cadastranteParser.getLotacao());
		}

		if (doc.getLotaCadastrante() == null) {
			doc.setLotaCadastrante(doc.getCadastrante().getLotacao());
		}
		if (exDocumentoDTO.getSubscritorSel().getId() != null) {
			PessoaLotacaoParser subscritorParser = new PessoaLotacaoParser(
					exDocumentoDTO.getSubscritorSel().getSigla());
			doc.setSubscritor(subscritorParser.getPessoa());
			doc.setLotaSubscritor(subscritorParser.getLotacao());
		} else {
			doc.setSubscritor(null);
		}

		if (exDocumentoDTO.isSubstituicao()) {
			if (exDocumentoDTO.getTitularSel().getId() != null) {
				PessoaLotacaoParser titularParser = new PessoaLotacaoParser(
						exDocumentoDTO.getTitularSel().getSigla());
				doc.setTitular(titularParser.getPessoa());
				doc.setLotaTitular(titularParser.getLotacao());
			} else {
				doc.setTitular(doc.getSubscritor());
				doc.setLotaTitular(doc.getLotaSubscritor());
			}
		} else {
			doc.setTitular(doc.getSubscritor());
			doc.setLotaTitular(doc.getLotaSubscritor());
		}

		if (exDocumentoDTO.getDestinatarioSel().getId() != null) {
			PessoaLotacaoParser destinatarioParser = new PessoaLotacaoParser(
					exDocumentoDTO.getDestinatarioSel().getSigla());
			
			doc.setDestinatario(destinatarioParser.getPessoa());
			doc.setLotaDestinatario(destinatarioParser.getLotacao());
			doc.setOrgaoExternoDestinatario(null);
		} else {
			doc.setDestinatario(null);
			if (exDocumentoDTO.getLotacaoDestinatarioSel().getId() != null) {
				/*doc.setLotaDestinatario(daoLot(exDocumentoDTO
						.getLotacaoDestinatarioSel().getId()));*/
				doc.setOrgaoExternoDestinatario(null);
			} else {
				doc.setLotaDestinatario(null);

				if (exDocumentoDTO.getOrgaoExternoDestinatarioSel().getId() != null) {
					doc.setOrgaoExternoDestinatario(dao().consultar(
							exDocumentoDTO.getOrgaoExternoDestinatarioSel()
									.getId(), CpOrgao.class, false));

				} else {
					doc.setOrgaoExternoDestinatario(null);
				}
			}
		}

		final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			doc.setDtDoc(df.parse(exDocumentoDTO.getDtDocString()));
		} catch (final ParseException e) {
			doc.setDtDoc(null);
		} catch (final NullPointerException e) {
			doc.setDtDoc(null);
		}
		if (doc.getDtRegDoc() == null)
			doc.setDtRegDoc(dao().dt());

		try {
			doc.setDtDocOriginal(df.parse(exDocumentoDTO
					.getDtDocOriginalString()));
		} catch (final ParseException e) {
			doc.setDtDocOriginal(null);
		} catch (final NullPointerException e) {
			doc.setDtDocOriginal(null);
		}

		if (exDocumentoDTO.getNumExpediente() != null) {
			doc.setNumExpediente(new Long(exDocumentoDTO.getNumExpediente()));
			doc.setAnoEmissao(new Long(exDocumentoDTO.getAnoEmissaoString()));
		}

		if (exDocumentoDTO.getMobilPaiSel().getId() != null) {
			doc.setExMobilPai(dao().consultar(
					exDocumentoDTO.getMobilPaiSel().getId(), ExMobil.class,
					false));
		} else {
			doc.setExMobilPai(null);
		}

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

			final String as[] = vars;
			if (as != null && as.length > 0) {
				for (final String s : as) {
					if (baos.size() > 0)
						baos.write('&');
					baos.write(s.getBytes());
					baos.write('=');
				}
				doc.setConteudoTpDoc("application/zip");
				doc.setConteudoBlobForm(baos.toByteArray());
			}
		}
	}


	@Override
	public String finalizarDocumento(String sigla, String cadastrante) {
		String novaSigla = "";
		try{
			ExMobil mob = buscarMobil(sigla);
			ExDocumento doc = mob.getDoc();
			PessoaLotacaoParser titularParser = new PessoaLotacaoParser(
					cadastrante);
			Ex.getInstance().getBL().finalizar(titularParser.getPessoa(), titularParser.getLotacao(),
					doc);
			novaSigla = doc.getSigla();
		}catch(Exception e){
			return "";
		}

		return novaSigla;
	}

	@Override
	public String anexarArquivo(String sigla, String cadastrante,
			String nomeArquivo, String contentType, byte[] arquivo) {
		try{
			final ExMobil mobOriginal = buscarMobil(sigla);
			ExMobil mob = mobOriginal;
			if (mob != null && !mob.isGeral())
				mob = mob.doc().getMobilGeral();
			final ExMovimentacaoBuilder movimentacaoBuilder = ExMovimentacaoBuilder
					.novaInstancia().setMob(mobOriginal).setContentType(contentType)
					.setFileName(nomeArquivo);
			PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(
					cadastrante);
			final ExMovimentacao mov = movimentacaoBuilder.construir(dao());
			mov.setSubscritor(cadastranteParser.getPessoa());
			mov.setTitular(cadastranteParser.getPessoa());
			
			Integer numBytes = 0;
			if (arquivo == null) {
				throw new AplicacaoException(
						"Arquivo vazio não pode ser anexado.");
			}
			numBytes = arquivo.length;
			if (numBytes > 10 * 1024 * 1024) {
				throw new AplicacaoException("Não é permitida a anexação de arquivos com mais de 10MB.");
			}
			mov.setConteudoBlobMov2(arquivo);

			try {
				final byte[] ab = mov.getNmArqMov().getBytes();
				for (int i = 0; i < ab.length; i++) {
					if (ab[i] == -29) {
						ab[i] = -61;
					}
				}
				final String sNmArqMov = new String(ab, "utf-8");

				Ex.getInstance().getBL()
						.anexarArquivoAuxiliar(cadastranteParser.getPessoa(), cadastranteParser.getLotacao(), mob,
								mov.getDtMov(), mov.getSubscritor(), sNmArqMov,
								mov.getTitular(), mov.getLotaTitular(),
								mov.getConteudoBlobMov2(), mov.getConteudoTpMov());
			} catch (UnsupportedEncodingException ex) {
				throw ex;
			}
			
		}catch(Exception e){
			return "";
		}
		return sigla;
	}
	

	@Override
	public Boolean excluirDocumento(String sigla, String titular) {
		
		ExMobil mob;
		try {
			mob = buscarMobil(sigla);
			final ExDocumento doc = mob.getDoc();
			
			PessoaLotacaoParser titularParser = new PessoaLotacaoParser(
					titular);
			
			if (titularParser.getLotacao() == null)
				titularParser
						.setLotacao(titularParser.getPessoa().getLotacao());
			

			Ex.getInstance().getBL()
					.excluirDocumento(doc, titularParser.getPessoa(), titularParser.getLotacao(), true);
		} catch (Exception e) {
			return false;
		} 

		return true;
	}
	
	
	@Override
	public byte[] getConteudo(String sigla) throws Exception{
		ExMobil mob = buscarMobil(sigla);
		byte[] conteudoForm = mob.getDoc().getConteudoBlobForm();
		
		return conteudoForm;
	}
	
	@Override
	public String buscarDocsFilhos(String sigla) throws Exception{
		String resultado = "";
		try{
			ExMobil mob = buscarMobil(sigla);
			Set<ExDocumento> docs = mob.getExDocumentoFilhoSet();
			JSONArray dArray = new JSONArray();
			for(ExDocumento doc : docs){
				JSONObject d = new JSONObject();
				d.put("sigla", doc.getSigla());
				d.put("lotacao", doc.getLotacao().getSigla());
				d.put("paginas", doc.getContarNumeroDePaginas());
				d.put("data", doc.getDtFinalizacaoDDMMYY());
				d.put("dataAssinatura", doc.getDtAssinatura());
				dArray.put(d);
			}
			
			resultado = dArray.toString();
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}
		return resultado;
	}

	@Override
	public String atualizarConteudo(String siglaCadastrante, String sigla, String siglaMobilPai, String conteudo, Boolean finalizar) throws Exception{
		
		try{
			ExMobil mob = buscarMobil(sigla);
			ExDocumento doc = mob.getDoc();
			
			PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(
					siglaCadastrante);
			
			if (cadastranteParser.getLotacao() == null)
				cadastranteParser
						.setLotacao(cadastranteParser.getPessoa().getLotacao());
			
			if (conteudo == null)
				conteudo = "";
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
			
			try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
				baos.write(conteudo.getBytes());
			
				doc.setConteudoTpDoc("application/zip");
				doc.setConteudoBlobForm(baos.toByteArray());
			}
    		doc = Ex.getInstance()
 			       .getBL().gravar(cadastranteParser.getPessoa(), cadastranteParser.getPessoa(), cadastranteParser.getLotacao(), doc);
     		
     		if(finalizar)
     			Ex.getInstance().getBL().finalizar(cadastranteParser.getPessoa(), cadastranteParser.getLotacao(), doc);
			
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}
		
		return sigla;

	}
	
	//TODO CRIAR METODO PARA ATUALIZAR TUDO DO DOCUMENTO, IGUAL AO CRIAR, SO QUE PASSANDO A SIGLA
	
	
	public String incluirCossignatario(String sigla, String titular, String cossignatario, String funcaoCossignatario) throws Exception{
		
		try{
			ExMobil mob = buscarMobil(sigla);
			ExDocumento doc = mob.getDoc();
			
			PessoaLotacaoParser cossignatarioParser = new PessoaLotacaoParser(cossignatario);
			
			PessoaLotacaoParser titularParser = new PessoaLotacaoParser(titular);
			
			if (titularParser.getLotacao() == null)
				titularParser
						.setLotacao(titularParser.getPessoa().getLotacao());
			
			
			ExMovimentacaoBuilder movimentacaoBuilder = ExMovimentacaoBuilder
					.novaInstancia()
					.setDescrMov(funcaoCossignatario)
					.setMob(mob);
			ExMovimentacao mov = movimentacaoBuilder.construir(dao());
			mov.setSubscritor(cossignatarioParser.getPessoa());
			
			if (!Ex.getInstance()
					.getComp()
					.podeIncluirCosignatario(titularParser.getPessoa(), titularParser.getLotacao(),
							mob)) {
				throw new AplicacaoException("Não é possível incluir cossignatário");
			}
			
			Ex.getInstance()
			.getBL()
			.incluirCosignatario(titularParser.getPessoa(), titularParser.getLotacao(), doc,
					mov.getDtMov(), mov.getSubscritor(), mov.getDescrMov());
			

			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}
		
		return sigla;
	}
	
	public String listarCossignatarios(String sigla, String titular) throws Exception{
		
		try{
			
			PessoaLotacaoParser titularParser = new PessoaLotacaoParser(
					titular);
			
			if (titularParser.getLotacao() == null)
				titularParser
						.setLotacao(titularParser.getPessoa().getLotacao());
			
			JSONArray cossignatarios = new JSONArray();

			ExMobil mob = buscarMobil(sigla);
			ExDocumento doc = mob.getDoc();
			for (ExMovimentacao movCossig : doc.getMovsCosignatario()){
				JSONObject movJson = new JSONObject();
				movJson.put("idMov", movCossig.getIdMov());
				movJson.put("nomePessoa", movCossig.getSubscritor().getNomePessoa());
				movJson.put("siglaPessoa", movCossig.getSubscritor().getSigla());
				
				boolean podeExcluir = Ex.getInstance()
				.getComp()
				.podeExcluirCosignatario(titularParser.getPessoa(), titularParser.getLotacao(),
						doc.getMobilGeral(), movCossig);
				
				movJson.put("podeExcluir", podeExcluir);
				
				cossignatarios.put(movJson);
				
			}
			
			return cossignatarios.toString();
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}

	}
	
	public Boolean excluirMov(String sigla, String titular, Long idMov) throws Exception{
		try{
			ExMobil mob = buscarMobil(sigla);			
			PessoaLotacaoParser titularParser = new PessoaLotacaoParser(
					titular);
			
			if (titularParser.getLotacao() == null)
				titularParser
						.setLotacao(titularParser.getPessoa().getLotacao());

			Ex.getInstance()
					.getBL()
					.excluirMovimentacao(titularParser.getPessoa(), titularParser.getLotacao(), mob,
							idMov);
			
			return true;
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		
		}

	}
	
	public byte[] getArquivo(String arquivo, boolean completo, boolean semMarcas) throws Exception{
		
		final boolean isPdf = arquivo.endsWith(".pdf");
		boolean estampar = !semMarcas;
		
		try{
			final ExMobil mob = Documento.getMobil(arquivo);
			if (mob == null) {
				throw new AplicacaoException("A sigla informada não corresponde a um documento da base de dados.");
			}
			final ExMovimentacao mov = Documento.getMov(mob, arquivo);
			final boolean isArquivoAuxiliar = mov != null && mov.getExTipoMovimentacao().getId().equals(ExTipoMovimentacao.TIPO_MOVIMENTACAO_ANEXACAO_DE_ARQUIVO_AUXILIAR);
			byte ab[] = null;
			if (isArquivoAuxiliar) {
				ab = mov.getConteudoBlobMov2();
			}
			if (isPdf) {
				if (mov != null && !completo && !estampar) {
					ab = mov.getConteudoBlobpdf();
				} else {
					ByteArrayOutputStream baos = new ByteArrayOutputStream();
					Documento.getDocumento(baos, null, mob, mov, completo, estampar, false, null,null);
					ab = baos.toByteArray();
				}
				if (ab == null) {
					throw new Exception("PDF inválido!");
				}
			}

			return ab;

		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}
		
	}
	
	public String consultarProcesso(String sigla) throws Exception{
		try{
			ExMobil mob = buscarMobil(sigla);
			
			if (mob.getDoc().isFinalizado()) {
				if (mob.getDoc().isProcesso()) {
					mob = mob.getDoc().getUltimoVolume();
				} else {
					mob = mob.getDoc().getPrimeiraVia();
				}
			}
			
			JSONArray dArray = new JSONArray();
			for(ExArquivoNumerado arquivo : mob.getArquivosNumerados()){
				JSONObject d = new JSONObject();
				d.put("referenciaPDF", arquivo.getReferenciaPDF());
				d.put("referenciaPDFCompleto", arquivo.getReferenciaPDFCompleto());
				DpLotacao dpLotacao = arquivo.getMobil().getDoc().getLotaCadastrante();
				d.put("lotacao", dpLotacao != null ? dpLotacao.getSigla() : null);
				d.put("pagina",arquivo.getPaginaInicial());
				
				d.put("data", arquivo.getData());
				d.put("sigla", arquivo.getMobil().getSigla());
				d.put("siglaAssinatura", arquivo.getArquivo().getSiglaAssinatura());
				d.put("dataAssinatura", arquivo.getMobil().getDoc().getDtAssinatura());
				
				dArray.put(d);
			}
			
			return dArray.toString();
			
		}catch(Exception e){
			e.printStackTrace(System.out);
			throw e;
		}
	}
	
	public String buscarClassificacao(String descricao, String codigo, Integer offset, Integer itemPagina) throws Exception{
		if (descricao != null){ 
			descricao = descricao.toUpperCase();
		}
		
		try {
			
			CpDao dao = CpDao.getInstance();
			
			final ExClassificacaoDaoFiltro flt = new ExClassificacaoDaoFiltro();
			flt.setDescricao(descricao);
			if (codigo != null) {
				flt.setSigla(codigo);
			}
			
			List<ExClassificacao> itens = dao.consultarPorFiltro(flt, offset, itemPagina);
			
			JSONArray lArray = new JSONArray();
			for(ExClassificacao classificacao : itens){
				JSONObject l = new JSONObject();
				l.put("nome", classificacao.getNome());
				l.put("descricao", classificacao.getDescricao());
				l.put("codAssunto", classificacao.getCodAssunto());
				l.put("sigla", classificacao.getSigla());
				l.put("isAtivo", classificacao.isAtivo());
				l.put("id", classificacao.getId());

				lArray.put(l);
			}

			return lArray.toString();
			
		}catch (Exception e) {
			e.printStackTrace(System.out);
			throw e;
		}

	}

	@Override
	public String consultarQuantitativo(Long forma, Long idClassificacao,
			Long idOrgao, String destinatario, String lotacao,
			String dataInicial, String dataFinal) throws Exception {
		
		

		ExMobilDaoFiltro flt = new ExMobilDaoFiltro();
		flt.setClassificacaoSelId(idClassificacao);
		flt.setIdOrgaoUsu(idOrgao);
		
		flt.setIdFormaDoc(forma);
		
		if(destinatario != null && !destinatario.isEmpty()){
			DpPessoa dest = dao().getPessoaFromSigla(destinatario);
			flt.setDestinatarioSelId(dest.getIdInicial());
		}
		
		if(lotacao != null && !lotacao.isEmpty()){
			DpLotacao lot = dao().getLotacaoFromSigla(lotacao);
			flt.setLotacaoDestinatarioSelId(lot != null ? lot.getIdInicial() : null);
		}
		
		
		final SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		

		if (dataInicial != null && !dataInicial.equals("")) {
			dataInicial = dataInicial + " 00:00:00";
			flt.setDtDoc(df.parse(dataInicial));
		}

		if (dataFinal != null && !dataFinal.equals("")) {
			dataFinal = dataFinal + " 23:59:59";
			flt.setDtDocFinal(df.parse(dataFinal));
		}else{
			flt.setDtDocFinal(new Date()); //PARA PESQUISAR SOMENTE OS DOCUMENTOS COM DATA DE FINALIZACAO
		}

		List<Object[]> mobs = dao().consultarPorFiltroOtimizado(flt);
		Integer qts = dao().consultarQuantidadePorFiltroOtimizado(flt, null, null);
		
		JSONObject retorno = new JSONObject();
		retorno.put("total", qts);
	
		JSONArray lArray = new JSONArray();
		
		for(Object[] obj : mobs){
			ExDocumento mobil = (ExDocumento)obj[0];
			
			JSONObject l = new JSONObject();
			l.put("sigla", mobil.getSigla());
			l.put("dataDocumento", mobil.getDtDoc());
			l.put("especie", mobil.getExFormaDocumento().getDescricao());
			l.put("classificacao", mobil.getExClassificacao().getDescricao());
			
			lArray.put(l);
		}
		
		retorno.put("documentos", lArray);

		return retorno.toString();
	}

	@Override
	public String detalharDocumento(String sigla) throws Exception {

		ExMobil mob = buscarMobil(sigla);
		JSONObject d = new JSONObject();

		d.put("sigla", mob.getExDocumento().getSigla());
		d.put("lotacao", mob.getExDocumento().getLotacao().getSigla());
		d.put("nomeCompleto", mob.getNomeCompleto());
		d.put("dataFinalizacao", mob.getExDocumento().getDtFinalizacaoDDMMYY());
		d.put("dataAssinatura", mob.getExDocumento().getDtAssinatura());
		d.put("especie", mob.getExDocumento().getExFormaDocumento()
				.getDescricao());
		d.put("modelo", mob.getExDocumento().getExModelo().getDescMod());
		d.put("cadastrante", mob.getExDocumento().getCadastrante()
				.getNomePessoa());
		d.put("destinatario",
				mob.getExDocumento().getDestinatario() != null ? mob
						.getExDocumento().getDestinatario().getNomePessoa()
						: "");
		d.put("classificacao", mob.getExDocumento().getExClassificacao()
				.getDescricaoCompleta());
		d.put("dataDocumento", mob.getExDocumento().getDtDocDDMMYYYY());
		d.put("lotacaoDestinatario",
				mob.getExDocumento().getLotaDestinatario() != null ? mob
						.getExDocumento().getLotaDestinatario().getDescricao()
						: "");
		
		return d.toString();
	}
	
	@Override
	public Boolean vincularPerfil(String sigla, String titular, String data, String responsavel, String lotacaoResponsavel, int tipoResponsavel, Long idPapel) throws Exception{

		DpPessoaSelecao responsavelSel = new DpPessoaSelecao();
		DpLotacaoSelecao lotaResponsavelSel =  new DpLotacaoSelecao();
		PessoaLotacaoParser titularParser = new PessoaLotacaoParser(titular);

		ExMobil mob = buscarMobil(sigla);

		ExMovimentacaoBuilder movimentacaoBuilder = ExMovimentacaoBuilder
				.novaInstancia();
		movimentacaoBuilder.setDtMovString(data)
				.setResponsavelSel(responsavelSel)
				.setLotaResponsavelSel(lotaResponsavelSel).setIdPapel(idPapel);

		if (responsavelSel == null || tipoResponsavel == 2) {
			movimentacaoBuilder.setResponsavelSel(new DpPessoaSelecao());
		}

		if (lotaResponsavelSel == null || tipoResponsavel == 1) {
			movimentacaoBuilder.setLotaResponsavelSel(new DpLotacaoSelecao());
		}

		final ExMovimentacao mov = movimentacaoBuilder.construir(dao());
		
		if(responsavel != null && !responsavel.isEmpty()){
			DpPessoa resp = dao().getPessoaFromSigla(responsavel);
			mov.setResp(resp);
		}
		if(lotacaoResponsavel != null && !lotacaoResponsavel.isEmpty()){
			DpLotacao lot = dao().getLotacaoFromSigla(lotacaoResponsavel);
			mov.setLotaResp(lot);
		}


		if (mov.getResp() == null && mov.getLotaResp() == null) {
			throw new AplicacaoException(
					"Não foi informado o responsável ou lotação responsável para a vinculação de papel ");
		}

		if (mov.getResp() != null) {
			mov.setDescrMov(mov.getExPapel().getDescPapel() + ":"
					+ mov.getResp().getDescricaoIniciaisMaiusculas());
		} else {
			if (mov.getLotaResp() != null) {
				mov.setDescrMov(mov.getExPapel().getDescPapel() + ":"
						+ mov.getLotaResp().getDescricaoIniciaisMaiusculas());
			}
		}

		if (titularParser.getLotacao() == null)
			titularParser
					.setLotacao(titularParser.getPessoa().getLotacao());

		if (!Ex.getInstance()
				.getComp()
				.podeFazerVinculacaoPapel(titularParser.getPessoa(), titularParser.getLotacao(),
						mob)) {
			throw new AplicacaoException(
					"Não é possível fazer vinculação de papel");
		}

		Ex.getInstance()
				.getBL()
				.vincularPapel(titularParser.getPessoa(), titularParser.getLotacao(),
						mob, mov.getDtMov(), mov.getLotaResp(),
						mov.getResp(), mov.getSubscritor(), mov.getTitular(),
						mov.getDescrMov(), mov.getNmFuncaoSubscritor(),
						mov.getExPapel());
		
		
		return true;
	}
	
	@Override
	public Boolean cancelarMov(String cadastrante, String sigla, Long idMov, String dataMov, String motivo) throws Exception{
				
		PessoaLotacaoParser cadastranteParser = new PessoaLotacaoParser(cadastrante);
		ExMovimentacao mov = dao().consultar(idMov, ExMovimentacao.class, false);
		BuscaDocumentoBuilder builder = BuscaDocumentoBuilder.novaInstancia()
				.setSigla(sigla);

		ExMobil mob = buscarMobil(sigla);

		try {
			Ex.getInstance()
					.getBL()
					.cancelar(cadastranteParser.getPessoa(), cadastranteParser.getLotacao(), mob, mov,
							null, mov.getSubscritor(),
							cadastranteParser.getPessoa(), motivo);
		} catch (final Exception e) {
			throw e;
		}

		return true;

	}
	
	@Override
	public String listarPerfilVinculado(String sigla, String titular) throws Exception{
		try{

			PessoaLotacaoParser titularParser = new PessoaLotacaoParser(
					titular);
			
			if (titularParser.getLotacao() == null)
				titularParser
						.setLotacao(titularParser.getPessoa().getLotacao());
			
			JSONArray perfis = new JSONArray();

			ExMobil mob = buscarMobil(sigla);
			ExDocumento doc = mob.getDoc();
			
			List<ExMovimentacao> movs = listaMovimentacaoPorTipo(mob, ExTipoMovimentacao.TIPO_MOVIMENTACAO_VINCULACAO_PAPEL);
			
			
			for (ExMovimentacao mov : movs){
				JSONObject movJson = new JSONObject();
				movJson.put("idMov", mov.getIdMov());
				movJson.put("nomePessoa", mov.getSubscritor().getNomePessoa());
				movJson.put("siglaPessoa", mov.getSubscritor().getSigla());
				movJson.put("papel", mov.getExPapel().getDescPapel());
				
				boolean podeCancelar = Ex.getInstance()
				.getComp()
				.podeCancelarVinculacaoPapel(titularParser.getPessoa(), titularParser.getLotacao(),
						doc.getMobilGeral(), mov);
				
				movJson.put("podeCancelar", podeCancelar);
				
				perfis.put(movJson);
				
			}
			
			return perfis.toString();


		}catch(Exception e){
			throw e;
		}
	}
	
	
	private List<ExMovimentacao> listaMovimentacaoPorTipo(ExMobil mob, long idTipo){
		
		List<ExMovimentacao> movs = new ArrayList<ExMovimentacao>();

		if (mob != null
				&& mob.getExMovimentacaoSet() != null) {
			for (ExMovimentacao m : mob.getExMovimentacaoSet()) {
				if (m.getExTipoMovimentacao().getIdTpMov() == idTipo
						&& m.getExMovimentacaoCanceladora() == null) {
					movs.add(m);
				}
			}
		}

		return movs;
	}

	@Override
	public String criarAtualizarDocumento(String sigla, String cadastranteStr, String subscritorStr,
			String destinatarioStr, String destinatarioCampoExtraStr,
			String descricaoTipoDeDocumento, String nomeForma,
			String nomeModelo, String classificacaoStr, String descricaoStr,
			Boolean eletronico, String nomeNivelDeAcesso,
			LinkedHashMap<String, String> campos, String siglaMobilPai, Boolean finalizar)
			throws Exception {
		
		
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
    		
    		if(nomeNivelDeAcesso == null) {
    			
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
    			nivelDeAcesso = dao().consultarExNidelAcesso(nomeNivelDeAcesso);
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
			
    		doc = Ex.getInstance()
			       .getBL().gravar(cadastrante, subscritor, subscritor.getLotacao(), doc);
    		
    		if(finalizar)
    			Ex.getInstance().getBL().finalizar(cadastrante, cadastrante.getLotacao(), doc);

    		return doc.getSigla();
		} catch (Exception e) {
			throw e;
		}
	}


}
