
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Contém uma cópia do conteúdo que
 *         foi assinado pelo destinatário,
 *         e o hash gerado nesse processo.
 *       
 * 
 * <p>Java class for conteudoDoReciboDeTramite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conteudoDoReciboDeTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recibo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/recibo}recibo"/>
 *         &lt;element name="cadeiaDoCertificado" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hashDaAssinatura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "conteudoDoReciboDeTramite", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "recibo",
    "cadeiaDoCertificado",
    "hashDaAssinatura"
})
public class ConteudoDoReciboDeTramite {

    @XmlElement(required = true)
    protected Recibo recibo;
    @XmlElement(required = true)
    protected String cadeiaDoCertificado;
    @XmlElement(required = true)
    protected String hashDaAssinatura;

    /**
     * Gets the value of the recibo property.
     * 
     * @return
     *     possible object is
     *     {@link Recibo }
     *     
     */
    public Recibo getRecibo() {
        return recibo;
    }

    /**
     * Sets the value of the recibo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Recibo }
     *     
     */
    public void setRecibo(Recibo value) {
        this.recibo = value;
    }

    /**
     * Gets the value of the cadeiaDoCertificado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCadeiaDoCertificado() {
        return cadeiaDoCertificado;
    }

    /**
     * Sets the value of the cadeiaDoCertificado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCadeiaDoCertificado(String value) {
        this.cadeiaDoCertificado = value;
    }

    /**
     * Gets the value of the hashDaAssinatura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashDaAssinatura() {
        return hashDaAssinatura;
    }

    /**
     * Sets the value of the hashDaAssinatura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashDaAssinatura(String value) {
        this.hashDaAssinatura = value;
    }

}
