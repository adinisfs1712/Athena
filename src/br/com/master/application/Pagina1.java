package br.com.master.application;

import br.com.master.entities.Cliente;

public class Pagina1 {

	public static void main(String[] args) {

		System.out.println("primeira pagina");
		Cliente cliente = new Cliente();
		String cei = cliente.getCei();
		String nome = cliente.getNomeFantasia();
		System.out.println("CEI "+cei);
	}

}
