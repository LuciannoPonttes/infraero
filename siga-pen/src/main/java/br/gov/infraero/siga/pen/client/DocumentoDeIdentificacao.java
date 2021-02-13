
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Define um documento de identificação de
 *         uma determinada pessoa.
 *       
 * 
 * <p>Java class for documentoDeIdentificacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="documentoDeIdentificacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="emissor" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome"/>
 *         &lt;element name="tipo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="CI"/>
 *               &lt;enumeration value="CNH"/>
 *               &lt;enumeration value="TE"/>
 *               &lt;enumeration value="CN"/>
 *               &lt;enumeration value="CC"/>
 *               &lt;enumeration value="PAS"/>
 *               &lt;enumeration value="CT"/>
 *               &lt;enumeration value="RIC"/>
 *               &lt;enumeration value="CMF"/>
 *               &lt;enumeration value="PISPASEP"/>
 *               &lt;enumeration value="CEI"/>
 *               &lt;enumeration value="NIT"/>
 *               &lt;enumeration value="CP"/>
 *               &lt;enumeration value="IF"/>
 *               &lt;enumeration value="OAB"/>
 *               &lt;enumeration value="RJC"/>
 *               &lt;enumeration value="RGE"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nomeNoDocumento" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "documentoDeIdentificacao", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "codigo",
    "emissor",
    "tipo",
    "nomeNoDocumento"
})
public class DocumentoDeIdentificacao {

    @XmlElement(required = true)
    protected String codigo;
    @XmlElement(required = true)
    protected String emissor;
    @XmlElement(required = true)
    protected String tipo;
    protected String nomeNoDocumento;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the emissor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmissor() {
        return emissor;
    }

    /**
     * Sets the value of the emissor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmissor(String value) {
        this.emissor = value;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the nomeNoDocumento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeNoDocumento() {
        return nomeNoDocumento;
    }

    /**
     * Sets the value of the nomeNoDocumento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeNoDocumento(String value) {
        this.nomeNoDocumento = value;
    }

}
