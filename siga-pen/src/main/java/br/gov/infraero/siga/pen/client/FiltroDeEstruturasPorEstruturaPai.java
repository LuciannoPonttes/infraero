
package br.gov.infraero.siga.pen.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Objeto usado para filtrar estruturas organizacionai
 *         na consulta hierárquica.
 *       
 * 
 * <p>Java class for filtroDeEstruturasPorEstruturaPai complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroDeEstruturasPorEstruturaPai">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificacaoDoRepositorioDeEstruturas" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}identificacaoDoRepositorioDeEstruturas"/>
 *         &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura" minOccurs="0"/>
 *         &lt;element name="apenasAtivas" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroDeEstruturasPorEstruturaPai", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "identificacaoDoRepositorioDeEstruturas",
    "numeroDeIdentificacaoDaEstrutura",
    "apenasAtivas"
})
public class FiltroDeEstruturasPorEstruturaPai {

    @XmlElement(required = true)
    protected BigInteger identificacaoDoRepositorioDeEstruturas;
    protected String numeroDeIdentificacaoDaEstrutura;
    protected Boolean apenasAtivas;

    /**
     * Gets the value of the identificacaoDoRepositorioDeEstruturas property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdentificacaoDoRepositorioDeEstruturas() {
        return identificacaoDoRepositorioDeEstruturas;
    }

    /**
     * Sets the value of the identificacaoDoRepositorioDeEstruturas property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdentificacaoDoRepositorioDeEstruturas(BigInteger value) {
        this.identificacaoDoRepositorioDeEstruturas = value;
    }

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
     * Gets the value of the apenasAtivas property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApenasAtivas() {
        return apenasAtivas;
    }

    /**
     * Sets the value of the apenasAtivas property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApenasAtivas(Boolean value) {
        this.apenasAtivas = value;
    }

}
