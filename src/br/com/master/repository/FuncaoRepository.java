package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Funcao;

public class FuncaoRepository {

    private EntityManager em;

    public FuncaoRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Funcao funcao) {
	this.em.persist(funcao);
	this.em.flush();
    }

    public void alterar(Funcao funcao) {
	this.em.merge(funcao);
	this.em.flush();
    }

    public void excluir(Funcao funcao) {
	Funcao funcaoTemp = new Funcao();
	funcaoTemp = this.em.find(Funcao.class, funcao.getId());
	this.em.remove(funcaoTemp);
    }

    public List<Funcao> allFuncoesByNome() {
	String query = "Select c from Funcao c order by c.descricao";
	return this.em.createQuery(query).getResultList();
    }

    public Long countFuncoes() {
	String query = "select count(c) from Funcao c";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Funcao funcaoById(Integer id) {
	return this.em.find(Funcao.class, id);
    }

}
