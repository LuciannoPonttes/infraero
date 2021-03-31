
package br.gov.infraero.siga.pen.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         Dados do trâmite que foi criado. É usado no
 *         retorno do serviço que permite o envio
 *         de um documento avulso para um ou mais órgãos.
 *       
 * 
 * <p>Java class for dadosTramiteDeDocumentoCriado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosTramiteDeDocumentoCriado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tramite" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
 *                   &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE"/>
 *                   &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ticketParaEnvioDeComponentesDigitais" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}ticketParaEnvioDeComponentesDigitais"/>
 *         &lt;element name="dataHoraDeRegistroDoTramite" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosTramiteDeDocumentoCriado", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "tramite",
    "ticketParaEnvioDeComponentesDigitais",
    "dataHoraDeRegistroDoTramite"
})
public class DadosTramiteDeDocumentoCriado {

    @XmlElement(required = true)
    protected List<DadosTramiteDeDocumentoCriado.Tramite> tramite;
    protected long ticketParaEnvioDeComponentesDigitais;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHoraDeRegistroDoTramite;

    /**
     * Gets the value of the tramite property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tramite property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTramite().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DadosTramiteDeDocumentoCriado.Tramite }
     * 
     * 
     */
    public List<DadosTramiteDeDocumentoCriado.Tramite> getTramite() {
        if (tramite == null) {
            tramite = new ArrayList<DadosTramiteDeDocumentoCriado.Tramite>();
        }
        return this.tramite;
    }

    /**
     * Gets the value of the ticketParaEnvioDeComponentesDigitais property.
     * 
     */
    public long getTicketParaEnvioDeComponentesDigitais() {
        return ticketParaEnvioDeComponentesDigitais;
    }

    /**
     * Sets the value of the ticketParaEnvioDeComponentesDigitais property.
     * 
     */
    public void setTicketParaEnvioDeComponentesDigitais(long value) {
        this.ticketParaEnvioDeComponentesDigitais = value;
    }

    /**
     * Gets the value of the dataHoraDeRegistroDoTramite property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataHoraDeRegistroDoTramite() {
        return dataHoraDeRegistroDoTramite;
    }

    /**
     * Sets the value of the dataHoraDeRegistroDoTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataHoraDeRegistroDoTramite(XMLGregorianCalendar value) {
        this.dataHoraDeRegistroDoTramite = value;
    }


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
     *         &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
     *         &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE"/>
     *         &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional"/>
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
        "idt",
        "nre",
        "destinatario"
    })
    public static class Tramite {

        @XmlElement(name = "IDT")
        protected long idt;
        @XmlElement(name = "NRE", required = true)
        protected String nre;
        @XmlElement(required = true)
        protected EstruturaOrganizacional destinatario;

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
         * Gets the value of the destinatario property.
         * 
         * @return
         *     possible object is
         *     {@link EstruturaOrganizacional }
         *     
         */
        public EstruturaOrganizacional getDestinatario() {
            return destinatario;
        }

        /**
         * Sets the value of the destinatario property.
         * 
         * @param value
         *     allowed object is
         *     {@link EstruturaOrganizacional }
         *     
         */
        public void setDestinatario(EstruturaOrganizacional value) {
            this.destinatario = value;
        }

    }

}
