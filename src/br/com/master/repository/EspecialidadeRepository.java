package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Especialidade;

public class EspecialidadeRepository {

    private EntityManager em;

    public EspecialidadeRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Especialidade especialidade) {
	this.em.persist(especialidade);
	this.em.flush();
    }

    public void alterar(Especialidade especialidade) {
	this.em.merge(especialidade);
	this.em.flush();
    }

    public void excluir(Especialidade especialidade) {
	Especialidade especialidadeTemp = new Especialidade();
	especialidadeTemp = this.em.find(Especialidade.class,
		especialidade.getId());
	this.em.remove(especialidadeTemp);
    }

    public List allEspecialidadesByNome() {
	String query = "Select e from Especialidade e order by e.descricao ";
	return this.em.createQuery(query).getResultList();
    }

    public Long countEspecialidades() {
	String query = "select count(e) from Especialidade e";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Especialidade especialidadeById(Long id) {
	return this.em.find(Especialidade.class, id);
    }

}
