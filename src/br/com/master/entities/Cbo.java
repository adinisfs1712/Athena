package br.com.master.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the cbo database table.
 * 
 */
@Entity
@Table(name = "cbo")
public class Cbo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;
    private String atividade;

    @Column(name = "cbo_ocupacao")
    private String cboOcupacao;

    private String codigo;
    private String codCbo;

    private String descricao;

    public Cbo() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAtividade() {
	return this.atividade;
    }

    public void setAtividade(String atividade) {
	this.atividade = atividade;
    }

    public String getCboOcupacao() {
	return this.cboOcupacao;
    }

    public void setCboOcupacao(String cboOcupacao) {
	this.cboOcupacao = cboOcupacao;
    }

    public String getCodigo() {
	return this.codigo;
    }

    public void setCodigo(String codigo) {
	this.codigo = codigo;
    }

    public String getDescricao() {
	return this.descricao;
    }

    public void setDescricao(String descricao) {
	this.descricao = descricao;
    }

    public String getCodCbo() {
	codCbo = codigo + "-" + cboOcupacao;
	return codCbo;
    }

    public void setCodCbo(String codCbo) {
	this.codCbo = codCbo;
    }

}