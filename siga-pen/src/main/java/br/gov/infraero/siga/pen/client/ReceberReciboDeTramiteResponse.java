
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
 *         &lt;element name="conteudoDoReciboDeTramite" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}conteudoDoReciboDeTramite"/>
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
    "conteudoDoReciboDeTramite"
})
@XmlRootElement(name = "receberReciboDeTramiteResponse")
public class ReceberReciboDeTramiteResponse {

    @XmlElement(required = true)
    protected ConteudoDoReciboDeTramite conteudoDoReciboDeTramite;

    /**
     * Gets the value of the conteudoDoReciboDeTramite property.
     * 
     * @return
     *     possible object is
     *     {@link ConteudoDoReciboDeTramite }
     *     
     */
    public ConteudoDoReciboDeTramite getConteudoDoReciboDeTramite() {
        return conteudoDoReciboDeTramite;
    }

    /**
     * Sets the value of the conteudoDoReciboDeTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConteudoDoReciboDeTramite }
     *     
     */
    public void setConteudoDoReciboDeTramite(ConteudoDoReciboDeTramite value) {
        this.conteudoDoReciboDeTramite = value;
    }

}
