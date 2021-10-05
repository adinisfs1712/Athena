package br.com.master.repository;

import javax.persistence.EntityManager;

import br.com.master.entities.Endereco;

public class EnderecoRepository {

    private EntityManager em;

    public EnderecoRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Endereco endereco) {
	this.em.persist(endereco);
	this.em.flush();
    }

    public void alterar(Endereco endereco) {
	this.em.merge(endereco);
	this.em.flush();
    }

    public void excluir(Endereco endereco) {
	Endereco enderecoTemp = new Endereco();
	enderecoTemp = this.em.find(Endereco.class, endereco.getId());
	this.em.remove(enderecoTemp);
    }

    public Endereco enderecoById(Integer id) {
	return this.em.find(Endereco.class, id);
    }

}
