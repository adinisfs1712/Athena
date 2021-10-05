package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.MotivoExclusao;

public class MotivoExclusaoRepository {

    private EntityManager em;

    public MotivoExclusaoRepository(EntityManager em) {
	this.em = em;
    }

    public List allMotivoExclusao() {
	String query = "Select me from MotivoExclusao me";
	return this.em.createQuery(query).getResultList();
    }

    public MotivoExclusao situacaoById(Long id) {
	return this.em.find(MotivoExclusao.class, id);
    }

}
