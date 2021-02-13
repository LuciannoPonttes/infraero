
package br.gov.infraero.siga.pen.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Lista de todas as operações que ocorreram
 *         no artefato relacionado. Cada operação é
 *         retratada através de seu código, data e
 *         complemento, se aplicável.
 *       
 * 
 * <p>Java class for historico complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="historico">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemHistorico" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}itemHistorico" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "historico", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "itemHistorico"
})
public class Historico {

    protected List<ItemHistorico> itemHistorico;

    /**
     * Gets the value of the itemHistorico property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the itemHistorico property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemHistorico().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ItemHistorico }
     * 
     * 
     */
    public List<ItemHistorico> getItemHistorico() {
        if (itemHistorico == null) {
            itemHistorico = new ArrayList<ItemHistorico>();
        }
        return this.itemHistorico;
    }

}
