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
 * AgenteRisco entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "agentes_riscos")
public class AgenteRisco extends BaseEntity {

    // Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "tipos_riscos_id", nullable = false)
    private TipoRisco tipoRisco;

    // Constructors

    /** default constructor */
    public AgenteRisco() {
    }

    /** full constructor */
    public AgenteRisco(TipoRisco tipoRisco, String descricao) {
	this.tipoRisco = tipoRisco;
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

    public TipoRisco getTipoRisco() {
	return tipoRisco;
    }

    public void setTipoRisco(TipoRisco tipoRisco) {
	this.tipoRisco = tipoRisco;
    }

}