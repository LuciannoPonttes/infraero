
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
 *         &lt;element name="dadosDoTerminoDeEnvioDePartes" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosDoTerminoDeEnvioDePartes"/>
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
    "dadosDoTerminoDeEnvioDePartes"
})
@XmlRootElement(name = "sinalizarTerminoDeEnvioDasPartesDoComponente")
public class SinalizarTerminoDeEnvioDasPartesDoComponente {

    @XmlElement(required = true)
    protected DadosDoTerminoDeEnvioDePartes dadosDoTerminoDeEnvioDePartes;

    /**
     * Gets the value of the dadosDoTerminoDeEnvioDePartes property.
     * 
     * @return
     *     possible object is
     *     {@link DadosDoTerminoDeEnvioDePartes }
     *     
     */
    public DadosDoTerminoDeEnvioDePartes getDadosDoTerminoDeEnvioDePartes() {
        return dadosDoTerminoDeEnvioDePartes;
    }

    /**
     * Sets the value of the dadosDoTerminoDeEnvioDePartes property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosDoTerminoDeEnvioDePartes }
     *     
     */
    public void setDadosDoTerminoDeEnvioDePartes(DadosDoTerminoDeEnvioDePartes value) {
        this.dadosDoTerminoDeEnvioDePartes = value;
    }

}