
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Recibo assinado pelo barramento, que 
 *         garante ao remetente que ele enviou
 *         todos os componentes digitais necessários
 *         para o andamento deste trâmite.
 *       
 * 
 * <p>Java class for conteudoDoReciboDeEnvio complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="conteudoDoReciboDeEnvio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reciboDeEnvio" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/recibo}reciboDeEnvio"/>
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
@XmlType(name = "conteudoDoReciboDeEnvio", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "reciboDeEnvio",
    "cadeiaDoCertificado",
    "hashDaAssinatura"
})
public class ConteudoDoReciboDeEnvio {

    @XmlElement(required = true)
    protected ReciboDeEnvio reciboDeEnvio;
    @XmlElement(required = true)
    protected String cadeiaDoCertificado;
    @XmlElement(required = true)
    protected String hashDaAssinatura;

    /**
     * Gets the value of the reciboDeEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link ReciboDeEnvio }
     *     
     */
    public ReciboDeEnvio getReciboDeEnvio() {
        return reciboDeEnvio;
    }

    /**
     * Sets the value of the reciboDeEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReciboDeEnvio }
     *     
     */
    public void setReciboDeEnvio(ReciboDeEnvio value) {
        this.reciboDeEnvio = value;
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
