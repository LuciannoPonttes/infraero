
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tipoDeConteudo.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="tipoDeConteudo">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="txt"/>
 *     &lt;enumeration value="img"/>
 *     &lt;enumeration value="aud"/>
 *     &lt;enumeration value="vid"/>
 *     &lt;enumeration value="out"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "tipoDeConteudo", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital")
@XmlEnum
public enum TipoDeConteudo {


    /**
     * Textual
     * 
     */
    @XmlEnumValue("txt")
    TXT("txt"),

    /**
     * Imagem
     * 
     */
    @XmlEnumValue("img")
    IMG("img"),

    /**
     * Áudio
     * 
     */
    @XmlEnumValue("aud")
    AUD("aud"),

    /**
     * Vídeo
     * 
     */
    @XmlEnumValue("vid")
    VID("vid"),

    /**
     * Outro
     * 
     */
    @XmlEnumValue("out")
    OUT("out");
    private final String value;

    TipoDeConteudo(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static TipoDeConteudo fromValue(String v) {
        for (TipoDeConteudo c: TipoDeConteudo.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
