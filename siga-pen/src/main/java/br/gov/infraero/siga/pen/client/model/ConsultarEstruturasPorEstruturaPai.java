
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
 *         &lt;element name="filtroDeEstruturasPorEstruturaPai" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}filtroDeEstruturasPorEstruturaPai"/>
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
    "filtroDeEstruturasPorEstruturaPai"
})
@XmlRootElement(name = "consultarEstruturasPorEstruturaPai")
public class ConsultarEstruturasPorEstruturaPai {

    @XmlElement(required = true)
    protected FiltroDeEstruturasPorEstruturaPai filtroDeEstruturasPorEstruturaPai;

    /**
     * Gets the value of the filtroDeEstruturasPorEstruturaPai property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroDeEstruturasPorEstruturaPai }
     *     
     */
    public FiltroDeEstruturasPorEstruturaPai getFiltroDeEstruturasPorEstruturaPai() {
        return filtroDeEstruturasPorEstruturaPai;
    }

    /**
     * Sets the value of the filtroDeEstruturasPorEstruturaPai property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroDeEstruturasPorEstruturaPai }
     *     
     */
    public void setFiltroDeEstruturasPorEstruturaPai(FiltroDeEstruturasPorEstruturaPai value) {
        this.filtroDeEstruturasPorEstruturaPai = value;
    }

}
