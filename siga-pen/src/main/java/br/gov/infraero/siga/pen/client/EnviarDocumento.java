
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
 *         &lt;element name="novoTramiteDeDocumento" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}novoTramiteDeDocumento"/>
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
    "novoTramiteDeDocumento"
})
@XmlRootElement(name = "enviarDocumento")
public class EnviarDocumento {

    @XmlElement(required = true)
    protected NovoTramiteDeDocumento novoTramiteDeDocumento;

    /**
     * Gets the value of the novoTramiteDeDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link NovoTramiteDeDocumento }
     *     
     */
    public NovoTramiteDeDocumento getNovoTramiteDeDocumento() {
        return novoTramiteDeDocumento;
    }

    /**
     * Sets the value of the novoTramiteDeDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link NovoTramiteDeDocumento }
     *     
     */
    public void setNovoTramiteDeDocumento(NovoTramiteDeDocumento value) {
        this.novoTramiteDeDocumento = value;
    }

}
