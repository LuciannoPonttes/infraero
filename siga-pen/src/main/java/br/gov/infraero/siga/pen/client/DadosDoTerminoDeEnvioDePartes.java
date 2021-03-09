
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Dados para sinalizar o término do envio
 *         das partes de um componente digital de um trâmite. 
 *         Usado em serviço próprio consumido
 *         pelo remetente do trâmite.
 *       
 * 
 * <p>Java class for dadosDoTerminoDeEnvioDePartes complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosDoTerminoDeEnvioDePartes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticketParaEnvioDeComponentesDigitais" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}ticketParaEnvioDeComponentesDigitais"/>
 *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *         &lt;element name="hashDoComponenteDigital" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosDoTerminoDeEnvioDePartes", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "ticketParaEnvioDeComponentesDigitais",
    "protocolo",
    "hashDoComponenteDigital"
})
public class DadosDoTerminoDeEnvioDePartes {

    protected long ticketParaEnvioDeComponentesDigitais;
    @XmlElement(required = true)
    protected String protocolo;
    @XmlElement(required = true)
    protected String hashDoComponenteDigital;

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

}
