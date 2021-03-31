
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
 *         &lt;element name="filtroDeConsultaDeTramites" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}filtroDeConsultaDeTramites"/>
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
    "filtroDeConsultaDeTramites"
})
@XmlRootElement(name = "consultarTramites")
public class ConsultarTramites {

    @XmlElement(required = true)
    protected FiltroDeConsultaDeTramites filtroDeConsultaDeTramites;

    /**
     * Gets the value of the filtroDeConsultaDeTramites property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroDeConsultaDeTramites }
     *     
     */
    public FiltroDeConsultaDeTramites getFiltroDeConsultaDeTramites() {
        return filtroDeConsultaDeTramites;
    }

    /**
     * Sets the value of the filtroDeConsultaDeTramites property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroDeConsultaDeTramites }
     *     
     */
    public void setFiltroDeConsultaDeTramites(FiltroDeConsultaDeTramites value) {
        this.filtroDeConsultaDeTramites = value;
    }

}
