package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "fornecedor_contatos")
public class FornecedorContato extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String nome;

    private String cargo;

    private String telefone;

    private String email;

    // bi-directional many-to-one association to Cliente
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fornecedores_id")
    private Fornecedor fornecedor;

    public FornecedorContato() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public String getCargo() {
	return cargo;
    }

    public void setCargo(String cargo) {
	this.cargo = cargo;
    }

    public String getTelefone() {
	return telefone;
    }

    public void setTelefone(String telefone) {
	this.telefone = telefone;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Fornecedor getFornecedor() {
	return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
	this.fornecedor = fornecedor;
    }

}