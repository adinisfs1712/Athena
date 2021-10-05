package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.TipoCobranca;

public class TipoCobrancaRepository {

    private EntityManager em;

    public TipoCobrancaRepository(EntityManager em) {
	this.em = em;
    }

    public List<TipoCobranca> getTipoCobranca() {
	String query = "Select t from TipoCobranca t";
	return this.em.createQuery(query).getResultList();
    }

    public TipoCobranca tipoCobrancaById(Long id) {
	return this.em.find(TipoCobranca.class, id);
    }

}
