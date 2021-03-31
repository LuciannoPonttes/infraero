package br.gov.infraero.siga.pen.client;

import java.util.Arrays;

public enum NivelAcessoEnum {

    PUBLICO(6, 1),
    RESTRITO(5, 2),
    RESERVADO(1, 3),
    SECRETO(2, 4),
    ULTRASSECRETO(7, 5);

    NivelAcessoEnum(Integer idSiga, Integer idPen){
        this.idSiga = idSiga;
        this.idPen = idPen;
    };

    private Integer idSiga;
    private Integer idPen;

    public Integer getIdSiga() {
        return idSiga;
    }

    public void setIdSiga(Integer idSiga) {
        this.idSiga = idSiga;
    }

    public Integer getIdPen() {
        return idPen;
    }

    public void setIdPen(Integer idPen) {
        this.idPen = idPen;
    }

    public static NivelAcessoEnum valueOf(Integer idPen){
        return Arrays.asList(NivelAcessoEnum.values()).stream().filter(n -> n.idPen == idPen).findFirst().orElse(NivelAcessoEnum.PUBLICO);
    }
}
