
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
 *         &lt;element name="repositoriosEncontrados" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas}repositoriosEncontrados"/>
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
    "repositoriosEncontrados"
})
@XmlRootElement(name = "consultarRepositoriosDeEstruturasResponse")
public class ConsultarRepositoriosDeEstruturasResponse {

    @XmlElement(required = true)
    protected RepositoriosEncontrados repositoriosEncontrados;

    /**
     * Gets the value of the repositoriosEncontrados property.
     * 
     * @return
     *     possible object is
     *     {@link RepositoriosEncontrados }
     *     
     */
    public RepositoriosEncontrados getRepositoriosEncontrados() {
        return repositoriosEncontrados;
    }

    /**
     * Sets the value of the repositoriosEncontrados property.
     * 
     * @param value
     *     allowed object is
     *     {@link RepositoriosEncontrados }
     *     
     */
    public void setRepositoriosEncontrados(RepositoriosEncontrados value) {
        this.repositoriosEncontrados = value;
    }

}
