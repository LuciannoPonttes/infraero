
package br.gov.infraero.siga.pen.client.model;

import java.math.BigInteger;
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
 *         Objeto usado na resposta da consulta de trâmites.
 *       
 * 
 * <p>Java class for tramitesEncontrados complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tramitesEncontrados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="tramite" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
 *                   &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE"/>
 *                   &lt;element name="remetente" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
 *                   &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
 *                   &lt;element name="situacaoAtual" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *                   &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *                   &lt;element name="justificativaDaRecusa" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}justificativaDaRecusaDeTramite" minOccurs="0"/>
 *                   &lt;element name="motivoDaRecusa" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}motivoDaRecusaDeTramite" minOccurs="0"/>
 *                   &lt;element name="historico" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="operacao" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="situacao" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                                       &lt;element name="dataHora" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="componenteDigitalPendenteDeEnvio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="componenteDigitalPendenteDeRecebimento" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="totalDeRegistros" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tramitesEncontrados", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "tramite",
    "totalDeRegistros"
})
public class TramitesEncontrados {

    protected List<TramitesEncontrados.Tramite> tramite;
    protected long totalDeRegistros;

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
     * {@link TramitesEncontrados.Tramite }
     * 
     * 
     */
    public List<TramitesEncontrados.Tramite> getTramite() {
        if (tramite == null) {
            tramite = new ArrayList<TramitesEncontrados.Tramite>();
        }
        return this.tramite;
    }

    /**
     * Gets the value of the totalDeRegistros property.
     * 
     */
    public long getTotalDeRegistros() {
        return totalDeRegistros;
    }

