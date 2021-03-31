
package br.gov.infraero.siga.pen.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Contém os metadados do trâmite que são
 *         úteis para o destinatário.
 *       
 * 
 * <p>Java class for metadados complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="metadados">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="remetente" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional"/>
 *         &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional"/>
 *         &lt;element name="unidadeReceptora" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional" minOccurs="0"/>
 *         &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE"/>
 *         &lt;element name="urgente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="motivoDaUrgencia" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}motivoDaUrgencia" minOccurs="0"/>
 *         &lt;element name="reproducaoDeTramite" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="propriedadeAdicional" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}propriedadeAdicional" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;choice>
 *           &lt;element name="processo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/processo}processo"/>
 *           &lt;element name="documento" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/documento}documentoAvulso"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "metadados", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "remetente",
    "destinatario",
    "unidadeReceptora",
    "nre",
    "urgente",
    "motivoDaUrgencia",
    "reproducaoDeTramite",
    "propriedadeAdicional",
    "processo",
    "documento"
})
public class Metadados {

    @XmlElement(required = true)
    protected EstruturaOrganizacional remetente;
    @XmlElement(required = true)
    protected EstruturaOrganizacional destinatario;
    protected EstruturaOrganizacional unidadeReceptora;
    @XmlElement(name = "NRE", required = true)
    protected String nre;
    protected Boolean urgente;
    protected String motivoDaUrgencia;
    protected Boolean reproducaoDeTramite;
    protected List<PropriedadeAdicional> propriedadeAdicional;
    protected Processo processo;
    protected DocumentoAvulso documento;

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
     * Gets the value of the unidadeReceptora property.
     * 
     * @return
     *     possible object is
     *     {@link EstruturaOrganizacional }
     *     
     */
    public EstruturaOrganizacional getUnidadeReceptora() {
        return unidadeReceptora;
    }

    /**
     * Sets the value of the unidadeReceptora property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstruturaOrganizacional }
     *     
     */
    public void setUnidadeReceptora(EstruturaOrganizacional value) {
        this.unidadeReceptora = value;
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
     * Gets the value of the reproducaoDeTramite property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReproducaoDeTramite() {
        return reproducaoDeTramite;
    }

    /**
     * Sets the value of the reproducaoDeTramite property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReproducaoDeTramite(Boolean value) {
        this.reproducaoDeTramite = value;
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

    /**
     * Gets the value of the processo property.
     * 
     * @return
     *     possible object is
     *     {@link Processo }
     *     
     */
    public Processo getProcesso() {
        return processo;
    }

    /**
     * Sets the value of the processo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Processo }
     *     
     */
    public void setProcesso(Processo value) {
        this.processo = value;
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

}
