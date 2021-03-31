
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
 *         &lt;element name="hipotesesLegais" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}hipotesesLegais"/>
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
    "hipotesesLegais"
})
@XmlRootElement(name = "consultarHipotesesLegaisResponse")
public class ConsultarHipotesesLegaisResponse {

    @XmlElement(required = true)
    protected HipotesesLegais hipotesesLegais;

    /**
     * Gets the value of the hipotesesLegais property.
     * 
     * @return
     *     possible object is
     *     {@link HipotesesLegais }
     *     
     */
    public HipotesesLegais getHipotesesLegais() {
        return hipotesesLegais;
    }

    /**
     * Sets the value of the hipotesesLegais property.
     * 
     * @param value
     *     allowed object is
     *     {@link HipotesesLegais }
     *     
     */
    public void setHipotesesLegais(HipotesesLegais value) {
        this.hipotesesLegais = value;
    }

}
