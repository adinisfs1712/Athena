package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.master.entities.Fornecedor;

public class FornecedorRepository {

    private EntityManager em;

    public FornecedorRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Fornecedor fornecedor) {
	this.em.persist(fornecedor);
	this.em.flush();
    }

    public void alterar(Fornecedor fornecedor) {
	this.em.merge(fornecedor);
	this.em.flush();
    }

    public void excluir(Fornecedor fornecedor) {
	Fornecedor fornecedorTemp = new Fornecedor();
	fornecedorTemp = this.em.find(Fornecedor.class, fornecedor.getId());
	this.em.remove(fornecedorTemp);
    }

    public List<Fornecedor> allFornecedores() {
	String query = "Select c from Fornecedor c";
	return this.em.createQuery(query).getResultList();
    }

    public List<Fornecedor> fornecedoresByNome(String rzSocial) {
	String query = "Select c from Fornecedor c where c.razaoSocial like :rzSocial";
	Query lista = em.createQuery(query);
	lista.setParameter("rzSocial", "%" + rzSocial + "%");
	return lista.getResultList();
    }

    public Long countFornecedores() {
	String query = "select count(c) from Fornecedor c";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Fornecedor fornecedorById(Long id) {
	return this.em.find(Fornecedor.class, id);
    }

}
