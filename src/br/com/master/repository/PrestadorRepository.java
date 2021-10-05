package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.master.entities.Prestador;

public class PrestadorRepository {

    private EntityManager em;

    public PrestadorRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Prestador prestador) {
	this.em.persist(prestador);
	this.em.flush();
    }

    public void alterar(Prestador prestador) {
	this.em.merge(prestador);
	this.em.flush();
    }

    public void excluir(Prestador prestador) {
	Prestador prestadorTemp = new Prestador();
	prestadorTemp = this.em.find(Prestador.class, prestador.getId());
	this.em.remove(prestadorTemp);
    }

    public List<Prestador> allPrestadores() {
	String query = "Select c from Prestador c";
	return this.em.createQuery(query).getResultList();
    }

    public List<Prestador> prestadoresByNome(String rzSocial) {
	String query = "Select c from Prestador c where c.razaoSocial like :rzSocial";
	Query lista = em.createQuery(query);
	lista.setParameter("rzSocial", "%" + rzSocial + "%");
	return lista.getResultList();
    }

    public Long countPrestadores() {
	String query = "select count(c) from Prestador c";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Prestador prestadorById(Long id) {
	return this.em.find(Prestador.class, id);
    }

}
