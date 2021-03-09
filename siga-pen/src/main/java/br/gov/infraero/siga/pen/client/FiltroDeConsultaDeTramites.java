
package br.gov.infraero.siga.pen.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Usado para filtrar os trâmites buscados
 *         no serviço correspondente.
 *       
 * 
 * <p>Java class for filtroDeConsultaDeTramites complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroDeConsultaDeTramites">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT" minOccurs="0"/>
 *         &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE" minOccurs="0"/>
 *         &lt;element name="remetente" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
 *         &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
 *         &lt;element name="situacaoAtual" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="periodo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}periodo" minOccurs="0"/>
 *         &lt;element name="nivelDeSigilo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nivelDeSigilo" minOccurs="0"/>
 *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo" minOccurs="0"/>
 *         &lt;element name="paginacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}paginacao" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroDeConsultaDeTramites", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "idt",
    "nre",
    "remetente",
    "destinatario",
    "situacaoAtual",
    "periodo",
    "nivelDeSigilo",
    "protocolo",
    "paginacao"
})
public class FiltroDeConsultaDeTramites {

    @XmlElement(name = "IDT")
    protected Long idt;
    @XmlElement(name = "NRE")
    protected String nre;
    protected EstruturaOrganizacional remetente;
    protected EstruturaOrganizacional destinatario;
    protected BigInteger situacaoAtual;
    protected Periodo periodo;
    protected BigInteger nivelDeSigilo;
    protected String protocolo;
    protected Paginacao paginacao;

    /**
     * Gets the value of the idt property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIDT() {
        return idt;
    }

    /**
     * Sets the value of the idt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIDT(Long value) {
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
     * Gets the value of the periodo property.
     * 
     * @return
     *     possible object is
     *     {@link Periodo }
     *     
     */
    public Periodo getPeriodo() {
        return periodo;
    }

    /**
     * Sets the value of the periodo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Periodo }
     *     
     */
    public void setPeriodo(Periodo value) {
        this.periodo = value;
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
     * Gets the value of the paginacao property.
     * 
     * @return
     *     possible object is
     *     {@link Paginacao }
     *     
     */
    public Paginacao getPaginacao() {
        return paginacao;
    }

    /**
     * Sets the value of the paginacao property.
     * 
     * @param value
     *     allowed object is
     *     {@link Paginacao }
     *     
     */
    public void setPaginacao(Paginacao value) {
        this.paginacao = value;
    }

}
