
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
 *         &lt;element name="listaDePendencias" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}listaDePendencias"/>
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
    "listaDePendencias"
})
@XmlRootElement(name = "listarPendenciasResponse")
public class ListarPendenciasResponse {

    @XmlElement(required = true)
    protected ListaDePendencias listaDePendencias;

    /**
     * Gets the value of the listaDePendencias property.
     * 
     * @return
     *     possible object is
     *     {@link ListaDePendencias }
     *     
     */
    public ListaDePendencias getListaDePendencias() {
        return listaDePendencias;
    }

    /**
     * Sets the value of the listaDePendencias property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaDePendencias }
     *     
     */
    public void setListaDePendencias(ListaDePendencias value) {
        this.listaDePendencias = value;
    }

}
