
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
 *         &lt;element name="dadosDoTramiteAReproduzir" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosDoTramiteAReproduzir"/>
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
    "dadosDoTramiteAReproduzir"
})
@XmlRootElement(name = "reproduzirUltimoTramite")
public class ReproduzirUltimoTramite {

    @XmlElement(required = true)
    protected DadosDoTramiteAReproduzir dadosDoTramiteAReproduzir;

    /**
     * Gets the value of the dadosDoTramiteAReproduzir property.
     * 
     * @return
     *     possible object is
     *     {@link DadosDoTramiteAReproduzir }
     *     
     */
    public DadosDoTramiteAReproduzir getDadosDoTramiteAReproduzir() {
        return dadosDoTramiteAReproduzir;
    }

    /**
     * Sets the value of the dadosDoTramiteAReproduzir property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosDoTramiteAReproduzir }
     *     
     */
    public void setDadosDoTramiteAReproduzir(DadosDoTramiteAReproduzir value) {
        this.dadosDoTramiteAReproduzir = value;
    }

}
