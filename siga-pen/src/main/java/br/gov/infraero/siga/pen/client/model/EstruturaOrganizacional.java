
package br.gov.infraero.siga.pen.client.model;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Estrutura organizacional vinculada a API de interoperabilidade.
 *         Pode ser um órgão, um departamento, ou qualquer outra entidade
 *         aplicável. Identificada através da identificação do repositório
 *         em que se encontra e do seu código de identificação.
 *       
 * 
 * <p>Java class for estruturaOrganizacional complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="estruturaOrganizacional">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificacaoDoRepositorioDeEstruturas" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}identificacaoDoRepositorioDeEstruturas"/>
 *         &lt;element name="numeroDeIdentificacaoDaEstrutura" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}numeroDeIdentificacaoDaEstrutura"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estruturaOrganizacional", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "identificacaoDoRepositorioDeEstruturas",
    "numeroDeIdentificacaoDaEstrutura"
})
public class EstruturaOrganizacional {

    @XmlElement(required = true)
    protected BigInteger identificacaoDoRepositorioDeEstruturas;
    @XmlElement(required = true)
    protected String numeroDeIdentificacaoDaEstrutura;

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

}
