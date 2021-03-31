
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
 *         &lt;element name="dadosTramiteDeDocumentoCriado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosTramiteDeDocumentoCriado"/>
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
    "dadosTramiteDeDocumentoCriado"
})
@XmlRootElement(name = "enviarDocumentoResponse")
public class EnviarDocumentoResponse {

    @XmlElement(required = true)
    protected DadosTramiteDeDocumentoCriado dadosTramiteDeDocumentoCriado;

    /**
     * Gets the value of the dadosTramiteDeDocumentoCriado property.
     * 
     * @return
     *     possible object is
     *     {@link DadosTramiteDeDocumentoCriado }
     *     
     */
    public DadosTramiteDeDocumentoCriado getDadosTramiteDeDocumentoCriado() {
        return dadosTramiteDeDocumentoCriado;
    }

    /**
     * Sets the value of the dadosTramiteDeDocumentoCriado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosTramiteDeDocumentoCriado }
     *     
     */
    public void setDadosTramiteDeDocumentoCriado(DadosTramiteDeDocumentoCriado value) {
        this.dadosTramiteDeDocumentoCriado = value;
    }

}
