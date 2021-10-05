package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Situacao;

public class SituacaoRepository {

    private EntityManager em;

    public SituacaoRepository(EntityManager em) {
	this.em = em;
    }

    public List allSituacao() {
	String query = "Select s from Situacao s";
	return this.em.createQuery(query).getResultList();
    }

    public Situacao situacaoById(Long id) {
	return this.em.find(Situacao.class, id);
    }

}
