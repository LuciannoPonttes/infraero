
package br.gov.infraero.siga.pen.client;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for poloDoInteressado.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="poloDoInteressado">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ativo"/>
 *     &lt;enumeration value="passivo"/>
 *     &lt;enumeration value="terceiro"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "poloDoInteressado", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum")
@XmlEnum
public enum PoloDoInteressado {

    @XmlEnumValue("ativo")
    ATIVO("ativo"),
    @XmlEnumValue("passivo")
    PASSIVO("passivo"),
    @XmlEnumValue("terceiro")
    TERCEIRO("terceiro");
    private final String value;

    PoloDoInteressado(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static PoloDoInteressado fromValue(String v) {
        for (PoloDoInteressado c: PoloDoInteressado.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
