
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
 *         &lt;element name="estruturasEncontradas" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}estruturasEncontradas"/>
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
    "estruturasEncontradas"
})
@XmlRootElement(name = "consultarEstruturasResponse")
public class ConsultarEstruturasResponse {

    @XmlElement(required = true)
    protected EstruturasEncontradas estruturasEncontradas;

    /**
     * Gets the value of the estruturasEncontradas property.
     * 
     * @return
     *     possible object is
     *     {@link EstruturasEncontradas }
     *     
     */
    public EstruturasEncontradas getEstruturasEncontradas() {
        return estruturasEncontradas;
    }

    /**
     * Sets the value of the estruturasEncontradas property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstruturasEncontradas }
     *     
     */
    public void setEstruturasEncontradas(EstruturasEncontradas value) {
        this.estruturasEncontradas = value;
    }

}
