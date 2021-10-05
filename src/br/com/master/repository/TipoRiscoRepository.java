package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.TipoRisco;

public class TipoRiscoRepository {

    private EntityManager em;

    public TipoRiscoRepository(EntityManager em) {
	this.em = em;
    }

    public List<TipoRisco> getTipoRisco() {
	String query = "Select tr from TipoRisco tr";
	return this.em.createQuery(query).getResultList();
    }

    public TipoRisco tipoRiscoById(Long id) {
	return this.em.find(TipoRisco.class, id);
    }

}
