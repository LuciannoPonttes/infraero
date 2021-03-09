
package br.gov.infraero.siga.pen.client;

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
 *         Representa um documento avulso, a ser tramitado
 *         para um ou mais órgãos de destino.
 *       
 * 
 * <p>Java class for documentoAvulso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentoAvulso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *         &lt;element name="nivelDeSigilo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nivelDeSigilo"/>
 *         &lt;element name="hipoteseLegal" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}fundamentoLegal" minOccurs="0"/>
 *         &lt;element name="protocoloRelacionado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="produtor" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}produtor"/>
 *         &lt;element name="descricao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}descricao"/>
 *         &lt;element name="dataHoraDeProducao" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataHoraDeRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="especie" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}especie"/>
 *         &lt;element name="identificacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}identificacaoDoDocumento" minOccurs="0"/>
 *         &lt;element name="componenteDigital" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}componenteDigital" maxOccurs="unbounded"/>
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
@XmlType(name = "documentoAvulso", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/documento", propOrder = {
    "protocolo",
    "nivelDeSigilo",
    "hipoteseLegal",
    "protocoloRelacionado",
    "produtor",
    "descricao",
    "dataHoraDeProducao",
    "dataHoraDeRegistro",
    "especie",
    "identificacao",
    "componenteDigital",
    "protocoloAnterior",
    "historico",
    "interessado"
})
public class DocumentoAvulso {

    @XmlElement(required = true)
    protected String protocolo;
    @XmlElement(required = true)
    protected BigInteger nivelDeSigilo;
    protected FundamentoLegal hipoteseLegal;
    protected List<String> protocoloRelacionado;
    @XmlElement(required = true)
    protected Produtor produtor;
    @XmlElement(required = true)
    protected String descricao;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHoraDeProducao;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dataHoraDeRegistro;
    @XmlElement(required = true)
    protected Especie especie;
    protected IdentificacaoDoDocumento identificacao;
    @XmlElement(required = true)
    protected List<ComponenteDigital> componenteDigital;
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
     * @return
     *     possible object is
     *     {@link FundamentoLegal }
     *     
     */
    public FundamentoLegal getHipoteseLegal() {
        return hipoteseLegal;
    }

    /**
     * Sets the value of the hipoteseLegal property.
     * 
     * @param value
     *     allowed object is
     *     {@link FundamentoLegal }
     *     
     */
    public void setHipoteseLegal(FundamentoLegal value) {
        this.hipoteseLegal = value;
    }

    /**
     * Gets the value of the protocoloRelacionado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the protocoloRelacionado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProtocoloRelacionado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProtocoloRelacionado() {
        if (protocoloRelacionado == null) {
            protocoloRelacionado = new ArrayList<String>();
        }
        return this.protocoloRelacionado;
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
     * Gets the value of the especie property.
     * 
     * @return
     *     possible object is
     *     {@link Especie }
     *     
     */
    public Especie getEspecie() {
        return especie;
    }

    /**
     * Sets the value of the especie property.
     * 
     * @param value
     *     allowed object is
     *     {@link Especie }
     *     
     */
    public void setEspecie(Especie value) {
        this.especie = value;
    }

    /**
     * Gets the value of the identificacao property.
     * 
     * @return
     *     possible object is
     *     {@link IdentificacaoDoDocumento }
     *     
     */
    public IdentificacaoDoDocumento getIdentificacao() {
        return identificacao;
    }

    /**
     * Sets the value of the identificacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificacaoDoDocumento }
     *     
     */
    public void setIdentificacao(IdentificacaoDoDocumento value) {
        this.identificacao = value;
    }

    /**
     * Gets the value of the componenteDigital property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componenteDigital property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponenteDigital().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteDigital }
     * 
     * 
     */
    public List<ComponenteDigital> getComponenteDigital() {
        if (componenteDigital == null) {
            componenteDigital = new ArrayList<ComponenteDigital>();
        }
        return this.componenteDigital;
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
