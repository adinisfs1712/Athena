package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.EstadoCivil;

public class EstadoCivilRepository {

    private EntityManager em;

    public EstadoCivilRepository(EntityManager em) {
	this.em = em;
    }

    public List<EstadoCivil> getEstadoCivil() {
	String query = "Select ec from EstadoCivil ec";
	return this.em.createQuery(query).getResultList();
    }

    public EstadoCivil estadoCivilById(Long id) {
	return this.em.find(EstadoCivil.class, id);
    }

}
