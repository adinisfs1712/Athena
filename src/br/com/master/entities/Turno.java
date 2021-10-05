package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TipoRisco entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "turnos")
public class Turno extends BaseEntity {

    // Fields
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "descricao", nullable = false, length = 20)
    private String descricao;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_inicial")
    private Date horaInicial;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_final")
    private Date horaFinal;

    // Constructors
    /** default constructor */
    public Turno() {
    }

    /** minimal constructor */
    public Turno(String descricao) {
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

    public Date getHoraInicial() {
	return horaInicial;
    }

    public void setHoraInicial(Date horaInicial) {
	this.horaInicial = horaInicial;
    }

    public Date getHoraFinal() {
	return horaFinal;
    }

    public void setHoraFinal(Date horaFinal) {
	this.horaFinal = horaFinal;
    }

}