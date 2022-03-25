package br.com.dock.desafio2.application.messages;

public enum MessagesEnum {
    BF_DESAFIO2_00001I("Iniciando a execucao da service "),
    BF_DESAFIO2_00002I("Fim da execucao da service "),
    BF_DESAFIO2_00003E("O valor do saque não pode ser zerado"),
    BF_DESAFIO2_00004E("O valor do saque excede o saldo em conta"),
    BF_DESAFIO2_00005E("O valor do saque excede o limite em conta"),
    BF_DESAFIO2_00006E("Tentativa de saque para conta nao ativa"),
    BF_DESAFIO2_00007E("Ocorreu um erro ao inserir o(s) dado(s) no banco"),
    BF_DESAFIO2_00008I("{} persistida: {}"),
    BF_DESAFIO2_00009E("Ocorreu um erro ao recuperar o(s) dado(s) do banco"),
    BF_DESAFIO2_00010E("%s %s nao existente no banco");

    private String code;
    private String message;

    private static final String CONST_HIFEN = " - ";

    MessagesEnum(String message) {
        this.message = message;
        this.code = this.name();
    }

    public String getCodAndDescription() {
        return code + CONST_HIFEN + message;
    }
}
