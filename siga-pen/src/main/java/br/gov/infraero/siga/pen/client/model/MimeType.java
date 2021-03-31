
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mimeType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="mimeType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="application/pdf"/>
 *     &lt;enumeration value="application/vnd.oasis.opendocument.text"/>
 *     &lt;enumeration value="application/vnd.oasis.opendocument.formula"/>
 *     &lt;enumeration value="application/vnd.oasis.opendocument.spreadsheet"/>
 *     &lt;enumeration value="application/vnd.oasis.opendocument.presentation"/>
 *     &lt;enumeration value="text/xml"/>
 *     &lt;enumeration value="text/rtf"/>
 *     &lt;enumeration value="text/html"/>
 *     &lt;enumeration value="text/plain"/>
 *     &lt;enumeration value="text/csv"/>
 *     &lt;enumeration value="image/gif"/>
 *     &lt;enumeration value="image/jpeg"/>
 *     &lt;enumeration value="image/png"/>
 *     &lt;enumeration value="image/svg+xml"/>
 *     &lt;enumeration value="image/tiff"/>
 *     &lt;enumeration value="image/bmp"/>
 *     &lt;enumeration value="audio/mp4"/>
 *     &lt;enumeration value="audio/midi"/>
 *     &lt;enumeration value="audio/ogg"/>
 *     &lt;enumeration value="audio/vnd.wave"/>
 *     &lt;enumeration value="video/avi"/>
 *     &lt;enumeration value="video/mpeg"/>
 *     &lt;enumeration value="video/mp4"/>
 *     &lt;enumeration value="video/ogg"/>
 *     &lt;enumeration value="video/webm"/>
 *     &lt;enumeration value="outro"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "mimeType", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/componente-digital")
@XmlEnum
public enum MimeType {

    @XmlEnumValue("application/pdf")
    APPLICATION_PDF("application/pdf"),
    @XmlEnumValue("application/vnd.oasis.opendocument.text")
    APPLICATION_VND_OASIS_OPENDOCUMENT_TEXT("application/vnd.oasis.opendocument.text"),
    @XmlEnumValue("application/vnd.oasis.opendocument.formula")
    APPLICATION_VND_OASIS_OPENDOCUMENT_FORMULA("application/vnd.oasis.opendocument.formula"),
    @XmlEnumValue("application/vnd.oasis.opendocument.spreadsheet")
    APPLICATION_VND_OASIS_OPENDOCUMENT_SPREADSHEET("application/vnd.oasis.opendocument.spreadsheet"),
    @XmlEnumValue("application/vnd.oasis.opendocument.presentation")
    APPLICATION_VND_OASIS_OPENDOCUMENT_PRESENTATION("application/vnd.oasis.opendocument.presentation"),
    @XmlEnumValue("text/xml")
    TEXT_XML("text/xml"),
    @XmlEnumValue("text/rtf")
    TEXT_RTF("text/rtf"),
    @XmlEnumValue("text/html")
    TEXT_HTML("text/html"),
    @XmlEnumValue("text/plain")
    TEXT_PLAIN("text/plain"),
    @XmlEnumValue("text/csv")
    TEXT_CSV("text/csv"),
    @XmlEnumValue("image/gif")
    IMAGE_GIF("image/gif"),
    @XmlEnumValue("image/jpeg")
    IMAGE_JPEG("image/jpeg"),
    @XmlEnumValue("image/png")
    IMAGE_PNG("image/png"),
    @XmlEnumValue("image/svg+xml")
    IMAGE_SVG_XML("image/svg+xml"),
    @XmlEnumValue("image/tiff")
    IMAGE_TIFF("image/tiff"),
    @XmlEnumValue("image/bmp")
    IMAGE_BMP("image/bmp"),
    @XmlEnumValue("audio/mp4")
    AUDIO_MP_4("audio/mp4"),
    @XmlEnumValue("audio/midi")
    AUDIO_MIDI("audio/midi"),
    @XmlEnumValue("audio/ogg")
    AUDIO_OGG("audio/ogg"),
    @XmlEnumValue("audio/vnd.wave")
    AUDIO_VND_WAVE("audio/vnd.wave"),
    @XmlEnumValue("video/avi")
    VIDEO_AVI("video/avi"),
    @XmlEnumValue("video/mpeg")
    VIDEO_MPEG("video/mpeg"),
    @XmlEnumValue("video/mp4")
    VIDEO_MP_4("video/mp4"),
    @XmlEnumValue("video/ogg")
    VIDEO_OGG("video/ogg"),
    @XmlEnumValue("video/webm")
    VIDEO_WEBM("video/webm"),

    /**
     * 
     *             Deve ser usado apenas para componentes que
     *             não se encaixem em nenhum outro mimeType dessa
     *             lista. A utilização desse item obriga o
     *             preenchimento do campo 'dadosComplementaresDoTipoDeArquivo'.
     *           
     * 
     */
    @XmlEnumValue("outro")
    OUTRO("outro");
    private final String value;

    MimeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MimeType fromValue(String v) {
        for (MimeType c: MimeType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
