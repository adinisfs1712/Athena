package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.TipoFornecedor;

public class TipoFornecedorRepository {

    private EntityManager em;

    public TipoFornecedorRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(TipoFornecedor tipoFornecedor) {
	this.em.persist(tipoFornecedor);
	this.em.flush();
    }

    public void alterar(TipoFornecedor tipoFornecedor) {
	this.em.merge(tipoFornecedor);
	this.em.flush();
    }

    public void excluir(TipoFornecedor tpFornecedor) {
	TipoFornecedor tpFornecedorTemp = new TipoFornecedor();
	tpFornecedorTemp = this.em.find(TipoFornecedor.class,
		tpFornecedor.getId());
	this.em.remove(tpFornecedorTemp);
    }

    public List<TipoFornecedor> getTipoFornecedor() {
	String query = "Select t from TipoFornecedor t";
	return this.em.createQuery(query).getResultList();
    }

    public TipoFornecedor tipoFornecedorById(Long id) {
	return this.em.find(TipoFornecedor.class, id);
    }

}
