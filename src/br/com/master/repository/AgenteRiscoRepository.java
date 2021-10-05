package br.com.master.repository;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.master.entities.AgenteRisco;

public class AgenteRiscoRepository {

    private EntityManager em;

    public AgenteRiscoRepository(EntityManager em) {
	this.em = em;
    }

    public Long countAgenteRiscos() {
	String query = "select count(m) from AgenteRisco m";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public AgenteRisco agenteRiscoById(Long id) {
	return this.em.find(AgenteRisco.class, id);
    }

    public List<AgenteRisco> findByParam(String query,
	    Map<String, Object> params) {
	Query q = em.createQuery(query);

	for (String chave : params.keySet()) {
	    q.setParameter(chave, params.get(chave));
	}

	return q.getResultList();
    }

    public void salvar(AgenteRisco agenteRisco) {
	this.em.persist(agenteRisco);
	this.em.flush();
    }

    public void alterar(AgenteRisco agenteRisco) {
	this.em.merge(agenteRisco);
	this.em.flush();
    }

    public void excluir(AgenteRisco agenteRisco) {
	AgenteRisco agenteTemp = new AgenteRisco();
	agenteTemp = this.em.find(AgenteRisco.class, agenteRisco.getId());
	this.em.remove(agenteTemp);
    }

}
