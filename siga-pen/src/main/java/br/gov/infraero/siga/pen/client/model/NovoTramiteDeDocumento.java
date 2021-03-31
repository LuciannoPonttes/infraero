
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
 *         Dados para criação de um novo trâmite de documento
 *         avulso para um ou mais destinatários. Utilizado 
 *         no serviço de envio de documento.
 *       
 * 
 * <p>Java class for novoTramiteDeDocumento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="novoTramiteDeDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cabecalho">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="remetente" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional"/>
 *                   &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" maxOccurs="unbounded"/>
 *                   &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE" minOccurs="0"/>
 *                   &lt;element name="urgente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="dataDeRetorno" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="motivoDaUrgencia" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}motivoDaUrgencia" minOccurs="0"/>
 *                   &lt;element name="propriedadeAdicional" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}propriedadeAdicional" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="documento" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/documento}documentoAvulso"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "novoTramiteDeDocumento", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "cabecalho",
    "documento"
})
public class NovoTramiteDeDocumento {

    @XmlElement(required = true)
    protected NovoTramiteDeDocumento.Cabecalho cabecalho;
    @XmlElement(required = true)
    protected DocumentoAvulso documento;

    /**
     * Gets the value of the cabecalho property.
     * 
     * @return
     *     possible object is
     *     {@link NovoTramiteDeDocumento.Cabecalho }
     *     
     */
    public NovoTramiteDeDocumento.Cabecalho getCabecalho() {
        return cabecalho;
    }

    /**
     * Sets the value of the cabecalho property.
     * 
     * @param value
     *     allowed object is
     *     {@link NovoTramiteDeDocumento.Cabecalho }
     *     
     */
    public void setCabecalho(NovoTramiteDeDocumento.Cabecalho value) {
        this.cabecalho = value;
    }

    /**
     * Gets the value of the documento property.
     * 
     * @return
     *     possible object is
     *     {@link DocumentoAvulso }
     *     
     */
    public DocumentoAvulso getDocumento() {
        return documento;
    }

    /**
     * Sets the value of the documento property.
     * 
     * @param value
     *     allowed object is
     *     {@link DocumentoAvulso }
     *     
     */
    public void setDocumento(DocumentoAvulso value) {
        this.documento = value;
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
     *         &lt;element name="remetente" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional"/>
     *         &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" maxOccurs="unbounded"/>
     *         &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE" minOccurs="0"/>
     *         &lt;element name="urgente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="dataDeRetorno" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="motivoDaUrgencia" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}motivoDaUrgencia" minOccurs="0"/>
     *         &lt;element name="propriedadeAdicional" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}propriedadeAdicional" maxOccurs="unbounded" minOccurs="0"/>
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
        "remetente",
        "destinatario",
        "nre",
        "urgente",
        "dataDeRetorno",
        "motivoDaUrgencia",
        "propriedadeAdicional"
    })
    public static class Cabecalho {

        @XmlElement(required = true)
        protected EstruturaOrganizacional remetente;
        @XmlElement(required = true)
        protected List<EstruturaOrganizacional> destinatario;
        @XmlElement(name = "NRE")
        protected String nre;
        protected Boolean urgente;
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dataDeRetorno;
        protected String motivoDaUrgencia;
        protected List<PropriedadeAdicional> propriedadeAdicional;

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
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the destinatario property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDestinatario().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link EstruturaOrganizacional }
         * 
         * 
         */
        public List<EstruturaOrganizacional> getDestinatario() {
            if (destinatario == null) {
                destinatario = new ArrayList<EstruturaOrganizacional>();
            }
            return this.destinatario;
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
         * Gets the value of the urgente property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isUrgente() {
            return urgente;
        }

        /**
         * Sets the value of the urgente property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setUrgente(Boolean value) {
            this.urgente = value;
        }

        /**
         * Gets the value of the dataDeRetorno property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataDeRetorno() {
            return dataDeRetorno;
        }

        /**
         * Sets the value of the dataDeRetorno property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataDeRetorno(XMLGregorianCalendar value) {
            this.dataDeRetorno = value;
        }

        /**
         * Gets the value of the motivoDaUrgencia property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMotivoDaUrgencia() {
            return motivoDaUrgencia;
        }

        /**
         * Sets the value of the motivoDaUrgencia property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMotivoDaUrgencia(String value) {
            this.motivoDaUrgencia = value;
        }

        /**
         * Gets the value of the propriedadeAdicional property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the propriedadeAdicional property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPropriedadeAdicional().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link PropriedadeAdicional }
         * 
         * 
         */
        public List<PropriedadeAdicional> getPropriedadeAdicional() {
            if (propriedadeAdicional == null) {
                propriedadeAdicional = new ArrayList<PropriedadeAdicional>();
            }
            return this.propriedadeAdicional;
        }

    }

}
