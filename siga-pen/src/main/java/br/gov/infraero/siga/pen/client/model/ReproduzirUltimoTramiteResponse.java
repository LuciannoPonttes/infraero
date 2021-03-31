
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
 *         &lt;element name="dadosDoTramiteDeReproducaoCriado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosDoTramiteDeReproducaoCriado"/>
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
    "dadosDoTramiteDeReproducaoCriado"
})
@XmlRootElement(name = "reproduzirUltimoTramiteResponse")
public class ReproduzirUltimoTramiteResponse {

    @XmlElement(required = true)
    protected DadosDoTramiteDeReproducaoCriado dadosDoTramiteDeReproducaoCriado;

    /**
     * Gets the value of the dadosDoTramiteDeReproducaoCriado property.
     * 
     * @return
     *     possible object is
     *     {@link DadosDoTramiteDeReproducaoCriado }
     *     
     */
    public DadosDoTramiteDeReproducaoCriado getDadosDoTramiteDeReproducaoCriado() {
        return dadosDoTramiteDeReproducaoCriado;
    }

    /**
     * Sets the value of the dadosDoTramiteDeReproducaoCriado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosDoTramiteDeReproducaoCriado }
     *     
     */
    public void setDadosDoTramiteDeReproducaoCriado(DadosDoTramiteDeReproducaoCriado value) {
        this.dadosDoTramiteDeReproducaoCriado = value;
    }

}
