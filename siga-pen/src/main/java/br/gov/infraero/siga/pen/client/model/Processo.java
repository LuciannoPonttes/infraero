
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
 *         Processo administrativo. Objeto que pode ser
 *         tramitado pelo serviço. Encapsula todos os
 *         dados estruturados de acordo com este modelo.
 *       
 * 
 * <p>Java class for processo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="processo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *         &lt;element name="nivelDeSigilo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nivelDeSigilo"/>
 *         &lt;element name="hipoteseLegal" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}fundamentoLegal" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="produtor" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}produtor"/>
 *         &lt;element name="processoDeNegocio" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}processoDeNegocio" minOccurs="0"/>
 *         &lt;element name="descricao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}descricao"/>
 *         &lt;element name="dataHoraDeProducao" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataHoraDeRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataHoraDeEncerramento" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="processoApensado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/processo}processo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documento" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/documento}documentoDoProcesso" maxOccurs="unbounded"/>
 *         &lt;element name="protocoloAnterior" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocoloAnterior" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="historico" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}historico" minOccurs="0"/>
 *         &lt;element name="interessado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}interessado" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "processo", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/processo", propOrder = {
    "protocolo",
    "nivelDeSigilo",
    "hipoteseLegal",
    "produtor",
    "processoDeNegocio",
    "descricao",
    "dataHoraDeProducao",
    "dataHoraDeRegistro",
    "dataHoraDeEncerramento",
    "processoApensado",
    "documento",
    "protocoloAnterior",
    "historico",
    "interessado"
})
public class Processo {

    @XmlElement(required = true)
    protected String protocolo;
    @XmlElement(required = true)
    protected BigInteger nivelDeSigilo;
    protected List<FundamentoLegal> hipoteseLegal;
    @XmlElement(required = true)
    protected Produtor produtor;
    protected String processoDeNegocio;
    @XmlElement(required = true)
    protected String descricao;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHoraDeProducao;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHoraDeRegistro;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHoraDeEncerramento;
    protected List<Processo> processoApensado;
    @XmlElement(required = true)
    protected List<DocumentoDoProcesso> documento;
    protected List<ProtocoloAnterior> protocoloAnterior;
    protected Historico historico;
    @XmlElement(required = true)
    protected List<Interessado> interessado;

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
     * Gets the value of the nivelDeSigilo property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNivelDeSigilo() {
        return nivelDeSigilo;
    }

    /**
     * Sets the value of the nivelDeSigilo property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNivelDeSigilo(BigInteger value) {
        this.nivelDeSigilo = value;
    }

    /**
     * Gets the value of the hipoteseLegal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hipoteseLegal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHipoteseLegal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FundamentoLegal }
     * 
     * 
     */
    public List<FundamentoLegal> getHipoteseLegal() {
        if (hipoteseLegal == null) {
            hipoteseLegal = new ArrayList<FundamentoLegal>();
        }
        return this.hipoteseLegal;
    }

    /**
     * Gets the value of the produtor property.
     * 
     * @return
     *     possible object is
     *     {@link Produtor }
     *     
     */
    public Produtor getProdutor() {
        return produtor;
    }

    /**
     * Sets the value of the produtor property.
     * 
     * @param value
     *     allowed object is
     *     {@link Produtor }
     *     
     */
    public void setProdutor(Produtor value) {
        this.produtor = value;
    }

    /**
     * Gets the value of the processoDeNegocio property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProcessoDeNegocio() {
        return processoDeNegocio;
    }

    /**
     * Sets the value of the processoDeNegocio property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProcessoDeNegocio(String value) {
        this.processoDeNegocio = value;
    }

    /**
     * Gets the value of the descricao property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Sets the value of the descricao property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescricao(String value) {
        this.descricao = value;
    }

    /**
     * Gets the value of the dataHoraDeProducao property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataHoraDeProducao() {
        return dataHoraDeProducao;
    }

    /**
     * Sets the value of the dataHoraDeProducao property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataHoraDeProducao(XMLGregorianCalendar value) {
        this.dataHoraDeProducao = value;
    }

    /**
     * Gets the value of the dataHoraDeRegistro property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataHoraDeRegistro() {
        return dataHoraDeRegistro;
    }

    /**
     * Sets the value of the dataHoraDeRegistro property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataHoraDeRegistro(XMLGregorianCalendar value) {
        this.dataHoraDeRegistro = value;
    }

    /**
     * Gets the value of the dataHoraDeEncerramento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataHoraDeEncerramento() {
        return dataHoraDeEncerramento;
    }

    /**
     * Sets the value of the dataHoraDeEncerramento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataHoraDeEncerramento(XMLGregorianCalendar value) {
        this.dataHoraDeEncerramento = value;
    }

    /**
     * Gets the value of the processoApensado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the processoApensado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProcessoApensado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Processo }
     * 
     * 
     */
    public List<Processo> getProcessoApensado() {
        if (processoApensado == null) {
            processoApensado = new ArrayList<Processo>();
        }
        return this.processoApensado;
    }

    /**
     * Gets the value of the documento property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documento property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumento().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoDoProcesso }
     * 
     * 
     */
    public List<DocumentoDoProcesso> getDocumento() {
        if (documento == null) {
            documento = new ArrayList<DocumentoDoProcesso>();
        }
        return this.documento;
    }

    /**
     * Gets the value of the protocoloAnterior property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the protocoloAnterior property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProtocoloAnterior().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProtocoloAnterior }
     * 
     * 
     */
    public List<ProtocoloAnterior> getProtocoloAnterior() {
        if (protocoloAnterior == null) {
            protocoloAnterior = new ArrayList<ProtocoloAnterior>();
        }
        return this.protocoloAnterior;
    }

    /**
     * Gets the value of the historico property.
     * 
     * @return
     *     possible object is
     *     {@link Historico }
     *     
     */
    public Historico getHistorico() {
        return historico;
    }

    /**
     * Sets the value of the historico property.
     * 
     * @param value
     *     allowed object is
     *     {@link Historico }
     *     
     */
    public void setHistorico(Historico value) {
        this.historico = value;
    }

    /**
     * Gets the value of the interessado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the interessado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInteressado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Interessado }
     * 
     * 
     */
    public List<Interessado> getInteressado() {
        if (interessado == null) {
            interessado = new ArrayList<Interessado>();
        }
        return this.interessado;
    }

}
