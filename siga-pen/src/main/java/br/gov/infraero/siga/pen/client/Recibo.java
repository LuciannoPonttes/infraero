
package br.gov.infraero.siga.pen.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for recibo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recibo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
 *         &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE"/>
 *         &lt;element name="dataDeRecebimento" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/recibo}dataDeRecebimento"/>
 *         &lt;element name="hashDoComponenteDigital" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recibo", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/recibo", propOrder = {
    "idt",
    "nre",
    "dataDeRecebimento",
    "hashDoComponenteDigital"
})
public class Recibo {

    @XmlElement(name = "IDT")
    protected long idt;
    @XmlElement(name = "NRE", required = true)
    protected String nre;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDeRecebimento;
    protected List<String> hashDoComponenteDigital;

    /**
     * Gets the value of the idt property.
     * 
     */
    public long getIDT() {
        return idt;
    }

    /**
     * Sets the value of the idt property.
     * 
     */
    public void setIDT(long value) {
        this.idt = value;
    }

    /**
     * Gets the value of the nre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNRE() {
        return nre;
    }

    /**
     * Sets the value of the nre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNRE(String value) {
        this.nre = value;
    }

    /**
     * Gets the value of the dataDeRecebimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDeRecebimento() {
        return dataDeRecebimento;
    }

    /**
     * Sets the value of the dataDeRecebimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDeRecebimento(XMLGregorianCalendar value) {
        this.dataDeRecebimento = value;
    }

    /**
     * Gets the value of the hashDoComponenteDigital property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hashDoComponenteDigital property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHashDoComponenteDigital().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getHashDoComponenteDigital() {
        if (hashDoComponenteDigital == null) {
            hashDoComponenteDigital = new ArrayList<String>();
        }
        return this.hashDoComponenteDigital;
    }

}
