package br.com.master.enums;

public enum SexoEnum {

    M("Masculino"), F("Feminino");

    private final String sexo;

    private SexoEnum(String sexo) {
	this.sexo = sexo;
    }

    public String toString() {
	return this.sexo;
    }

    public String getSexo() {
	return sexo;
    }

}