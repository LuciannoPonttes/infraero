
package br.gov.infraero.siga.pen.client.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.*;


/**
 * 
 *         Representa um arquivo binário. Normalmente
 *         este arquivo binário está atrelado a um
 *         documento, que pode, por sua vez, ser uma
 *         peça de um processo ou um documento avulso.
 *       
 * 
 * <p>Java class for componenteDigital complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="componenteDigital">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nome">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="hash" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}hash"/>
 *         &lt;element name="tipoDeConteudo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}tipoDeConteudo"/>
 *         &lt;element name="mimeType" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}mimeType"/>
 *         &lt;element name="dadosComplementaresDoTipoDeArquivo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}dadosComplementaresDoTipoDeArquivo" minOccurs="0"/>
 *         &lt;element name="tamanhoEmBytes" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="ordem" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="assinaturaDigital" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}assinaturaDigital" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "componenteDigital", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital", propOrder = {
    "nome",
    "hash",
    "tipoDeConteudo",
    "mimeType",
    "dadosComplementaresDoTipoDeArquivo",
    "tamanhoEmBytes",
    "ordem",
    "assinaturaDigital"
})
public class ComponenteDigital {

    @XmlElement(required = true)
    protected String nome;
    @XmlElement(required = true)
    protected Hash hash;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected TipoDeConteudo tipoDeConteudo;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected MimeType mimeType;
    protected String dadosComplementaresDoTipoDeArquivo;
    protected long tamanhoEmBytes;
    @XmlElement(required = true)
    protected BigInteger ordem;
    protected List<AssinaturaDigital> assinaturaDigital;

    @XmlTransient
    private byte[] bytes;

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the hash property.
     * 
     * @return
     *     possible object is
     *     {@link Hash }
     *     
     */
    public Hash getHash() {
        return hash;
    }

    /**
     * Sets the value of the hash property.
     * 
     * @param value
     *     allowed object is
     *     {@link Hash }
     *     
     */
    public void setHash(Hash value) {
        this.hash = value;
    }

    /**
     * Gets the value of the tipoDeConteudo property.
     * 
     * @return
     *     possible object is
     *     {@link TipoDeConteudo }
     *     
     */
    public TipoDeConteudo getTipoDeConteudo() {
        return tipoDeConteudo;
    }

    /**
     * Sets the value of the tipoDeConteudo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipoDeConteudo }
     *     
     */
    public void setTipoDeConteudo(TipoDeConteudo value) {
        this.tipoDeConteudo = value;
    }

    /**
     * Gets the value of the mimeType property.
     * 
     * @return
     *     possible object is
     *     {@link MimeType }
     *     
     */
    public MimeType getMimeType() {
        return mimeType;
    }

    /**
     * Sets the value of the mimeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link MimeType }
     *     
     */
    public void setMimeType(MimeType value) {
        this.mimeType = value;
    }

    /**
     * Gets the value of the dadosComplementaresDoTipoDeArquivo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDadosComplementaresDoTipoDeArquivo() {
        return dadosComplementaresDoTipoDeArquivo;
    }

    /**
     * Sets the value of the dadosComplementaresDoTipoDeArquivo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDadosComplementaresDoTipoDeArquivo(String value) {
        this.dadosComplementaresDoTipoDeArquivo = value;
    }

    /**
     * Gets the value of the tamanhoEmBytes property.
     * 
     */
    public long getTamanhoEmBytes() {
        return tamanhoEmBytes;
    }

    /**
     * Sets the value of the tamanhoEmBytes property.
     * 
     */
    public void setTamanhoEmBytes(long value) {
        this.tamanhoEmBytes = value;
    }

    /**
     * Gets the value of the ordem property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getOrdem() {
        return ordem;
    }

    /**
     * Sets the value of the ordem property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setOrdem(BigInteger value) {
        this.ordem = value;
    }

    /**
     * Gets the value of the assinaturaDigital property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the assinaturaDigital property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssinaturaDigital().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AssinaturaDigital }
     * 
     * 
     */
    public List<AssinaturaDigital> getAssinaturaDigital() {
        if (assinaturaDigital == null) {
            assinaturaDigital = new ArrayList<AssinaturaDigital>();
        }
        return this.assinaturaDigital;
    }

}
