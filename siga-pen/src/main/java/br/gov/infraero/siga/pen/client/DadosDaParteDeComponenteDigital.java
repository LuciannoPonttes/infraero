
package br.gov.infraero.siga.pen.client;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Dados para upload de uma parte de componente
 *         digital. Usado em serviço próprio consumido
 *         pelo remetente do trâmite.
 *       
 * 
 * <p>Java class for dadosDaParteDeComponenteDigital complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosDaParteDeComponenteDigital">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ticketParaEnvioDeComponentesDigitais" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}ticketParaEnvioDeComponentesDigitais"/>
 *         &lt;element name="protocolo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}protocolo"/>
 *         &lt;element name="hashDoComponenteDigital" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="identificacaoDaParte" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}parteDeComponenteDigital"/>
 *         &lt;element name="conteudoDaParteDeComponenteDigital" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosDaParteDeComponenteDigital", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "ticketParaEnvioDeComponentesDigitais",
    "protocolo",
    "hashDoComponenteDigital",
    "identificacaoDaParte",
    "conteudoDaParteDeComponenteDigital"
})
public class DadosDaParteDeComponenteDigital {

    protected long ticketParaEnvioDeComponentesDigitais;
    @XmlElement(required = true)
    protected String protocolo;
    @XmlElement(required = true)
    protected String hashDoComponenteDigital;
    @XmlElement(required = true)
    protected ParteDeComponenteDigital identificacaoDaParte;
    @XmlElement(required = true)
    @XmlMimeType("application/octet-stream")
    protected DataHandler conteudoDaParteDeComponenteDigital;

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
     * Gets the value of the identificacaoDaParte property.
     * 
     * @return
     *     possible object is
     *     {@link ParteDeComponenteDigital }
     *     
     */
    public ParteDeComponenteDigital getIdentificacaoDaParte() {
        return identificacaoDaParte;
    }

    /**
     * Sets the value of the identificacaoDaParte property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParteDeComponenteDigital }
     *     
     */
    public void setIdentificacaoDaParte(ParteDeComponenteDigital value) {
        this.identificacaoDaParte = value;
    }

    /**
     * Gets the value of the conteudoDaParteDeComponenteDigital property.
     * 
     * @return
     *     possible object is
     *     {@link DataHandler }
     *     
     */
    public DataHandler getConteudoDaParteDeComponenteDigital() {
        return conteudoDaParteDeComponenteDigital;
    }

    /**
     * Sets the value of the conteudoDaParteDeComponenteDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataHandler }
     *     
     */
    public void setConteudoDaParteDeComponenteDigital(DataHandler value) {
        this.conteudoDaParteDeComponenteDigital = value;
    }

}
