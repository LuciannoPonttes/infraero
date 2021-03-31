
package br.gov.infraero.siga.pen.client.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for paginacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="paginacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="registroInicial" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="quantidadeDeRegistros" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "paginacao", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "registroInicial",
    "quantidadeDeRegistros"
})
public class Paginacao {

    @XmlElement(required = true)
    protected BigInteger registroInicial;
    @XmlElement(required = true)
    protected BigInteger quantidadeDeRegistros;

    /**
     * Gets the value of the registroInicial property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRegistroInicial() {
        return registroInicial;
    }

    /**
     * Sets the value of the registroInicial property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRegistroInicial(BigInteger value) {
        this.registroInicial = value;
    }

    /**
     * Gets the value of the quantidadeDeRegistros property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getQuantidadeDeRegistros() {
        return quantidadeDeRegistros;
    }

    /**
     * Sets the value of the quantidadeDeRegistros property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setQuantidadeDeRegistros(BigInteger value) {
        this.quantidadeDeRegistros = value;
    }

}
