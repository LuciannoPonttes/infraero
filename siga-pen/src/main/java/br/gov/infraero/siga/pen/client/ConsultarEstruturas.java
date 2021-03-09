
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
 *         &lt;element name="filtroDeEstruturas" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}filtroDeEstruturas"/>
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
    "filtroDeEstruturas"
})
@XmlRootElement(name = "consultarEstruturas")
public class ConsultarEstruturas {

    @XmlElement(required = true)
    protected FiltroDeEstruturas filtroDeEstruturas;

    /**
     * Gets the value of the filtroDeEstruturas property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroDeEstruturas }
     *     
     */
    public FiltroDeEstruturas getFiltroDeEstruturas() {
        return filtroDeEstruturas;
    }

    /**
     * Sets the value of the filtroDeEstruturas property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroDeEstruturas }
     *     
     */
    public void setFiltroDeEstruturas(FiltroDeEstruturas value) {
        this.filtroDeEstruturas = value;
    }

}
