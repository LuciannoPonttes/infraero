
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Dados do trâmite de reprodução criado. O
 *         conteúdo é o mesmo da resposta do envio 
 *         normal de processos e documentos.
 *       
 * 
 * <p>Java class for dadosDoTramiteDeReproducaoCriado complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dadosDoTramiteDeReproducaoCriado">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="tramiteDeProcessoCriado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosTramiteDeProcessoCriado"/>
 *         &lt;element name="tramiteDeDocumentoCriado" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite}dadosTramiteDeDocumentoCriado"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dadosDoTramiteDeReproducaoCriado", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/tramite", propOrder = {
    "tramiteDeProcessoCriado",
    "tramiteDeDocumentoCriado"
})
public class DadosDoTramiteDeReproducaoCriado {

    protected DadosTramiteDeProcessoCriado tramiteDeProcessoCriado;
    protected DadosTramiteDeDocumentoCriado tramiteDeDocumentoCriado;

    /**
     * Gets the value of the tramiteDeProcessoCriado property.
     * 
     * @return
     *     possible object is
     *     {@link DadosTramiteDeProcessoCriado }
     *     
     */
    public DadosTramiteDeProcessoCriado getTramiteDeProcessoCriado() {
        return tramiteDeProcessoCriado;
    }

    /**
     * Sets the value of the tramiteDeProcessoCriado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosTramiteDeProcessoCriado }
     *     
     */
    public void setTramiteDeProcessoCriado(DadosTramiteDeProcessoCriado value) {
        this.tramiteDeProcessoCriado = value;
    }

    /**
     * Gets the value of the tramiteDeDocumentoCriado property.
     * 
     * @return
     *     possible object is
     *     {@link DadosTramiteDeDocumentoCriado }
     *     
     */
    public DadosTramiteDeDocumentoCriado getTramiteDeDocumentoCriado() {
        return tramiteDeDocumentoCriado;
    }

    /**
     * Sets the value of the tramiteDeDocumentoCriado property.
     * 
     * @param value
     *     allowed object is
     *     {@link DadosTramiteDeDocumentoCriado }
     *     
     */
    public void setTramiteDeDocumentoCriado(DadosTramiteDeDocumentoCriado value) {
        this.tramiteDeDocumentoCriado = value;
    }

}
