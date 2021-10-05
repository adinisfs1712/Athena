package br.com.master.enums;

public enum TipoEnderecoEnum {

	com("Comercial"), res("Residencial"), cob("Cobrança"), ent("Entrega");
	
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	private TipoEnderecoEnum(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return getTipo();

	}

}
