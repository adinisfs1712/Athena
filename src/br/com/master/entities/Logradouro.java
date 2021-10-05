package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "logradouros")
public class Logradouro extends BaseEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(length = 60, nullable = false)
    private String endereco;
    @Column(length = 40, nullable = false)
    private String bairro;
    @Column(length = 8, nullable = false)
    private String cep;
    @ManyToOne
    @JoinColumn(name = "municipios_id")
    private Municipio municipio;

    @Override
    public Long getId() {
	return null;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getEndereco() {
	return endereco;
    }

    public void setEndereco(String endereco) {
	this.endereco = endereco;
    }

    public String getBairro() {
	return bairro;
    }

    public void setBairro(String bairro) {
	this.bairro = bairro;
    }

    public String getCep() {
	return cep;
    }

    public void setCep(String cep) {
	this.cep = cep;
    }

    public Municipio getMunicipio() {
	return municipio;
    }

    public void setMunicipio(Municipio municipio) {
	this.municipio = municipio;
    }

}
