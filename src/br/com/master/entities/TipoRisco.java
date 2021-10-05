package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TipoRisco entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "tipos_riscos")
public class TipoRisco extends BaseEntity {

    // Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "descricao", nullable = false, length = 20)
    private String descricao;

    // Constructors
    /** default constructor */
    public TipoRisco() {
    }

    /** minimal constructor */
    public TipoRisco(String descricao) {
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