package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Cbo;

public class CboRepository {

    private EntityManager em;

    public CboRepository(EntityManager em) {
	this.em = em;
    }

    public List allCboByNome() {
	String query = "Select e from Cbo e order by e.descricao ";
	return this.em.createQuery(query).getResultList();
    }

    public Long countCbo() {
	String query = "select count(e) from Cbo e";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Cbo cboById(Long id) {
	return this.em.find(Cbo.class, id);
    }

}
