
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Encapsula os parâmetros possíveis para recebimento
 *         de um componente digital. Através desse objeto, o
 *         consumidor do serviço pode especificar a parte específica
 *         do componente que deseja receber, caso esteja fazendo o
 *         download do mesmo por frações do conteúdo (chunks).
 *       
 * 
 * <p>Java class for parametrosParaRecebimentoDeComponenteDigital complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parametrosParaRecebimentoDeComponenteDigital">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificacaoDoComponenteDigital" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}identificacaoDoComponenteDigital"/>
 *         &lt;element name="parte" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital}parteDeComponenteDigital" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parametrosParaRecebimentoDeComponenteDigital", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "identificacaoDoComponenteDigital",
    "parte"
})
public class ParametrosParaRecebimentoDeComponenteDigital {

    @XmlElement(required = true)
    protected IdentificacaoDoComponenteDigital identificacaoDoComponenteDigital;
    protected ParteDeComponenteDigital parte;

    /**
     * Gets the value of the identificacaoDoComponenteDigital property.
     * 
     * @return
     *     possible object is
     *     {@link IdentificacaoDoComponenteDigital }
     *     
     */
    public IdentificacaoDoComponenteDigital getIdentificacaoDoComponenteDigital() {
        return identificacaoDoComponenteDigital;
    }

    /**
     * Sets the value of the identificacaoDoComponenteDigital property.
     * 
     * @param value
     *     allowed object is
     *     {@link IdentificacaoDoComponenteDigital }
     *     
     */
    public void setIdentificacaoDoComponenteDigital(IdentificacaoDoComponenteDigital value) {
        this.identificacaoDoComponenteDigital = value;
    }

    /**
     * Gets the value of the parte property.
     * 
     * @return
     *     possible object is
     *     {@link ParteDeComponenteDigital }
     *     
     */
    public ParteDeComponenteDigital getParte() {
        return parte;
    }

    /**
     * Sets the value of the parte property.
     * 
     * @param value
     *     allowed object is
     *     {@link ParteDeComponenteDigital }
     *     
     */
    public void setParte(ParteDeComponenteDigital value) {
        this.parte = value;
    }

}
