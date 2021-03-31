
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
 *         &lt;element name="recusaDeTramite" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}recusaDeTramite"/>
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
    "recusaDeTramite"
})
@XmlRootElement(name = "recusarTramite")
public class RecusarTramite {

    @XmlElement(required = true)
    protected RecusaDeTramite recusaDeTramite;

    /**
     * Gets the value of the recusaDeTramite property.
     * 
     * @return
     *     possible object is
     *     {@link RecusaDeTramite }
     *     
     */
    public RecusaDeTramite getRecusaDeTramite() {
        return recusaDeTramite;
    }

    /**
     * Sets the value of the recusaDeTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link RecusaDeTramite }
     *     
     */
    public void setRecusaDeTramite(RecusaDeTramite value) {
        this.recusaDeTramite = value;
    }

}
