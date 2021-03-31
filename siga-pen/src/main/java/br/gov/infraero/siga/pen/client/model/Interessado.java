
package br.gov.infraero.siga.pen.client.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * 
 *         Pessoa que possui algum interesse
 *         no desfecho do processo administrativo
 *         relacionado.
 *       
 * 
 * <p>Java class for interessado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="interessado">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}pessoa">
 *       &lt;sequence>
 *         &lt;element name="polo" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}poloDoInteressado" minOccurs="0"/>
 *         &lt;element name="outroNome" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documentoDeIdentificacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}documentoDeIdentificacao" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="endereco" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}endereco" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sexo" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="M"/>
 *               &lt;enumeration value="F"/>
 *               &lt;enumeration value="D"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nomeDoGenitor" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome" minOccurs="0"/>
 *         &lt;element name="nomeDaGenitora" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome" minOccurs="0"/>
 *         &lt;element name="dataDeNascimento" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="dataDeObito" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="cidadeNatural" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome" minOccurs="0"/>
 *         &lt;element name="estadoNatural" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[A-Z]{2}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nacionalidade" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;pattern value="[A-Z]{2}"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "interessado", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "polo",
    "outroNome",
    "documentoDeIdentificacao",
    "endereco",
    "sexo",
    "nomeDoGenitor",
    "nomeDaGenitora",
    "dataDeNascimento",
    "dataDeObito",
    "cidadeNatural",
    "estadoNatural",
    "nacionalidade"
})
public class Interessado
    extends Pessoa
{

    @XmlSchemaType(name = "string")
    protected PoloDoInteressado polo;
    protected List<String> outroNome;
    protected List<DocumentoDeIdentificacao> documentoDeIdentificacao;
    protected List<Endereco> endereco;
    protected String sexo;
    protected String nomeDoGenitor;
    protected String nomeDaGenitora;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDeNascimento;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDeObito;
    protected String cidadeNatural;
    protected String estadoNatural;
    protected String nacionalidade;

    /**
     * Gets the value of the polo property.
     * 
     * @return
     *     possible object is
     *     {@link PoloDoInteressado }
     *     
     */
    public PoloDoInteressado getPolo() {
        return polo;
    }

    /**
     * Sets the value of the polo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PoloDoInteressado }
     *     
     */
    public void setPolo(PoloDoInteressado value) {
        this.polo = value;
    }

    /**
     * Gets the value of the outroNome property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outroNome property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutroNome().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getOutroNome() {
        if (outroNome == null) {
            outroNome = new ArrayList<String>();
        }
        return this.outroNome;
    }

    /**
     * Gets the value of the documentoDeIdentificacao property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentoDeIdentificacao property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentoDeIdentificacao().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocumentoDeIdentificacao }
     * 
     * 
     */
    public List<DocumentoDeIdentificacao> getDocumentoDeIdentificacao() {
        if (documentoDeIdentificacao == null) {
            documentoDeIdentificacao = new ArrayList<DocumentoDeIdentificacao>();
        }
        return this.documentoDeIdentificacao;
    }

    /**
     * Gets the value of the endereco property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endereco property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndereco().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Endereco }
     * 
     * 
     */
    public List<Endereco> getEndereco() {
        if (endereco == null) {
            endereco = new ArrayList<Endereco>();
        }
        return this.endereco;
    }

    /**
     * Gets the value of the sexo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Sets the value of the sexo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSexo(String value) {
        this.sexo = value;
    }

    /**
     * Gets the value of the nomeDoGenitor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeDoGenitor() {
        return nomeDoGenitor;
    }

    /**
     * Sets the value of the nomeDoGenitor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeDoGenitor(String value) {
        this.nomeDoGenitor = value;
    }

    /**
     * Gets the value of the nomeDaGenitora property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeDaGenitora() {
        return nomeDaGenitora;
    }

    /**
     * Sets the value of the nomeDaGenitora property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeDaGenitora(String value) {
        this.nomeDaGenitora = value;
    }

    /**
     * Gets the value of the dataDeNascimento property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDeNascimento() {
        return dataDeNascimento;
    }

    /**
     * Sets the value of the dataDeNascimento property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDeNascimento(XMLGregorianCalendar value) {
        this.dataDeNascimento = value;
    }

    /**
     * Gets the value of the dataDeObito property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDeObito() {
        return dataDeObito;
    }

    /**
     * Sets the value of the dataDeObito property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDeObito(XMLGregorianCalendar value) {
        this.dataDeObito = value;
    }

    /**
     * Gets the value of the cidadeNatural property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCidadeNatural() {
        return cidadeNatural;
    }

    /**
     * Sets the value of the cidadeNatural property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCidadeNatural(String value) {
        this.cidadeNatural = value;
    }

    /**
     * Gets the value of the estadoNatural property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoNatural() {
        return estadoNatural;
    }

    /**
     * Sets the value of the estadoNatural property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoNatural(String value) {
        this.estadoNatural = value;
    }

    /**
     * Gets the value of the nacionalidade property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNacionalidade() {
        return nacionalidade;
    }

    /**
     * Sets the value of the nacionalidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNacionalidade(String value) {
        this.nacionalidade = value;
    }

}
