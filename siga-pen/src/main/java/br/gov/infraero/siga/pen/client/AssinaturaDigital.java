
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         Dados que certificam que uma determinada entidade
 *         assinou digitalmente alguma cadeia binária, que
 *         deve estar presente no contexto deste elemento.
 *       
 * 
 * <p>Java class for assinaturaDigital complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="assinaturaDigital">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataHora" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cadeiaDoCertificado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}cadeiaDoCertificado"/>
 *         &lt;element name="hash" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}hashDeAssinatura"/>
 *         &lt;element name="razao" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="observacao" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="0"/>
 *               &lt;maxLength value="1000"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "assinaturaDigital", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital", propOrder = {
    "dataHora",
    "cadeiaDoCertificado",
    "hash",
    "razao",
    "observacao"
})
public class AssinaturaDigital {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHora;
    @XmlElement(required = true)
    protected CadeiaDoCertificado cadeiaDoCertificado;
    @XmlElement(required = true)
    protected HashDeAssinatura hash;
    protected String razao;
    protected String observacao;

    /**
     * Gets the value of the dataHora property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataHora() {
        return dataHora;
    }

    /**
     * Sets the value of the dataHora property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataHora(XMLGregorianCalendar value) {
        this.dataHora = value;
    }

    /**
     * Gets the value of the cadeiaDoCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link CadeiaDoCertificado }
     *     
     */
    public CadeiaDoCertificado getCadeiaDoCertificado() {
        return cadeiaDoCertificado;
    }

    /**
     * Sets the value of the cadeiaDoCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link CadeiaDoCertificado }
     *     
     */
    public void setCadeiaDoCertificado(CadeiaDoCertificado value) {
        this.cadeiaDoCertificado = value;
    }

    /**
     * Gets the value of the hash property.
     * 
     * @return
     *     possible object is
     *     {@link HashDeAssinatura }
     *     
     */
    public HashDeAssinatura getHash() {
        return hash;
    }

    /**
     * Sets the value of the hash property.
     * 
     * @param value
     *     allowed object is
     *     {@link HashDeAssinatura }
     *     
     */
    public void setHash(HashDeAssinatura value) {
        this.hash = value;
    }

    /**
     * Gets the value of the razao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRazao() {
        return razao;
    }

    /**
     * Sets the value of the razao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRazao(String value) {
        this.razao = value;
    }

    /**
     * Gets the value of the observacao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * Sets the value of the observacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObservacao(String value) {
        this.observacao = value;
    }

}
