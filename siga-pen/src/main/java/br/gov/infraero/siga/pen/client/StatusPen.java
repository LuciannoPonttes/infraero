package br.gov.infraero.siga.pen.client;


public enum StatusPen {
    AGUARDANDO_ENVIO_ARQUIVOS_DIGITAIS(1),
    ARQUIVOS_DIGITAIS_RECEBIDOS_SOLUCAO(2),
    METADADOS_RECEBIDOS_DESTINATARIO(3),
    ARQUIVOS_DIGITAIS_RECEBIDOS_DESTINATARIO(4),
    RECIBO_CONCLUSAO_TRAMITE_RECEB_SOLUCAO(5),
    RECIBO_CONCLUSAO_TRAMITE_RECEB_REMETENTE(6),
    CANCELADO_USUARIO(7),
    AGUARDANDO_CIENCIA(8),
    RECUSADO_DESTINATARIO(9),
    CANCELADO_AUTOMATICAMENTE(10);


    StatusPen(Integer idStatus){
        this.id = idStatus;
    }
    private Integer id;

    public Integer getId() {
        return id;
    }

}
