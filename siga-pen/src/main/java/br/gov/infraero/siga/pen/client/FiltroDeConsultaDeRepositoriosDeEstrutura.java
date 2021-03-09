
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Objeto usado no serviço de consulta de
 *         repositórios de estruturas.
 *       
 * 
 * <p>Java class for filtroDeConsultaDeRepositoriosDeEstrutura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filtroDeConsultaDeRepositoriosDeEstrutura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ativos" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filtroDeConsultaDeRepositoriosDeEstrutura", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/consultas", propOrder = {
    "ativos"
})
public class FiltroDeConsultaDeRepositoriosDeEstrutura {

    protected Boolean ativos;

    /**
     * Gets the value of the ativos property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAtivos() {
        return ativos;
    }

    /**
     * Sets the value of the ativos property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAtivos(Boolean value) {
        this.ativos = value;
    }

}
