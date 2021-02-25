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
package br.gov.jfrj.siga.ex.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

//import br.gov.jfrj.siga.base.auditoria.filter.ThreadFilter;
import br.gov.jfrj.siga.ex.bl.CurrentRequest;
import br.gov.jfrj.siga.ex.bl.Ex;
import br.gov.jfrj.siga.ex.bl.RequestInfo;
import br.gov.jfrj.siga.model.ContextoPersistencia;
import br.gov.jfrj.siga.model.dao.ModeloDao;
import br.gov.jfrj.siga.base.Prop;
import br.gov.jfrj.siga.base.auditoria.hibernate.util.SigaHibernateAuditorLogUtil;

public class ExLogAcessoDocsFilter implements Filter{

	private FilterConfig config;

	private static final String ASPAS = "\"";
	private static final String SEPARADOR = ";";
	private boolean isAuditaThreadFilter;
	private static final Logger log = Logger.getLogger("br.gov.jfrj.siga.base.auditoria.filter.ThreadFilter");

	public ExLogAcessoDocsFilter() {
		this.isAuditaThreadFilter = Prop.getBool("audita.thread.filter");
	}

	/**
	 * Marca o momento em que o ThreadFilter iniciou a execução do método
	 * doFilter e grava a URL que está sendo executada. Estes dados serão
	 * utilizados para gerar o Log do tempo gastu durante a execução do filtro
	 * para a URL em questão.</br> <b>Obs:</b> Para que funcione, é necessário
	 * que a propriedade <i>audita.thread.filter</i> esteja definida como
	 * <i>true</i> no arquivo <i>siga.properties</i>.
	 * 
	 * @param request
	 */
	protected StringBuilder iniciaAuditoria(final ServletRequest request) {

		final StringBuilder csv = new StringBuilder();

		if (this.isAuditaThreadFilter) {

			HttpServletRequest r = (HttpServletRequest) request;

			String hostName = this.getHostName();
			String contexto = this.getContexto(r);
			String uri = r.getRequestURI();
			String action = this.getAction(uri, contexto);
			String queryString = r.getQueryString();
			String userPrincipalName = this.getUserPrincipalName(r);

			appendEntreAspas(csv, hostName);
			appendEntreAspas(csv, contexto);
			appendEntreAspas(csv, action);
			appendEntreAspas(csv, queryString);
			appendEntreAspas(csv, userPrincipalName);
			appendEntreAspas(csv, r.getRequestURL());

			SigaHibernateAuditorLogUtil.iniciaMarcacaoDeTempoGasto();
		}

		return csv;
	}

	/**
	 * 
	 * @param request
	 * @return Matrícula do Usuário obtida através do método getName da
	 *         implementação da interface Principal
	 */
	protected String getUserPrincipalName(HttpServletRequest request) {
		return ContextoPersistencia.getUserPrincipal() != null ? ContextoPersistencia.getUserPrincipal() : "";
	}

	/**
	 * Marca o momento em que o ThreadFilter terminou a execução do método
	 * doFilter loga a URL que está sendo executada e o tempo gasto durante o
	 * processo.</br> <b>Obs:</b> Para que funcione, é necessário que a
	 * propriedade <i>audita.thread.filter</i> esteja definida como <i>true</i>
	 * no arquivo <i>siga.properties</i>.
	 */
	protected void terminaAuditoria(final StringBuilder csv) {

		if (this.isAuditaThreadFilter && csv != null) {

			String tempoGastoFormatado = SigaHibernateAuditorLogUtil
					.getTempoGastoFormatado();
			long tempoGastoMillisegundos = SigaHibernateAuditorLogUtil
					.getTempoGastoMilliSegundos();

			appendEntreAspas(csv, tempoGastoFormatado);
			appendEntreAspas(csv, tempoGastoMillisegundos);

			log.info(csv);
		}
	}

	/**
	 * Extrai o contexto da aplicação a partir da requisição
	 * 
	 * @param request
	 * @return Contexto da aplicação
	 */
	private String getContexto(HttpServletRequest request) {
		String contexto = request.getContextPath();
		if (StringUtils.isNotBlank(contexto)) {
			contexto = contexto.substring(1);
		}
		return contexto;
	}

	protected String getAction(String uri, String contexto) {
		String action = null;
		if (StringUtils.isNotBlank(uri) && StringUtils.isNotBlank(contexto)) {
			action = uri.replaceFirst(contexto, "");
		}
		return StringUtils.isNotBlank(action) ? action.substring(1) : action;
	}

	protected String getHostName() {
		String hostName = null;
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			log.warn(
					"Não foi possível identificar o nome do Host para adicioná-lo ao Log por CSV",
					e);
			e.printStackTrace();
		}
		return hostName;
	}

	private StringBuilder appendEntreAspas(StringBuilder csv, Object o) {
		return csv.append(SEPARADOR).append(ASPAS).append(o).append(ASPAS);
	}

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		try {
			doFiltro(request,response,chain);
		} catch (Exception e) {
			throw e;
		}

		
	}

	public void doFiltro(final ServletRequest request,
			final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException {

		final StringBuilder csv = iniciaAuditoria(request);

		// InicializaÃ§Ã£o padronizada
		CurrentRequest.set(new RequestInfo(config.getServletContext(),
				(HttpServletRequest) request, (HttpServletResponse) response));

		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			throw new ServletException(e);
		} finally {
			terminaAuditoria(csv);
		}
	}

	protected String getLoggerName() {
		return "br.gov.jfrj.siga.ex.LogAcessoDocs";
	}

	public void destroy() {
	}

}
