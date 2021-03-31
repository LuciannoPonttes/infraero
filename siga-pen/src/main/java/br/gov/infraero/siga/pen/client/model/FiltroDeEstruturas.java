
package br.gov.infraero.siga.pen.client.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Opções de filtro para o serviço de busca
 *         de estruturas dentro dos repositórios.
 *         O intuito desse serviço é ser utilizado
 *         como backend de grade com formulário de filtro.
 *       
 * 
 * <p>Java class for filtroDeEstruturas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroDeEstruturas">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificacaoDoRepositorioDeEstruturas" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}identificacaoDoRepositorioDeEstruturas"/>
 *         &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura" minOccurs="0"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="siglaCompleta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codigoOrgaoEntidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filtroGeral" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numeroDeIdentificacaoDaEstruturaRaizDaConsulta" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura" minOccurs="0"/>
 *         &lt;element name="apenasAtivas" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="permiteRecebimento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="permiteEnvio" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="paginacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}paginacao" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroDeEstruturas", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "identificacaoDoRepositorioDeEstruturas",
    "numeroDeIdentificacaoDaEstrutura",
    "nome",
    "sigla",
    "siglaCompleta",
    "codigoOrgaoEntidade",
    "filtroGeral",
    "numeroDeIdentificacaoDaEstruturaRaizDaConsulta",
    "apenasAtivas",
    "permiteRecebimento",
    "permiteEnvio",
    "paginacao"
})
public class FiltroDeEstruturas {

    @XmlElement(required = true)
    protected BigInteger identificacaoDoRepositorioDeEstruturas;
    protected String numeroDeIdentificacaoDaEstrutura;
    protected String nome;
    protected String sigla;
    protected String siglaCompleta;
    protected String codigoOrgaoEntidade;
    protected String filtroGeral;
    protected String numeroDeIdentificacaoDaEstruturaRaizDaConsulta;
    protected Boolean apenasAtivas;
    protected Boolean permiteRecebimento;
    protected Boolean permiteEnvio;
    protected Paginacao paginacao;

    /**
     * Gets the value of the identificacaoDoRepositorioDeEstruturas property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdentificacaoDoRepositorioDeEstruturas() {
        return identificacaoDoRepositorioDeEstruturas;
    }

    /**
     * Sets the value of the identificacaoDoRepositorioDeEstruturas property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdentificacaoDoRepositorioDeEstruturas(BigInteger value) {
        this.identificacaoDoRepositorioDeEstruturas = value;
    }

    /**
     * Gets the value of the numeroDeIdentificacaoDaEstrutura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeIdentificacaoDaEstrutura() {
        return numeroDeIdentificacaoDaEstrutura;
    }

    /**
     * Sets the value of the numeroDeIdentificacaoDaEstrutura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeIdentificacaoDaEstrutura(String value) {
        this.numeroDeIdentificacaoDaEstrutura = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the sigla property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSigla() {
        return sigla;
    }

    /**
     * Sets the value of the sigla property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSigla(String value) {
        this.sigla = value;
    }

    /**
     * Gets the value of the siglaCompleta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaCompleta() {
        return siglaCompleta;
    }

    /**
     * Sets the value of the siglaCompleta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaCompleta(String value) {
        this.siglaCompleta = value;
    }

    /**
     * Gets the value of the codigoOrgaoEntidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoOrgaoEntidade() {
        return codigoOrgaoEntidade;
    }

    /**
     * Sets the value of the codigoOrgaoEntidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoOrgaoEntidade(String value) {
        this.codigoOrgaoEntidade = value;
    }

    /**
     * Gets the value of the filtroGeral property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiltroGeral() {
        return filtroGeral;
    }

    /**
     * Sets the value of the filtroGeral property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiltroGeral(String value) {
        this.filtroGeral = value;
    }

    /**
     * Gets the value of the numeroDeIdentificacaoDaEstruturaRaizDaConsulta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeIdentificacaoDaEstruturaRaizDaConsulta() {
        return numeroDeIdentificacaoDaEstruturaRaizDaConsulta;
    }

    /**
     * Sets the value of the numeroDeIdentificacaoDaEstruturaRaizDaConsulta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeIdentificacaoDaEstruturaRaizDaConsulta(String value) {
        this.numeroDeIdentificacaoDaEstruturaRaizDaConsulta = value;
    }

    /**
     * Gets the value of the apenasAtivas property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApenasAtivas() {
        return apenasAtivas;
    }

    /**
     * Sets the value of the apenasAtivas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApenasAtivas(Boolean value) {
        this.apenasAtivas = value;
    }

    /**
     * Gets the value of the permiteRecebimento property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPermiteRecebimento() {
        return permiteRecebimento;
    }

    /**
     * Sets the value of the permiteRecebimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPermiteRecebimento(Boolean value) {
        this.permiteRecebimento = value;
    }

    /**
     * Gets the value of the permiteEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPermiteEnvio() {
        return permiteEnvio;
    }

    /**
     * Sets the value of the permiteEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPermiteEnvio(Boolean value) {
        this.permiteEnvio = value;
    }

    /**
     * Gets the value of the paginacao property.
     * 
     * @return
     *     possible object is
     *     {@link Paginacao }
     *     
     */
    public Paginacao getPaginacao() {
        return paginacao;
    }

    /**
     * Sets the value of the paginacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paginacao }
     *     
     */
    public void setPaginacao(Paginacao value) {
        this.paginacao = value;
    }

}
