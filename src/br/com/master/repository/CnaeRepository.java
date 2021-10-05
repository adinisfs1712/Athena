package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Cnae;

public class CnaeRepository {

	private EntityManager em;

	public CnaeRepository(EntityManager em) {
		this.em = em;
	}

	public void salvar(Cnae cnae) {
		this.em.persist(cnae);
		this.em.flush();
	}

	public void alterar(Cnae cnae) {
		this.em.merge(cnae);
		this.em.flush();
	}

	public void excluir(Cnae cnae) {
		Cnae cnaeTemp = new Cnae();
		cnaeTemp = this.em.find(Cnae.class, cnae.getId());
		this.em.remove(cnaeTemp);
	}

	@SuppressWarnings("unchecked")
	public List<Cnae> allCnae() {
		String query = "Select c from Cnae c";
		return this.em.createQuery(query).getResultList();
	}

	public Long countCnae() {
		String query = "select count(c) from Cnae c";
		return (Long) this.em.createQuery(query).getSingleResult();
	}

	public Cnae cnaeById(Long id) {
		return this.em.find(Cnae.class, id);
	}

}
