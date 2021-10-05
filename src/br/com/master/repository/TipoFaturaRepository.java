package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.TipoFatura;

public class TipoFaturaRepository {

    private EntityManager em;

    public TipoFaturaRepository(EntityManager em) {
	this.em = em;
    }

    public List<TipoFatura> getTipoFatura() {
	String query = "Select t from TipoFatura t";
	return this.em.createQuery(query).getResultList();
    }

    public TipoFatura tipoFaturaById(Long id) {
	return this.em.find(TipoFatura.class, id);
    }

}
