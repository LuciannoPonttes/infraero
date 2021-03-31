
package br.gov.infraero.siga.pen.client.model;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Dados para upload de um arquivo binário de
 *         um trâmite. Usado em serviço próprio consumido
 *         pelo remetente do trâmite.
 *       
 * 
 * <p>Java class for dadosDoComponenteDigital complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosDoComponenteDigital">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticketParaEnvioDeComponentesDigitais" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}ticketParaEnvioDeComponentesDigitais"/>
 *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *         &lt;element name="hashDoComponenteDigital" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="conteudoDoComponenteDigital" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosDoComponenteDigital", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "ticketParaEnvioDeComponentesDigitais",
    "protocolo",
    "hashDoComponenteDigital",
    "conteudoDoComponenteDigital"
})
public class DadosDoComponenteDigital {

    protected long ticketParaEnvioDeComponentesDigitais;
    @XmlElement(required = true)
    protected String protocolo;
    @XmlElement(required = true)
    protected String hashDoComponenteDigital;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler conteudoDoComponenteDigital;

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
     * Gets the value of the hashDoComponenteDigital property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHashDoComponenteDigital() {
        return hashDoComponenteDigital;
    }

    /**
     * Sets the value of the hashDoComponenteDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHashDoComponenteDigital(String value) {
        this.hashDoComponenteDigital = value;
    }

    /**
     * Gets the value of the conteudoDoComponenteDigital property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getConteudoDoComponenteDigital() {
        return conteudoDoComponenteDigital;
    }

    /**
     * Sets the value of the conteudoDoComponenteDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setConteudoDoComponenteDigital(DataHandler value) {
        this.conteudoDoComponenteDigital = value;
    }

}
