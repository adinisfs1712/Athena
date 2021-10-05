package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Funcao entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "funcoes")
public class Funcao extends BaseEntity {

    // Fields

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column
    private String descricao;

    // @Column(name = "cbo_id")
    // private Cbo cbo;

    // private Set<Funcionario> funcionarios = new HashSet<Funcionario>(0);

    // Constructors

    /** default constructor */
    public Funcao() {
    }

    /** minimal constructor */
    public Funcao(String descricao) {
	this.descricao = descricao;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getDescricao() {
	return descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

}