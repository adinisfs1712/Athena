package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.TipoContrato;

public class TipoContratoRepository {

    private EntityManager em;

    public TipoContratoRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(TipoContrato tipoContrato) {
	this.em.persist(tipoContrato);
	this.em.flush();
    }

    public void alterar(TipoContrato tipoContrato) {
	this.em.merge(tipoContrato);
	this.em.flush();
    }

    public void excluir(TipoContrato tpContrato) {
	TipoContrato tpContratoTemp = new TipoContrato();
	tpContratoTemp = this.em.find(TipoContrato.class, tpContrato.getId());
	this.em.remove(tpContratoTemp);
    }

    public List<TipoContrato> getTipoContrato() {
	String query = "Select t from TipoContrato t";
	return this.em.createQuery(query).getResultList();
    }

    public TipoContrato tipoContratoById(Long id) {
	return this.em.find(TipoContrato.class, id);
    }

}
