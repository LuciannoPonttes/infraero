
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * 
 *         Hash gerado a partir do algoritmo informado
 *         no atributo correspondente, codificado em Base64. Ex:
 *         Input: 123456
 *         Output: ujJTh2rta8ItSm/1PYQGxq2GQZXtFEq1yHYhtsIz
 *         tUi66uaVbfNG7IwX9eoQ817jy8UUeX7X3dMUVGTioLq0Ew==
 *       
 * 
 * <p>Java class for hashDeAssinatura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hashDeAssinatura">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="algoritmo" use="required">
 *         &lt;simpleType>
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *             &lt;enumeration value="NONEwithRSA"/>
 *             &lt;enumeration value="MD2withRSA"/>
 *             &lt;enumeration value="MD5withRSA"/>
 *             &lt;enumeration value="SHA1withRSA"/>
 *             &lt;enumeration value="SHA256withRSA"/>
 *             &lt;enumeration value="SHA384withRSA"/>
 *             &lt;enumeration value="SHA512withRSA"/>
 *             &lt;enumeration value="NONEwithDSA"/>
 *             &lt;enumeration value="SHA1withDSA"/>
 *             &lt;enumeration value="NONEwithECDSA"/>
 *             &lt;enumeration value="SHA1withECDSA"/>
 *             &lt;enumeration value="SHA256withECDSA"/>
 *             &lt;enumeration value="SHA384withECDSA"/>
 *             &lt;enumeration value="SHA512withECDSA"/>
 *           &lt;/restriction>
 *         &lt;/simpleType>
 *       &lt;/attribute>
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hashDeAssinatura", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital", propOrder = {
    "value"
})
public class HashDeAssinatura {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "algoritmo", required = true)
    protected String algoritmo;

    /**
     * Gets the value of the value property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the value of the algoritmo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlgoritmo() {
        return algoritmo;
    }

    /**
     * Sets the value of the algoritmo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlgoritmo(String value) {
        this.algoritmo = value;
    }

}
