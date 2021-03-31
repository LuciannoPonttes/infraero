
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         Contém informações que o destinatário produz
 *         e envia para conclusão do trâmite. Essas informações
 *         são disponibilizadas para o remetente.
 *       
 * 
 * <p>Java class for dadosDoReciboDeTramite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosDoReciboDeTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
 *         &lt;element name="dataDeRecebimento" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/recibo}dataDeRecebimento"/>
 *         &lt;element name="hashDaAssinatura" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosDoReciboDeTramite", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "idt",
    "dataDeRecebimento",
    "hashDaAssinatura"
})
public class DadosDoReciboDeTramite {

    @XmlElement(name = "IDT")
    protected long idt;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataDeRecebimento;
    @XmlElement(required = true)
    protected String hashDaAssinatura;

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
     * Gets the value of the hashDaAssinatura property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashDaAssinatura() {
        return hashDaAssinatura;
    }

    /**
     * Sets the value of the hashDaAssinatura property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashDaAssinatura(String value) {
        this.hashDaAssinatura = value;
    }

}
