
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="conteudoDoReciboDeEnvio" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}conteudoDoReciboDeEnvio"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "conteudoDoReciboDeEnvio"
})
@XmlRootElement(name = "receberReciboDeEnvioResponse")
public class ReceberReciboDeEnvioResponse {

    @XmlElement(required = true)
    protected ConteudoDoReciboDeEnvio conteudoDoReciboDeEnvio;

    /**
     * Gets the value of the conteudoDoReciboDeEnvio property.
     * 
     * @return
     *     possible object is
     *     {@link ConteudoDoReciboDeEnvio }
     *     
     */
    public ConteudoDoReciboDeEnvio getConteudoDoReciboDeEnvio() {
        return conteudoDoReciboDeEnvio;
    }

    /**
     * Sets the value of the conteudoDoReciboDeEnvio property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConteudoDoReciboDeEnvio }
     *     
     */
    public void setConteudoDoReciboDeEnvio(ConteudoDoReciboDeEnvio value) {
        this.conteudoDoReciboDeEnvio = value;
    }

}
