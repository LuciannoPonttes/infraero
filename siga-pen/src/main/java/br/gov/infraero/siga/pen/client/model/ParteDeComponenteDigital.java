
package br.gov.infraero.siga.pen.client.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Representa uma faixa de bytes, por exemplo: 10-50.
 *         O primeiro valor é o início da faixa, e o segundo,
 *         o fim. Considera-se índices iniciados em zero, ou seja,
 *         0-10 significa os primeiros 10 bytes do componente.
 *         Essa definição também indica que o segundo número é
 *         exclusivo, ou seja, o byte representado pelo seu valor
 *         *não* é retornado: a faixa 0-1 traz apenas 1 byte, e não
 *         2. Exemplo utilizando notação matemática: "[0-1[".
 *       
 * 
 * <p>Java class for parteDeComponenteDigital complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parteDeComponenteDigital">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inicio">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="fim">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}integer">
 *               &lt;minInclusive value="0"/>
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
@XmlType(name = "parteDeComponenteDigital", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital", propOrder = {
    "inicio",
    "fim"
})
public class ParteDeComponenteDigital {

    @XmlElement(required = true)
    protected BigInteger inicio;
    @XmlElement(required = true)
    protected BigInteger fim;

    /**
     * Gets the value of the inicio property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInicio() {
        return inicio;
    }

    /**
     * Sets the value of the inicio property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInicio(BigInteger value) {
        this.inicio = value;
    }

    /**
     * Gets the value of the fim property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFim() {
        return fim;
    }

    /**
     * Sets the value of the fim property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFim(BigInteger value) {
        this.fim = value;
    }

}
