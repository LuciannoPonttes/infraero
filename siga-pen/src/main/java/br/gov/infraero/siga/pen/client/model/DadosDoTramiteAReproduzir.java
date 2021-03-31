
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Dados da solicitação de reprodução de trâmite,
 *         informados pelo remetente do trâmite original.
 *         Com essas informações (NRE e destinatário), a
 *         solução pode buscar o último trâmite com essa
 *         configuração e tentar reproduzí-lo.
 *       
 * 
 * <p>Java class for dadosDoTramiteAReproduzir complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosDoTramiteAReproduzir">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="NRE" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}NRE"/>
 *         &lt;element name="destinatario" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}estruturaOrganizacional"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosDoTramiteAReproduzir", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "nre",
    "destinatario"
})
public class DadosDoTramiteAReproduzir {

    @XmlElement(name = "NRE", required = true)
    protected String nre;
    @XmlElement(required = true)
    protected EstruturaOrganizacional destinatario;

    /**
     * Gets the value of the nre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNRE() {
        return nre;
    }

    /**
     * Sets the value of the nre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNRE(String value) {
        this.nre = value;
    }

    /**
     * Gets the value of the destinatario property.
     * 
     * @return
     *     possible object is
     *     {@link EstruturaOrganizacional }
     *     
     */
    public EstruturaOrganizacional getDestinatario() {
        return destinatario;
    }

    /**
     * Sets the value of the destinatario property.
     * 
     * @param value
     *     allowed object is
     *     {@link EstruturaOrganizacional }
     *     
     */
    public void setDestinatario(EstruturaOrganizacional value) {
        this.destinatario = value;
    }

}
