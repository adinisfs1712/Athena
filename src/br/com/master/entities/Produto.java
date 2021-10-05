package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the produtos database table.
 * 
 */
@Entity
@Table(name = "produtos")
public class Produto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String descricao;

    // bi-directional many-to-many association to Cliente
    // @ManyToMany
    // @JoinTable(name = "clientes_produtos", joinColumns = { @JoinColumn(name =
    // "produtos_id") }, inverseJoinColumns = { @JoinColumn(name =
    // "clientes_id") })
    // private List<Cliente> clientes;

    public Produto() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getDescricao() {
	return this.descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

}