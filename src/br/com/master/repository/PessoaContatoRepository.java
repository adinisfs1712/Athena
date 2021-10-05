package br.com.master.repository;

import javax.persistence.EntityManager;

import br.com.master.entities.ClienteContato;

public class PessoaContatoRepository {

    private EntityManager em;

    public PessoaContatoRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(ClienteContato contato) {
	this.em.persist(contato);
	this.em.flush();
    }

    public void alterar(ClienteContato contato) {
	this.em.merge(contato);
	this.em.flush();
    }

    public void excluir(ClienteContato contato) {
	ClienteContato contatoTemp = new ClienteContato();
	contatoTemp = this.em.find(ClienteContato.class, contato.getId());
	this.em.remove(contatoTemp);
    }

    public ClienteContato contatoById(Integer id) {
	return this.em.find(ClienteContato.class, id);
    }

}
