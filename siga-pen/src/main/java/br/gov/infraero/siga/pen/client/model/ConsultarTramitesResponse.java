
package br.gov.infraero.siga.pen.client.model;

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
 *         &lt;element name="tramitesEncontrados" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}tramitesEncontrados"/>
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
    "tramitesEncontrados"
})
@XmlRootElement(name = "consultarTramitesResponse")
public class ConsultarTramitesResponse {

    @XmlElement(required = true)
    protected TramitesEncontrados tramitesEncontrados;

    /**
     * Gets the value of the tramitesEncontrados property.
     * 
     * @return
     *     possible object is
     *     {@link TramitesEncontrados }
     *     
     */
    public TramitesEncontrados getTramitesEncontrados() {
        return tramitesEncontrados;
    }

    /**
     * Sets the value of the tramitesEncontrados property.
     * 
     * @param value
     *     allowed object is
     *     {@link TramitesEncontrados }
     *     
     */
    public void setTramitesEncontrados(TramitesEncontrados value) {
        this.tramitesEncontrados = value;
    }

}
