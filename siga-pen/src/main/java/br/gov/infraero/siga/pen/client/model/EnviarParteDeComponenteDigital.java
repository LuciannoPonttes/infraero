
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
 *         &lt;element name="dadosDaParteDeComponenteDigital" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosDaParteDeComponenteDigital"/>
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
    "dadosDaParteDeComponenteDigital"
})
@XmlRootElement(name = "enviarParteDeComponenteDigital")
public class EnviarParteDeComponenteDigital {

    @XmlElement(required = true)
    protected DadosDaParteDeComponenteDigital dadosDaParteDeComponenteDigital;

    /**
     * Gets the value of the dadosDaParteDeComponenteDigital property.
     * 
     * @return
     *     possible object is
     *     {@link DadosDaParteDeComponenteDigital }
     *     
     */
    public DadosDaParteDeComponenteDigital getDadosDaParteDeComponenteDigital() {
        return dadosDaParteDeComponenteDigital;
    }

    /**
     * Sets the value of the dadosDaParteDeComponenteDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosDaParteDeComponenteDigital }
     *     
     */
    public void setDadosDaParteDeComponenteDigital(DadosDaParteDeComponenteDigital value) {
        this.dadosDaParteDeComponenteDigital = value;
    }

}
