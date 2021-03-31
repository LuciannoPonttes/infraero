
package br.gov.infraero.siga.pen.client.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Reúne dados de identificação do documento:
 *         número, ano, sigla da unidade produtora
 *         e o complemento.
 *       
 * 
 * <p>Java class for identificacaoDoDocumento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="identificacaoDoDocumento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="ano" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="siglaDaUnidadeProdutora" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *         &lt;element name="complemento" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="100"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "identificacaoDoDocumento", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "numero",
    "ano",
    "siglaDaUnidadeProdutora",
    "complemento"
})
public class IdentificacaoDoDocumento {

    protected BigInteger numero;
    protected BigInteger ano;
    protected Object siglaDaUnidadeProdutora;
    protected String complemento;

    /**
     * Gets the value of the numero property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumero() {
        return numero;
    }

    /**
     * Sets the value of the numero property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumero(BigInteger value) {
        this.numero = value;
    }

    /**
     * Gets the value of the ano property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAno() {
        return ano;
    }

    /**
     * Sets the value of the ano property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAno(BigInteger value) {
        this.ano = value;
    }

    /**
     * Gets the value of the siglaDaUnidadeProdutora property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getSiglaDaUnidadeProdutora() {
        return siglaDaUnidadeProdutora;
    }

    /**
     * Sets the value of the siglaDaUnidadeProdutora property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setSiglaDaUnidadeProdutora(Object value) {
        this.siglaDaUnidadeProdutora = value;
    }

    /**
     * Gets the value of the complemento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * Sets the value of the complemento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplemento(String value) {
        this.complemento = value;
    }

}
