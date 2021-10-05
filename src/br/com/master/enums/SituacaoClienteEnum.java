package br.com.master.enums;

public enum SituacaoClienteEnum {

    A("Ativo"), C("Cancelamento de Contrato"), P("Pendência de Pagamento"), Q(
	    "Quebra de Contrato");

    public String getCodigo() {
	return this.toString();
    }

    private String descricao;

    private SituacaoClienteEnum(String descricao) {
	this.descricao = descricao;
    }

    public String toString() {
	return this.descricao;
    }

    public String getDescricao() {
	return descricao;
    }

}
