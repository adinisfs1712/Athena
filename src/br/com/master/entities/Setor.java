package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "setores")
public class Setor extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column
    private String descricao;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "clientes_setores", joinColumns = { @JoinColumn(name = "setores_id") }, inverseJoinColumns = { @JoinColumn(name = "clientes_id") })
    private List<Cliente> clientes;

    // Constructors

    /** default constructor */
    public Setor() {
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

    public List<Cliente> getClientes() {
	return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
	this.clientes = clientes;
    }

    /**
     * full constructor public Setor(String codigo, String descricao,
     * Set<Funcionario> funcionarios) { this.codigo = codigo; this.descricao =
     * descricao; this.funcionarios = funcionarios; }
     */

    // Property accessors

}