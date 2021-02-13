
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
 *         Objeto usado para organizar o resultado da
 *         lista de hipóteses legais consultados.
 *       
 * 
 * <p>Java class for hipotesesLegais complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hipotesesLegais">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hipotese" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="identificacao" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *                   &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="baseLegal" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
@XmlType(name = "hipotesesLegais", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "hipotese"
})
public class HipotesesLegais {

    protected List<HipotesesLegais.Hipotese> hipotese;

    /**
     * Gets the value of the hipotese property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hipotese property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHipotese().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HipotesesLegais.Hipotese }
     * 
     * 
     */
    public List<HipotesesLegais.Hipotese> getHipotese() {
        if (hipotese == null) {
            hipotese = new ArrayList<HipotesesLegais.Hipotese>();
        }
        return this.hipotese;
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
     *         &lt;element name="identificacao" type="{http://www.w3.org/2001/XMLSchema}integer"/>
     *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="baseLegal" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="descricao" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
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
        "identificacao",
        "nome",
        "baseLegal",
        "descricao",
        "status"
    })
    public static class Hipotese {

        @XmlElement(required = true)
        protected BigInteger identificacao;
        @XmlElement(required = true)
        protected String nome;
        @XmlElement(required = true)
        protected String baseLegal;
        @XmlElement(required = true)
        protected String descricao;
        protected boolean status;

        /**
         * Gets the value of the identificacao property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getIdentificacao() {
            return identificacao;
        }

        /**
         * Sets the value of the identificacao property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setIdentificacao(BigInteger value) {
            this.identificacao = value;
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
         * Gets the value of the baseLegal property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBaseLegal() {
            return baseLegal;
        }

        /**
         * Sets the value of the baseLegal property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBaseLegal(String value) {
            this.baseLegal = value;
        }

        /**
         * Gets the value of the descricao property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDescricao() {
            return descricao;
        }

        /**
         * Sets the value of the descricao property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDescricao(String value) {
            this.descricao = value;
        }

        /**
         * Gets the value of the status property.
         * 
         */
        public boolean isStatus() {
            return status;
        }

        /**
         * Sets the value of the status property.
         * 
         */
        public void setStatus(boolean value) {
            this.status = value;
        }

    }

}
