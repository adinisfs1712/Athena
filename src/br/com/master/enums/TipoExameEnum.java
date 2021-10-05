package br.com.master.enums;

public enum TipoExameEnum {
    A("Admissional"), P("Peri�dico"), R("Retorno ao trabalho"), M(
	    "Mudan�a de fun��o"), D("Demissional");

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
