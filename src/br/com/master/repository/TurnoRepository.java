package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Turno;

public class TurnoRepository {

    private EntityManager em;

    public TurnoRepository(EntityManager em) {
	this.em = em;
    }

    public List<Turno> getTurno() {
	String query = "Select var from Turno var";
	return this.em.createQuery(query).getResultList();
    }

    public Turno turnoById(Long id) {
	return this.em.find(Turno.class, id);
    }

}
