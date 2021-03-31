
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Dados que o destinatário deve informar
 *         para recusar um trâmite. São eles:
 *         IDT do trâmite a ser recusado, justificativa
 *         (texto aberto) da recusa e motivo (enumeração
 *         de opções) da recusa.
 *       
 * 
 * <p>Java class for recusaDeTramite complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="recusaDeTramite">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IDT" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}IDT"/>
 *         &lt;element name="justificativa" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}justificativaDaRecusaDeTramite"/>
 *         &lt;element name="motivo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}motivoDaRecusaDeTramite"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "recusaDeTramite", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "idt",
    "justificativa",
    "motivo"
})
public class RecusaDeTramite {

    @XmlElement(name = "IDT")
    protected long idt;
    @XmlElement(required = true)
    protected String justificativa;
    @XmlElement(required = true)
    protected String motivo;

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
     * Gets the value of the justificativa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJustificativa() {
        return justificativa;
    }

    /**
     * Sets the value of the justificativa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJustificativa(String value) {
        this.justificativa = value;
    }

    /**
     * Gets the value of the motivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Sets the value of the motivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMotivo(String value) {
        this.motivo = value;
    }

}
