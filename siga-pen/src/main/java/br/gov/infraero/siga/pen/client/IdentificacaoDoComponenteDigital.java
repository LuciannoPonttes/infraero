
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Dados do componente digital que se deseja
 *         efetuar download. Deve ser um componente
 *         válido e de um trâmite que ainda está aberto.
 *       
 * 
 * <p>Java class for identificacaoDoComponenteDigital complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="identificacaoDoComponenteDigital">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
 *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *         &lt;element name="hashDoComponenteDigital" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identificacaoDoComponenteDigital", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "idt",
    "protocolo",
    "hashDoComponenteDigital"
})
public class IdentificacaoDoComponenteDigital {

    @XmlElement(name = "IDT")
    protected long idt;
    @XmlElement(required = true)
    protected String protocolo;
    @XmlElement(required = true)
    protected String hashDoComponenteDigital;

    /**
     * Gets the value of the idt property.
     * 
     */
    public long getIDT() {
        return idt;
    }

    /**
     * Sets the value of the idt property.
     * 
     */
    public void setIDT(long value) {
        this.idt = value;
    }

    /**
     * Gets the value of the protocolo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocolo() {
        return protocolo;
    }

    /**
     * Sets the value of the protocolo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocolo(String value) {
        this.protocolo = value;
    }

    /**
     * Gets the value of the hashDoComponenteDigital property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashDoComponenteDigital() {
        return hashDoComponenteDigital;
    }

    /**
     * Sets the value of the hashDoComponenteDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashDoComponenteDigital(String value) {
        this.hashDoComponenteDigital = value;
    }

}
