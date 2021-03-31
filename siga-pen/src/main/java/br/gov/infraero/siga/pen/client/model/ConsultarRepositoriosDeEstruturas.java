
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
 *         &lt;element name="filtroDeConsultaDeRepositoriosDeEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}filtroDeConsultaDeRepositoriosDeEstrutura"/>
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
    "filtroDeConsultaDeRepositoriosDeEstrutura"
})
@XmlRootElement(name = "consultarRepositoriosDeEstruturas")
public class ConsultarRepositoriosDeEstruturas {

    @XmlElement(required = true)
    protected FiltroDeConsultaDeRepositoriosDeEstrutura filtroDeConsultaDeRepositoriosDeEstrutura;

    /**
     * Gets the value of the filtroDeConsultaDeRepositoriosDeEstrutura property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroDeConsultaDeRepositoriosDeEstrutura }
     *     
     */
    public FiltroDeConsultaDeRepositoriosDeEstrutura getFiltroDeConsultaDeRepositoriosDeEstrutura() {
        return filtroDeConsultaDeRepositoriosDeEstrutura;
    }

    /**
     * Sets the value of the filtroDeConsultaDeRepositoriosDeEstrutura property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroDeConsultaDeRepositoriosDeEstrutura }
     *     
     */
    public void setFiltroDeConsultaDeRepositoriosDeEstrutura(FiltroDeConsultaDeRepositoriosDeEstrutura value) {
        this.filtroDeConsultaDeRepositoriosDeEstrutura = value;
    }

}
