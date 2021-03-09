
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
 *         &lt;element name="filtroDePendencias" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}filtroDePendencias"/>
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
    "filtroDePendencias"
})
@XmlRootElement(name = "listarPendencias")
public class ListarPendencias {

    @XmlElement(required = true)
    protected FiltroDePendencias filtroDePendencias;

    /**
     * Gets the value of the filtroDePendencias property.
     * 
     * @return
     *     possible object is
     *     {@link FiltroDePendencias }
     *     
     */
    public FiltroDePendencias getFiltroDePendencias() {
        return filtroDePendencias;
    }

    /**
     * Sets the value of the filtroDePendencias property.
     * 
     * @param value
     *     allowed object is
     *     {@link FiltroDePendencias }
     *     
     */
    public void setFiltroDePendencias(FiltroDePendencias value) {
        this.filtroDePendencias = value;
    }

}
