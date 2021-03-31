
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
 *         &lt;element name="dadosTramiteDeProcessoCriado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosTramiteDeProcessoCriado"/>
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
    "dadosTramiteDeProcessoCriado"
})
@XmlRootElement(name = "enviarProcessoResponse")
public class EnviarProcessoResponse {

    @XmlElement(required = true)
    protected DadosTramiteDeProcessoCriado dadosTramiteDeProcessoCriado;

    /**
     * Gets the value of the dadosTramiteDeProcessoCriado property.
     * 
     * @return
     *     possible object is
     *     {@link DadosTramiteDeProcessoCriado }
     *     
     */
    public DadosTramiteDeProcessoCriado getDadosTramiteDeProcessoCriado() {
        return dadosTramiteDeProcessoCriado;
    }

    /**
     * Sets the value of the dadosTramiteDeProcessoCriado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosTramiteDeProcessoCriado }
     *     
     */
    public void setDadosTramiteDeProcessoCriado(DadosTramiteDeProcessoCriado value) {
        this.dadosTramiteDeProcessoCriado = value;
    }

}
