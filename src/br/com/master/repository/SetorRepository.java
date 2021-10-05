package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Setor;

public class SetorRepository {

    private EntityManager em;

    public SetorRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Setor setor) {
	this.em.persist(setor);
	this.em.flush();
    }

    public void alterar(Setor setor) {
	this.em.merge(setor);
	this.em.flush();
    }

    public void excluir(Setor setor) {
	Setor setorTemp = new Setor();
	setorTemp = this.em.find(Setor.class, setor.getId());
	this.em.remove(setorTemp);
    }

    public List allSetoresByNome() {
	String query = "Select e from Setor e ";
	return this.em.createQuery(query).getResultList();
    }

    public Long countSetores() {
	String query = "select count(e) from Setor e";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Setor setorById(Long id) {
	return this.em.find(Setor.class, id);
    }

}