    /**
     * Sets the value of the totalDeRegistros property.
     * 
     */
    public void setTotalDeRegistros(long value) {
        this.totalDeRegistros = value;
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
     *         &lt;element name="remetente" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
     *         &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
     *         &lt;element name="situacaoAtual" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
     *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
     *         &lt;element name="justificativaDaRecusa" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}justificativaDaRecusaDeTramite" minOccurs="0"/>
     *         &lt;element name="motivoDaRecusa" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}motivoDaRecusaDeTramite" minOccurs="0"/>
     *         &lt;element name="historico" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="operacao" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="situacao" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *                             &lt;element name="dataHora" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
     *         &lt;element name="componenteDigitalPendenteDeEnvio" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="componenteDigitalPendenteDeRecebimento" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "remetente",
        "destinatario",
        "situacaoAtual",
        "protocolo",
        "justificativaDaRecusa",
        "motivoDaRecusa",
        "historico",
        "componenteDigitalPendenteDeEnvio",
        "componenteDigitalPendenteDeRecebimento"
    })
    public static class Tramite {

        @XmlElement(name = "IDT")
        protected long idt;
        @XmlElement(name = "NRE", required = true)
        protected String nre;
        protected EstruturaOrganizacional remetente;
        protected EstruturaOrganizacional destinatario;
        protected BigInteger situacaoAtual;
        @XmlElement(required = true)
        protected String protocolo;
        protected String justificativaDaRecusa;
        protected String motivoDaRecusa;
        protected TramitesEncontrados.Tramite.Historico historico;
        protected List<String> componenteDigitalPendenteDeEnvio;
        protected List<String> componenteDigitalPendenteDeRecebimento;

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
         * Gets the value of the remetente property.
         * 
         * @return
         *     possible object is
         *     {@link EstruturaOrganizacional }
         *     
         */
        public EstruturaOrganizacional getRemetente() {
            return remetente;
        }

        /**
         * Sets the value of the remetente property.
         * 
         * @param value
         *     allowed object is
         *     {@link EstruturaOrganizacional }
         *     
         */
        public void setRemetente(EstruturaOrganizacional value) {
            this.remetente = value;
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

        /**
         * Gets the value of the situacaoAtual property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getSituacaoAtual() {
            return situacaoAtual;
        }

        /**
         * Sets the value of the situacaoAtual property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setSituacaoAtual(BigInteger value) {
            this.situacaoAtual = value;
        }

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
         * Gets the value of the justificativaDaRecusa property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJustificativaDaRecusa() {
            return justificativaDaRecusa;
        }

        /**
         * Sets the value of the justificativaDaRecusa property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJustificativaDaRecusa(String value) {
            this.justificativaDaRecusa = value;
        }

        /**
         * Gets the value of the motivoDaRecusa property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMotivoDaRecusa() {
            return motivoDaRecusa;
        }

        /**
         * Sets the value of the motivoDaRecusa property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMotivoDaRecusa(String value) {
            this.motivoDaRecusa = value;
        }

        /**
         * Gets the value of the historico property.
         * 
         * @return
         *     possible object is
         *     {@link TramitesEncontrados.Tramite.Historico }
         *     
         */
        public TramitesEncontrados.Tramite.Historico getHistorico() {
            return historico;
        }

        /**
         * Sets the value of the historico property.
         * 
         * @param value
         *     allowed object is
         *     {@link TramitesEncontrados.Tramite.Historico }
         *     
         */
        public void setHistorico(TramitesEncontrados.Tramite.Historico value) {
            this.historico = value;
        }

        /**
         * Gets the value of the componenteDigitalPendenteDeEnvio property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the componenteDigitalPendenteDeEnvio property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComponenteDigitalPendenteDeEnvio().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getComponenteDigitalPendenteDeEnvio() {
            if (componenteDigitalPendenteDeEnvio == null) {
                componenteDigitalPendenteDeEnvio = new ArrayList<String>();
            }
            return this.componenteDigitalPendenteDeEnvio;
        }

        /**
         * Gets the value of the componenteDigitalPendenteDeRecebimento property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the componenteDigitalPendenteDeRecebimento property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComponenteDigitalPendenteDeRecebimento().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getComponenteDigitalPendenteDeRecebimento() {
            if (componenteDigitalPendenteDeRecebimento == null) {
                componenteDigitalPendenteDeRecebimento = new ArrayList<String>();
            }
            return this.componenteDigitalPendenteDeRecebimento;
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
         *         &lt;element name="operacao" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="situacao" type="{http://www.w3.org/2001/XMLSchema}integer"/>
         *                   &lt;element name="dataHora" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
            "operacao"
        })
        public static class Historico {

            protected List<TramitesEncontrados.Tramite.Historico.Operacao> operacao;

            /**
             * Gets the value of the operacao property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the operacao property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getOperacao().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link TramitesEncontrados.Tramite.Historico.Operacao }
             * 
             * 
             */
            public List<TramitesEncontrados.Tramite.Historico.Operacao> getOperacao() {
                if (operacao == null) {
                    operacao = new ArrayList<TramitesEncontrados.Tramite.Historico.Operacao>();
                }
                return this.operacao;
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
             *         &lt;element name="situacao" type="{http://www.w3.org/2001/XMLSchema}integer"/>
             *         &lt;element name="dataHora" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
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
                "situacao",
                "dataHora"
            })
            public static class Operacao {

                @XmlElement(required = true)
                protected BigInteger situacao;
                @XmlElement(required = true)
                @XmlSchemaType(name = "dateTime")
                protected XMLGregorianCalendar dataHora;

                /**
                 * Gets the value of the situacao property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link BigInteger }
                 *     
                 */
                public BigInteger getSituacao() {
                    return situacao;
                }

                /**
                 * Sets the value of the situacao property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link BigInteger }
                 *     
                 */
                public void setSituacao(BigInteger value) {
                    this.situacao = value;
                }

                /**
                 * Gets the value of the dataHora property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public XMLGregorianCalendar getDataHora() {
                    return dataHora;
                }

                /**
                 * Sets the value of the dataHora property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link XMLGregorianCalendar }
                 *     
                 */
                public void setDataHora(XMLGregorianCalendar value) {
                    this.dataHora = value;
                }

            }

        }

    }

}
