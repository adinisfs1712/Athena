package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.SituacaoCliente;

public class SituacaoClienteRepository {

    private EntityManager em;

    public SituacaoClienteRepository(EntityManager em) {
	this.em = em;
    }

    public List allSituacao() {
	String query = "Select s from Situacao s";
	return this.em.createQuery(query).getResultList();
    }

    public SituacaoCliente situacaoById(Long id) {
	return this.em.find(SituacaoCliente.class, id);
    }

}
