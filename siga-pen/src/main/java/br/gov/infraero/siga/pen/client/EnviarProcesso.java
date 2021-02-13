
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
 *         &lt;element name="novoTramiteDeProcesso" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}novoTramiteDeProcesso"/>
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
    "novoTramiteDeProcesso"
})
@XmlRootElement(name = "enviarProcesso")
public class EnviarProcesso {

    @XmlElement(required = true)
    protected NovoTramiteDeProcesso novoTramiteDeProcesso;

    /**
     * Gets the value of the novoTramiteDeProcesso property.
     * 
     * @return
     *     possible object is
     *     {@link NovoTramiteDeProcesso }
     *     
     */
    public NovoTramiteDeProcesso getNovoTramiteDeProcesso() {
        return novoTramiteDeProcesso;
    }

    /**
     * Sets the value of the novoTramiteDeProcesso property.
     * 
     * @param value
     *     allowed object is
     *     {@link NovoTramiteDeProcesso }
     *     
     */
    public void setNovoTramiteDeProcesso(NovoTramiteDeProcesso value) {
        this.novoTramiteDeProcesso = value;
    }

}
