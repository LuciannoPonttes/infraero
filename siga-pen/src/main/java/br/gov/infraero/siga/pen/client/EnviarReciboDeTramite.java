
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
 *         &lt;element name="dadosDoReciboDeTramite" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosDoReciboDeTramite"/>
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
    "dadosDoReciboDeTramite"
})
@XmlRootElement(name = "enviarReciboDeTramite")
public class EnviarReciboDeTramite {

    @XmlElement(required = true)
    protected DadosDoReciboDeTramite dadosDoReciboDeTramite;

    /**
     * Gets the value of the dadosDoReciboDeTramite property.
     * 
     * @return
     *     possible object is
     *     {@link DadosDoReciboDeTramite }
     *     
     */
    public DadosDoReciboDeTramite getDadosDoReciboDeTramite() {
        return dadosDoReciboDeTramite;
    }

    /**
     * Sets the value of the dadosDoReciboDeTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosDoReciboDeTramite }
     *     
     */
    public void setDadosDoReciboDeTramite(DadosDoReciboDeTramite value) {
        this.dadosDoReciboDeTramite = value;
    }

}
