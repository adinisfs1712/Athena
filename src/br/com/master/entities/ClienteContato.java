package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the cliente_contatos database table.
 * 
 */
@Entity
@Table(name = "cliente_contatos")
public class ClienteContato extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String nome;

    private String setor;

    private String telefone;

    private String email;

    // bi-directional many-to-one association to Cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public ClienteContato() {
    }

    public String getNome() {
	return this.nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getSetor() {
	return this.setor;
    }

    public void setSetor(String setor) {
	this.setor = setor;
    }

    public String getTelefone() {
	return this.telefone;
    }

    public void setTelefone(String telefone) {
	this.telefone = telefone;
    }

    public Cliente getCliente() {
	return this.cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Long getId() {
	return id;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}