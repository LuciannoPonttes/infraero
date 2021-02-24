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
package br.gov.jfrj.siga.cp;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import br.gov.jfrj.siga.dp.dao.CpDao;


@Entity
@Immutable
@Cacheable
@Cache(region = CpDao.CACHE_HOURS, usage = CacheConcurrencyStrategy.READ_ONLY)
@Table(name="corporativo.cp_tipo_identidade")
public class CpTipoIdentidade extends AbstractCpTipoIdentidade {
	public static final Integer FORMULARIO = new Integer(1);
	public static final Integer CERTIFICADO = new Integer(2);
	public static final Integer LDAP = new Integer(3);
	public boolean isTipoFormulario() {
		return CpTipoIdentidade.FORMULARIO.equals(this.getIdCpTpIdentidade());
	}
	public boolean isTipoCertificado() {
		return CpTipoIdentidade.CERTIFICADO.equals(this.getIdCpTpIdentidade());
	}
	public boolean isTipoLdap() {
		return CpTipoIdentidade.LDAP.equals(this.getIdCpTpIdentidade());
	}
}
