package br.com.master.enums;

public enum TipoPessoaEnum {

    F("Física"), J("Jurídica");

    private String pessoa;

    private TipoPessoaEnum(String pessoa) {
	this.pessoa = pessoa;
    }

    public String toString() {
	return this.pessoa;
    }

    public String getPessoa() {
	return pessoa;
    }

}