
package br.gov.infraero.siga.pen.client.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 *         Espécie documental, identificada por um código
 *         e o nome da espécie no produtor do artefato.
 *       
 * 
 * <p>Java class for especie complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="especie">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigo">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;enumeration value="1"/>
 *               &lt;enumeration value="2"/>
 *               &lt;enumeration value="3"/>
 *               &lt;enumeration value="4"/>
 *               &lt;enumeration value="5"/>
 *               &lt;enumeration value="6"/>
 *               &lt;enumeration value="7"/>
 *               &lt;enumeration value="8"/>
 *               &lt;enumeration value="9"/>
 *               &lt;enumeration value="10"/>
 *               &lt;enumeration value="11"/>
 *               &lt;enumeration value="12"/>
 *               &lt;enumeration value="13"/>
 *               &lt;enumeration value="14"/>
 *               &lt;enumeration value="15"/>
 *               &lt;enumeration value="16"/>
 *               &lt;enumeration value="17"/>
 *               &lt;enumeration value="18"/>
 *               &lt;enumeration value="19"/>
 *               &lt;enumeration value="20"/>
 *               &lt;enumeration value="21"/>
 *               &lt;enumeration value="22"/>
 *               &lt;enumeration value="23"/>
 *               &lt;enumeration value="24"/>
 *               &lt;enumeration value="25"/>
 *               &lt;enumeration value="26"/>
 *               &lt;enumeration value="27"/>
 *               &lt;enumeration value="28"/>
 *               &lt;enumeration value="29"/>
 *               &lt;enumeration value="30"/>
 *               &lt;enumeration value="31"/>
 *               &lt;enumeration value="32"/>
 *               &lt;enumeration value="33"/>
 *               &lt;enumeration value="34"/>
 *               &lt;enumeration value="35"/>
 *               &lt;enumeration value="36"/>
 *               &lt;enumeration value="37"/>
 *               &lt;enumeration value="38"/>
 *               &lt;enumeration value="39"/>
 *               &lt;enumeration value="40"/>
 *               &lt;enumeration value="41"/>
 *               &lt;enumeration value="42"/>
 *               &lt;enumeration value="43"/>
 *               &lt;enumeration value="44"/>
 *               &lt;enumeration value="45"/>
 *               &lt;enumeration value="46"/>
 *               &lt;enumeration value="47"/>
 *               &lt;enumeration value="48"/>
 *               &lt;enumeration value="49"/>
 *               &lt;enumeration value="50"/>
 *               &lt;enumeration value="51"/>
 *               &lt;enumeration value="52"/>
 *               &lt;enumeration value="53"/>
 *               &lt;enumeration value="54"/>
 *               &lt;enumeration value="55"/>
 *               &lt;enumeration value="56"/>
 *               &lt;enumeration value="57"/>
 *               &lt;enumeration value="58"/>
 *               &lt;enumeration value="59"/>
 *               &lt;enumeration value="60"/>
 *               &lt;enumeration value="61"/>
 *               &lt;enumeration value="62"/>
 *               &lt;enumeration value="63"/>
 *               &lt;enumeration value="64"/>
 *               &lt;enumeration value="65"/>
 *               &lt;enumeration value="66"/>
 *               &lt;enumeration value="67"/>
 *               &lt;enumeration value="68"/>
 *               &lt;enumeration value="69"/>
 *               &lt;enumeration value="70"/>
 *               &lt;enumeration value="71"/>
 *               &lt;enumeration value="72"/>
 *               &lt;enumeration value="73"/>
 *               &lt;enumeration value="74"/>
 *               &lt;enumeration value="75"/>
 *               &lt;enumeration value="76"/>
 *               &lt;enumeration value="77"/>
 *               &lt;enumeration value="78"/>
 *               &lt;enumeration value="79"/>
 *               &lt;enumeration value="80"/>
 *               &lt;enumeration value="81"/>
 *               &lt;enumeration value="82"/>
 *               &lt;enumeration value="83"/>
 *               &lt;enumeration value="84"/>
 *               &lt;enumeration value="85"/>
 *               &lt;enumeration value="86"/>
 *               &lt;enumeration value="87"/>
 *               &lt;enumeration value="88"/>
 *               &lt;enumeration value="89"/>
 *               &lt;enumeration value="90"/>
 *               &lt;enumeration value="91"/>
 *               &lt;enumeration value="92"/>
 *               &lt;enumeration value="93"/>
 *               &lt;enumeration value="94"/>
 *               &lt;enumeration value="95"/>
 *               &lt;enumeration value="96"/>
 *               &lt;enumeration value="97"/>
 *               &lt;enumeration value="98"/>
 *               &lt;enumeration value="99"/>
 *               &lt;enumeration value="100"/>
 *               &lt;enumeration value="101"/>
 *               &lt;enumeration value="102"/>
 *               &lt;enumeration value="103"/>
 *               &lt;enumeration value="104"/>
 *               &lt;enumeration value="105"/>
 *               &lt;enumeration value="106"/>
 *               &lt;enumeration value="107"/>
 *               &lt;enumeration value="108"/>
 *               &lt;enumeration value="109"/>
 *               &lt;enumeration value="110"/>
 *               &lt;enumeration value="111"/>
 *               &lt;enumeration value="112"/>
 *               &lt;enumeration value="113"/>
 *               &lt;enumeration value="114"/>
 *               &lt;enumeration value="115"/>
 *               &lt;enumeration value="116"/>
 *               &lt;enumeration value="117"/>
 *               &lt;enumeration value="118"/>
 *               &lt;enumeration value="119"/>
 *               &lt;enumeration value="120"/>
 *               &lt;enumeration value="121"/>
 *               &lt;enumeration value="122"/>
 *               &lt;enumeration value="123"/>
 *               &lt;enumeration value="124"/>
 *               &lt;enumeration value="125"/>
 *               &lt;enumeration value="126"/>
 *               &lt;enumeration value="127"/>
 *               &lt;enumeration value="128"/>
 *               &lt;enumeration value="129"/>
 *               &lt;enumeration value="130"/>
 *               &lt;enumeration value="131"/>
 *               &lt;enumeration value="132"/>
 *               &lt;enumeration value="133"/>
 *               &lt;enumeration value="134"/>
 *               &lt;enumeration value="135"/>
 *               &lt;enumeration value="136"/>
 *               &lt;enumeration value="137"/>
 *               &lt;enumeration value="138"/>
 *               &lt;enumeration value="139"/>
 *               &lt;enumeration value="140"/>
 *               &lt;enumeration value="141"/>
 *               &lt;enumeration value="142"/>
 *               &lt;enumeration value="143"/>
 *               &lt;enumeration value="144"/>
 *               &lt;enumeration value="145"/>
 *               &lt;enumeration value="146"/>
 *               &lt;enumeration value="147"/>
 *               &lt;enumeration value="148"/>
 *               &lt;enumeration value="149"/>
 *               &lt;enumeration value="150"/>
 *               &lt;enumeration value="151"/>
 *               &lt;enumeration value="152"/>
 *               &lt;enumeration value="153"/>
 *               &lt;enumeration value="154"/>
 *               &lt;enumeration value="155"/>
 *               &lt;enumeration value="156"/>
 *               &lt;enumeration value="157"/>
 *               &lt;enumeration value="158"/>
 *               &lt;enumeration value="159"/>
 *               &lt;enumeration value="160"/>
 *               &lt;enumeration value="161"/>
 *               &lt;enumeration value="162"/>
 *               &lt;enumeration value="163"/>
 *               &lt;enumeration value="164"/>
 *               &lt;enumeration value="165"/>
 *               &lt;enumeration value="166"/>
 *               &lt;enumeration value="167"/>
 *               &lt;enumeration value="168"/>
 *               &lt;enumeration value="169"/>
 *               &lt;enumeration value="170"/>
 *               &lt;enumeration value="171"/>
 *               &lt;enumeration value="172"/>
 *               &lt;enumeration value="173"/>
 *               &lt;enumeration value="174"/>
 *               &lt;enumeration value="175"/>
 *               &lt;enumeration value="176"/>
 *               &lt;enumeration value="177"/>
 *               &lt;enumeration value="178"/>
 *               &lt;enumeration value="179"/>
 *               &lt;enumeration value="180"/>
 *               &lt;enumeration value="181"/>
 *               &lt;enumeration value="182"/>
 *               &lt;enumeration value="183"/>
 *               &lt;enumeration value="184"/>
 *               &lt;enumeration value="185"/>
 *               &lt;enumeration value="186"/>
 *               &lt;enumeration value="999"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="nomeNoProdutor" type="{http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum}nome"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "especie", namespace = "http://pen.planejamento.gov.br/interoperabilidade/soap/v2/comum", propOrder = {
    "codigo",
    "nomeNoProdutor"
})
public class Especie {

    @XmlElement(required = true)
    protected String codigo;
    @XmlElement(required = true)
    protected String nomeNoProdutor;

    /**
     * Gets the value of the codigo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Sets the value of the codigo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigo(String value) {
        this.codigo = value;
    }

    /**
     * Gets the value of the nomeNoProdutor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeNoProdutor() {
        return nomeNoProdutor;
    }

    /**
     * Sets the value of the nomeNoProdutor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeNoProdutor(String value) {
        this.nomeNoProdutor = value;
    }

}
