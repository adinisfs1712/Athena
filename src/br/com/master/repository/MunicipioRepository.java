package br.com.master.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.master.entities.Municipio;

public class MunicipioRepository {

    private EntityManager em;

    public MunicipioRepository(EntityManager em) {
	this.em = em;
    }

    public Long getCountMunicipios() {
	String query = "select count(m) from Municipio m";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Municipio municipioById(Long id) {
	return this.em.find(Municipio.class, id);
    }

    public List<Municipio> findByParam(String query, Map<String, Object> params) {
	Query q = em.createQuery(query);

	for (String chave : params.keySet()) {
	    q.setParameter(chave, params.get(chave));
	}

	return q.getResultList();
    }

}
