
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
 *         Objeto que encapsula o conjunto de estruturas
 *         organizacionais encontradas no filtro.
 *       
 * 
 * <p>Java class for estruturasEncontradas complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="estruturasEncontradas">
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
 *                   &lt;element name="codigoUnidadeReceptora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="codigoUnidadeProtocolizadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tipoDeTramitacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}tipoDeTramitacao" minOccurs="0"/>
 *                   &lt;element name="hierarquia" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="nivel" maxOccurs="unbounded" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura"/>
 *                                       &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="totalDeRegistros" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estruturasEncontradas", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "estrutura",
    "totalDeRegistros"
})
public class EstruturasEncontradas {

    protected List<EstruturasEncontradas.Estrutura> estrutura;
    protected long totalDeRegistros;

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
     * {@link EstruturasEncontradas.Estrutura }
     * 
     * 
     */
    public List<EstruturasEncontradas.Estrutura> getEstrutura() {
        if (estrutura == null) {
            estrutura = new ArrayList<EstruturasEncontradas.Estrutura>();
        }
        return this.estrutura;
    }

    /**
     * Gets the value of the totalDeRegistros property.
     * 
     */
    public long getTotalDeRegistros() {
        return totalDeRegistros;
    }

    /**
     * Sets the value of the totalDeRegistros property.
     * 
     */
    public void setTotalDeRegistros(long value) {
        this.totalDeRegistros = value;
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
     *         &lt;element name="codigoUnidadeReceptora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="codigoUnidadeProtocolizadora" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tipoDeTramitacao" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}tipoDeTramitacao" minOccurs="0"/>
     *         &lt;element name="hierarquia" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="nivel" maxOccurs="unbounded" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura"/>
     *                             &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
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
    @XmlType(name = "", propOrder = {
        "numeroDeIdentificacaoDaEstrutura",
        "nome",
        "sigla",
        "ativo",
        "unidadeProtocolizadora",
        "unidadeReceptora",
        "aptoParaReceberTramites",
        "codigoNoOrgaoEntidade",
        "codigoUnidadeReceptora",
        "codigoUnidadeProtocolizadora",
        "tipoDeTramitacao",
        "hierarquia"
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
        protected String codigoUnidadeReceptora;
        protected String codigoUnidadeProtocolizadora;
        protected BigInteger tipoDeTramitacao;
        protected EstruturasEncontradas.Estrutura.Hierarquia hierarquia;

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
         * Gets the value of the codigoUnidadeReceptora property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoUnidadeReceptora() {
            return codigoUnidadeReceptora;
        }

        /**
         * Sets the value of the codigoUnidadeReceptora property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoUnidadeReceptora(String value) {
            this.codigoUnidadeReceptora = value;
        }

        /**
         * Gets the value of the codigoUnidadeProtocolizadora property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCodigoUnidadeProtocolizadora() {
            return codigoUnidadeProtocolizadora;
        }

        /**
         * Sets the value of the codigoUnidadeProtocolizadora property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCodigoUnidadeProtocolizadora(String value) {
            this.codigoUnidadeProtocolizadora = value;
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

        /**
         * Gets the value of the hierarquia property.
         * 
         * @return
         *     possible object is
         *     {@link EstruturasEncontradas.Estrutura.Hierarquia }
         *     
         */
        public EstruturasEncontradas.Estrutura.Hierarquia getHierarquia() {
            return hierarquia;
        }

        /**
         * Sets the value of the hierarquia property.
         * 
         * @param value
         *     allowed object is
         *     {@link EstruturasEncontradas.Estrutura.Hierarquia }
         *     
         */
        public void setHierarquia(EstruturasEncontradas.Estrutura.Hierarquia value) {
            this.hierarquia = value;
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
         *         &lt;element name="nivel" maxOccurs="unbounded" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura"/>
         *                   &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="sigla" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        @XmlType(name = "", propOrder = {
            "nivel"
        })
        public static class Hierarquia {

            protected List<EstruturasEncontradas.Estrutura.Hierarquia.Nivel> nivel;

            /**
             * Gets the value of the nivel property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the nivel property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getNivel().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link EstruturasEncontradas.Estrutura.Hierarquia.Nivel }
             * 
             * 
             */
            public List<EstruturasEncontradas.Estrutura.Hierarquia.Nivel> getNivel() {
                if (nivel == null) {
                    nivel = new ArrayList<EstruturasEncontradas.Estrutura.Hierarquia.Nivel>();
                }
                return this.nivel;
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
                "sigla"
            })
            public static class Nivel {

                @XmlElement(required = true)
                protected String numeroDeIdentificacaoDaEstrutura;
                @XmlElement(required = true)
                protected String nome;
                @XmlElement(required = true)
                protected String sigla;

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

            }

        }

    }

}
