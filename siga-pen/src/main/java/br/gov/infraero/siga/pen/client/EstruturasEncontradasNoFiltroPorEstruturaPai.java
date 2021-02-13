
package br.gov.infraero.siga.pen.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Coleção de estruturas organizacionais encontradas
 *         na consulta de estruturas por estrutura pai.
 *       
 * 
 * <p>Java class for estruturasEncontradasNoFiltroPorEstruturaPai complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="estruturasEncontradasNoFiltroPorEstruturaPai">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="estrutura" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura"/>
 *                   &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="unidadeProtocolizadora" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="unidadeReceptora" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="aptoParaReceberTramites" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *                   &lt;element name="codigoNoOrgaoEntidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tipoDeTramitacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}tipoDeTramitacao" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
@XmlType(name = "estruturasEncontradasNoFiltroPorEstruturaPai", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "estrutura"
})
public class EstruturasEncontradasNoFiltroPorEstruturaPai {

    protected List<EstruturasEncontradasNoFiltroPorEstruturaPai.Estrutura> estrutura;

    /**
     * Gets the value of the estrutura property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the estrutura property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEstrutura().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EstruturasEncontradasNoFiltroPorEstruturaPai.Estrutura }
     * 
     * 
     */
    public List<EstruturasEncontradasNoFiltroPorEstruturaPai.Estrutura> getEstrutura() {
        if (estrutura == null) {
            estrutura = new ArrayList<EstruturasEncontradasNoFiltroPorEstruturaPai.Estrutura>();
        }
        return this.estrutura;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura"/>
     *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="ativo" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="unidadeProtocolizadora" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="unidadeReceptora" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="aptoParaReceberTramites" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
     *         &lt;element name="codigoNoOrgaoEntidade" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tipoDeTramitacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}tipoDeTramitacao" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "numeroDeIdentificacaoDaEstrutura",
        "nome",
        "sigla",
        "ativo",
        "unidadeProtocolizadora",
        "unidadeReceptora",
        "aptoParaReceberTramites",
        "codigoNoOrgaoEntidade",
        "tipoDeTramitacao"
    })
    public static class Estrutura {

        @XmlElement(required = true)
        protected String numeroDeIdentificacaoDaEstrutura;
        @XmlElement(required = true)
        protected String nome;
        @XmlElement(required = true)
        protected String sigla;
        protected boolean ativo;
        protected boolean unidadeProtocolizadora;
        protected boolean unidadeReceptora;
        protected boolean aptoParaReceberTramites;
        protected String codigoNoOrgaoEntidade;
        protected BigInteger tipoDeTramitacao;

        /**
         * Gets the value of the numeroDeIdentificacaoDaEstrutura property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNumeroDeIdentificacaoDaEstrutura() {
            return numeroDeIdentificacaoDaEstrutura;
        }

        /**
         * Sets the value of the numeroDeIdentificacaoDaEstrutura property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNumeroDeIdentificacaoDaEstrutura(String value) {
            this.numeroDeIdentificacaoDaEstrutura = value;
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
         * Gets the value of the sigla property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSigla() {
            return sigla;
        }

        /**
         * Sets the value of the sigla property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSigla(String value) {
            this.sigla = value;
        }

        /**
         * Gets the value of the ativo property.
         * 
         */
        public boolean isAtivo() {
            return ativo;
        }

        /**
         * Sets the value of the ativo property.
         * 
         */
        public void setAtivo(boolean value) {
            this.ativo = value;
        }

        /**
         * Gets the value of the unidadeProtocolizadora property.
         * 
         */
        public boolean isUnidadeProtocolizadora() {
            return unidadeProtocolizadora;
        }

        /**
         * Sets the value of the unidadeProtocolizadora property.
         * 
         */
        public void setUnidadeProtocolizadora(boolean value) {
            this.unidadeProtocolizadora = value;
        }

        /**
         * Gets the value of the unidadeReceptora property.
         * 
         */
        public boolean isUnidadeReceptora() {
            return unidadeReceptora;
        }

        /**
         * Sets the value of the unidadeReceptora property.
         * 
         */
        public void setUnidadeReceptora(boolean value) {
            this.unidadeReceptora = value;
        }

        /**
         * Gets the value of the aptoParaReceberTramites property.
         * 
         */
        public boolean isAptoParaReceberTramites() {
            return aptoParaReceberTramites;
        }

        /**
         * Sets the value of the aptoParaReceberTramites property.
         * 
         */
        public void setAptoParaReceberTramites(boolean value) {
            this.aptoParaReceberTramites = value;
        }

        /**
         * Gets the value of the codigoNoOrgaoEntidade property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoNoOrgaoEntidade() {
            return codigoNoOrgaoEntidade;
        }

        /**
         * Sets the value of the codigoNoOrgaoEntidade property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoNoOrgaoEntidade(String value) {
            this.codigoNoOrgaoEntidade = value;
        }

        /**
         * Gets the value of the tipoDeTramitacao property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTipoDeTramitacao() {
            return tipoDeTramitacao;
        }

        /**
         * Sets the value of the tipoDeTramitacao property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTipoDeTramitacao(BigInteger value) {
            this.tipoDeTramitacao = value;
        }

    }

}
