
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Pessoa física, jurídica, órgão ou entidade. Identificada
 *         pelo seu nome ou por vínculo com estrutura do repositório
 *         de estruturas do serviço.
 *       
 * 
 * <p>Java class for pessoa complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="pessoa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome"/>
 *         &lt;element name="tipo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="fisica"/>
 *               &lt;enumeration value="juridica"/>
 *               &lt;enumeration value="orgaopublico"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="numeroDeIdentificacao" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="20"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="estruturaOrganizacional" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pessoa", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "nome",
    "tipo",
    "numeroDeIdentificacao",
    "estruturaOrganizacional"
})
@XmlSeeAlso({
    Produtor.Unidade.class,
    Produtor.class,
    Interessado.class
})
public class Pessoa {

    @XmlElement(required = true)
    protected String nome;
    protected String tipo;
    protected String numeroDeIdentificacao;
    protected EstruturaOrganizacional estruturaOrganizacional;

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
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the numeroDeIdentificacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDeIdentificacao() {
        return numeroDeIdentificacao;
    }

    /**
     * Sets the value of the numeroDeIdentificacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDeIdentificacao(String value) {
        this.numeroDeIdentificacao = value;
    }

    /**
     * Gets the value of the estruturaOrganizacional property.
     * 
     * @return
     *     possible object is
     *     {@link EstruturaOrganizacional }
     *     
     */
    public EstruturaOrganizacional getEstruturaOrganizacional() {
        return estruturaOrganizacional;
    }

    /**
     * Sets the value of the estruturaOrganizacional property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstruturaOrganizacional }
     *     
     */
    public void setEstruturaOrganizacional(EstruturaOrganizacional value) {
        this.estruturaOrganizacional = value;
    }

}
