package br.com.master.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "pessoas")
public class Pessoa extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @OneToMany(mappedBy = "pessoa", targetEntity = PessoaContato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PessoaContato> pessoaContatos;

    public Pessoa() {

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

    public List<PessoaContato> getPessoaContatos() {
	return pessoaContatos;
    }

    public void setPessoaContatos(List<PessoaContato> pessoaContatos) {
	this.pessoaContatos = pessoaContatos;
    }

}
