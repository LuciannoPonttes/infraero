package br.gov.infraero.siga.pen.client;

import br.gov.infraero.siga.pen.client.util.PenProperties;

public enum MotivoRecusaEnum {

    FORMATO_NAO_SUPORTADO("01", PenProperties.getValue("pen.textorecusa.01")),
    COMPONENTE_CORROMPIDO("02", PenProperties.getValue("pen.textorecusa.02")),
    FALTA_COMPONENTE("03", PenProperties.getValue("pen.textorecusa.03")),
    ESPECIE_NAO_MAPEADA("04", PenProperties.getValue("pen.textorecusa.04")),
    OUTRO("99", PenProperties.getValue("pen.textorecusa.99"));

    private String codigo;
    private String descricao;

    MotivoRecusaEnum(String codigo, String descricao){
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
