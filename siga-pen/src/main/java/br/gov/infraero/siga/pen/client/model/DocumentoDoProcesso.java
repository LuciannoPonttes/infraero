
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
 *         Representa uma peça de um determinado
 *         processo. Possui metadados próprios e uma
 *         coleção de componentes digitais.
 *       
 * 
 * <p>Java class for documentoDoProcesso complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentoDoProcesso">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="protocoloDoDocumentoAnexado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo" minOccurs="0"/>
 *         &lt;element name="protocoloDoProcessoAnexado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo" minOccurs="0"/>
 *         &lt;element name="retirado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ordem" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="ordemDoDocumentoReferenciado" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="nivelDeSigilo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nivelDeSigilo"/>
 *         &lt;element name="hipoteseLegal" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}fundamentoLegal" minOccurs="0"/>
 *         &lt;element name="volume" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}volume" minOccurs="0"/>
 *         &lt;element name="produtor" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}produtor"/>
 *         &lt;element name="descricao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}descricao"/>
 *         &lt;element name="dataHoraDeProducao" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="dataHoraDeRegistro" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="especie" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}especie"/>
 *         &lt;element name="identificacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}identificacaoDoDocumento" minOccurs="0"/>
 *         &lt;element name="componenteDigital" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}componenteDigital" maxOccurs="unbounded"/>
 *         &lt;element name="protocoloAnterior" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocoloAnterior" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="historico" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}historico" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoDoProcesso", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/documento", propOrder = {
    "protocoloDoDocumentoAnexado",
    "protocoloDoProcessoAnexado",
    "retirado",
    "ordem",
    "ordemDoDocumentoReferenciado",
    "nivelDeSigilo",
    "hipoteseLegal",
    "volume",
    "produtor",
    "descricao",
    "dataHoraDeProducao",
    "dataHoraDeRegistro",
    "especie",
    "identificacao",
    "componenteDigital",
    "protocoloAnterior",
    "historico"
})
public class DocumentoDoProcesso {

    protected String protocoloDoDocumentoAnexado;
    protected String protocoloDoProcessoAnexado;
    protected Boolean retirado;
    @XmlElement(required = true)
    protected BigInteger ordem;
    protected List<BigInteger> ordemDoDocumentoReferenciado;
    @XmlElement(required = true)
    protected BigInteger nivelDeSigilo;
    protected FundamentoLegal hipoteseLegal;
    protected String volume;
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

    /**
     * Gets the value of the protocoloDoDocumentoAnexado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocoloDoDocumentoAnexado() {
        return protocoloDoDocumentoAnexado;
    }

    /**
     * Sets the value of the protocoloDoDocumentoAnexado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocoloDoDocumentoAnexado(String value) {
        this.protocoloDoDocumentoAnexado = value;
    }

    /**
     * Gets the value of the protocoloDoProcessoAnexado property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProtocoloDoProcessoAnexado() {
        return protocoloDoProcessoAnexado;
    }

    /**
     * Sets the value of the protocoloDoProcessoAnexado property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProtocoloDoProcessoAnexado(String value) {
        this.protocoloDoProcessoAnexado = value;
    }

    /**
     * Gets the value of the retirado property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRetirado() {
        return retirado;
    }

    /**
     * Sets the value of the retirado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRetirado(Boolean value) {
        this.retirado = value;
    }

    /**
     * Gets the value of the ordem property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrdem() {
        return ordem;
    }

    /**
     * Sets the value of the ordem property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrdem(BigInteger value) {
        this.ordem = value;
    }

    /**
     * Gets the value of the ordemDoDocumentoReferenciado property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ordemDoDocumentoReferenciado property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrdemDoDocumentoReferenciado().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getOrdemDoDocumentoReferenciado() {
        if (ordemDoDocumentoReferenciado == null) {
            ordemDoDocumentoReferenciado = new ArrayList<BigInteger>();
        }
        return this.ordemDoDocumentoReferenciado;
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
     * Gets the value of the volume property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolume() {
        return volume;
    }

    /**
     * Sets the value of the volume property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolume(String value) {
        this.volume = value;
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

}
