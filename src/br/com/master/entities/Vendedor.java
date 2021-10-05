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
 * The persistent class for the vendedores database table.
 * 
 */
@Entity
@Table(name = "vendedores")
public class Vendedor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String nome;

    // bi-directional many-to-one association to Empresa
    @ManyToOne
    @JoinColumn(name = "empresas_id")
    private Empresa empresa;

    public Vendedor() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return this.nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Empresa getEmpresa() {
	return this.empresa;
    }

    public void setEmpresa(Empresa empresa) {
	this.empresa = empresa;
    }

}