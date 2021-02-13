
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Entidade produtora do artefato em questão.
 *         Pode ou não estar presente nos repositórios
 *         de estruturas do serviço.
 *       
 * 
 * <p>Java class for produtor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="produtor">
 *   &lt;complexContent>
 *     &lt;extension base="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}pessoa">
 *       &lt;sequence>
 *         &lt;element name="unidade" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}pessoa">
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
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
@XmlType(name = "produtor", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "unidade"
})
public class Produtor
    extends Pessoa
{

    protected Produtor.Unidade unidade;

    /**
     * Gets the value of the unidade property.
     * 
     * @return
     *     possible object is
     *     {@link Produtor.Unidade }
     *     
     */
    public Produtor.Unidade getUnidade() {
        return unidade;
    }

    /**
     * Sets the value of the unidade property.
     * 
     * @param value
     *     allowed object is
     *     {@link Produtor.Unidade }
     *     
     */
    public void setUnidade(Produtor.Unidade value) {
        this.unidade = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}pessoa">
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Unidade
        extends Pessoa
    {


    }

}
