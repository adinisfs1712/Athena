package br.com.master.enums;

public enum TipoExameEnum {
    A("Admissional"), P("Periódico"), R("Retorno ao trabalho"), M(
	    "Mudança de função"), D("Demissional");

    private String tipo;

    public String getTipo() {
	return tipo;
    }

    private TipoExameEnum(String tipo) {
	this.tipo = tipo;
    }

    @Override
    public String toString() {
	return getTipo();

    }
}
