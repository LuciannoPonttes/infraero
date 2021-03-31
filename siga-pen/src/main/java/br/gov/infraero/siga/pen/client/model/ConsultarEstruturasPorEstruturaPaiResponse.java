
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
 *         &lt;element name="estruturasEncontradasNoFiltroPorEstruturaPai" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}estruturasEncontradasNoFiltroPorEstruturaPai"/>
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
    "estruturasEncontradasNoFiltroPorEstruturaPai"
})
@XmlRootElement(name = "consultarEstruturasPorEstruturaPaiResponse")
public class ConsultarEstruturasPorEstruturaPaiResponse {

    @XmlElement(required = true)
    protected EstruturasEncontradasNoFiltroPorEstruturaPai estruturasEncontradasNoFiltroPorEstruturaPai;

    /**
     * Gets the value of the estruturasEncontradasNoFiltroPorEstruturaPai property.
     * 
     * @return
     *     possible object is
     *     {@link EstruturasEncontradasNoFiltroPorEstruturaPai }
     *     
     */
    public EstruturasEncontradasNoFiltroPorEstruturaPai getEstruturasEncontradasNoFiltroPorEstruturaPai() {
        return estruturasEncontradasNoFiltroPorEstruturaPai;
    }

    /**
     * Sets the value of the estruturasEncontradasNoFiltroPorEstruturaPai property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstruturasEncontradasNoFiltroPorEstruturaPai }
     *     
     */
    public void setEstruturasEncontradasNoFiltroPorEstruturaPai(EstruturasEncontradasNoFiltroPorEstruturaPai value) {
        this.estruturasEncontradasNoFiltroPorEstruturaPai = value;
    }

}
