package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.UnidFederacao;

public class UnidFederacaoRepository {

    private EntityManager em;

    public UnidFederacaoRepository(EntityManager em) {
	this.em = em;
    }

    public List getUfs() {
	String query = "Select uf from UnidFederacao uf order by uf.sigla";
	return this.em.createQuery(query).getResultList();
    }

    public UnidFederacao unidFederacaoById(Long id) {
	return this.em.find(UnidFederacao.class, id);
    }

}
