
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
 *         &lt;element name="dadosDoComponenteDigital" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosDoComponenteDigital"/>
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
    "dadosDoComponenteDigital"
})
@XmlRootElement(name = "enviarComponenteDigital")
public class EnviarComponenteDigital {

    @XmlElement(required = true)
    protected DadosDoComponenteDigital dadosDoComponenteDigital;

    /**
     * Gets the value of the dadosDoComponenteDigital property.
     * 
     * @return
     *     possible object is
     *     {@link DadosDoComponenteDigital }
     *     
     */
    public DadosDoComponenteDigital getDadosDoComponenteDigital() {
        return dadosDoComponenteDigital;
    }

    /**
     * Sets the value of the dadosDoComponenteDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosDoComponenteDigital }
     *     
     */
    public void setDadosDoComponenteDigital(DadosDoComponenteDigital value) {
        this.dadosDoComponenteDigital = value;
    }

}