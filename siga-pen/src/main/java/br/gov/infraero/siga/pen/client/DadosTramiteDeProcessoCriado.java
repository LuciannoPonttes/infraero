
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
 * 
 *         Dados do trâmite que foi criado. É usado no
 *         retorno do serviço que permite o envio
 *         de um processo administrativo para outro órgão.
 *       
 * 
 * <p>Java class for dadosTramiteDeProcessoCriado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosTramiteDeProcessoCriado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
 *         &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE" minOccurs="0"/>
 *         &lt;element name="ticketParaEnvioDeComponentesDigitais" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}ticketParaEnvioDeComponentesDigitais"/>
 *         &lt;element name="dataHoraDeRegistroDoTramite" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="componentesDigitaisSolicitados">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="processo" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *                             &lt;element name="hash" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosTramiteDeProcessoCriado", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "idt",
    "nre",
    "ticketParaEnvioDeComponentesDigitais",
    "dataHoraDeRegistroDoTramite",
    "componentesDigitaisSolicitados"
})
public class DadosTramiteDeProcessoCriado {

    @XmlElement(name = "IDT")
    protected long idt;
    @XmlElement(name = "NRE")
    protected String nre;
    protected long ticketParaEnvioDeComponentesDigitais;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHoraDeRegistroDoTramite;
    @XmlElement(required = true)
    protected DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados componentesDigitaisSolicitados;

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
     * Gets the value of the componentesDigitaisSolicitados property.
     * 
     * @return
     *     possible object is
     *     {@link DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados }
     *     
     */
    public DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados getComponentesDigitaisSolicitados() {
        return componentesDigitaisSolicitados;
    }

    /**
     * Sets the value of the componentesDigitaisSolicitados property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados }
     *     
     */
    public void setComponentesDigitaisSolicitados(DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados value) {
        this.componentesDigitaisSolicitados = value;
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
     *         &lt;element name="processo" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
     *                   &lt;element name="hash" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
        "processo"
    })
    public static class ComponentesDigitaisSolicitados {

        protected List<DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados.Processo> processo;

        /**
         * Gets the value of the processo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the processo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProcesso().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados.Processo }
         * 
         * 
         */
        public List<DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados.Processo> getProcesso() {
            if (processo == null) {
                processo = new ArrayList<DadosTramiteDeProcessoCriado.ComponentesDigitaisSolicitados.Processo>();
            }
            return this.processo;
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
         *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
         *         &lt;element name="hash" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
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
            "protocolo",
            "hash"
        })
        public static class Processo {

            @XmlElement(required = true)
            protected String protocolo;
            @XmlElement(required = true)
            protected List<String> hash;

            /**
             * Gets the value of the protocolo property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getProtocolo() {
                return protocolo;
            }

            /**
             * Sets the value of the protocolo property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setProtocolo(String value) {
                this.protocolo = value;
            }

            /**
             * Gets the value of the hash property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the hash property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getHash().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link String }
             * 
             * 
             */
            public List<String> getHash() {
                if (hash == null) {
                    hash = new ArrayList<String>();
                }
                return this.hash;
            }

        }

    }

}
