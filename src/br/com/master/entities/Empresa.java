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

@Table(name = "empresas")
@Entity
public class Empresa extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "razao_social", length = 60, nullable = false)
    private String razaoSocial;
    @Column(name = "nome_fantasia", length = 20)
    private String nomeFantasia;
    @Column(length = 15)
    private String telefone1;
    @Column(length = 15)
    private String telefone2;
    @Column(length = 14, nullable = false, unique = true)
    private String cnpj;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enderecos_id")
    private Endereco endereco;

    public Empresa() {

    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getRazaoSocial() {
	return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
	this.razaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
	return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
	this.nomeFantasia = nomeFantasia;
    }

    public String getTelefone1() {
	return telefone1;
    }

    public void setTelefone1(String telefone1) {
	this.telefone1 = telefone1;
    }

    public String getTelefone2() {
	return telefone2;
    }

    public void setTelefone2(String telefone2) {
	this.telefone2 = telefone2;
    }

    public String getCnpj() {
	return cnpj;
    }

    public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

}
