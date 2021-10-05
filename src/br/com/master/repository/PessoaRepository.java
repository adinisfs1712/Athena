package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.master.entities.Pessoa;
import br.com.master.entities.PessoaContato;

public class PessoaRepository {

    private EntityManager em;

    public PessoaRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Pessoa pessoa) {
	this.em.persist(pessoa);
	this.em.flush();
    }

    public void alterar(Pessoa pessoa) {
	this.em.merge(pessoa);
	this.em.flush();
    }

    public void excluir(Pessoa pessoa) {
	Pessoa pessoaTemp = new Pessoa();
	pessoaTemp = this.em.find(Pessoa.class, pessoa.getId());
	this.em.remove(pessoaTemp);
    }

    public List<Pessoa> allPessoas() {
	String query = "Select c from Pessoa c";
	return this.em.createQuery(query).getResultList();
    }

    public List<Pessoa> pessoasByNome(String rzSocial) {
	String query = "Select c from Pessoa c where c.razaoSocial like :rzSocial";
	Query lista = em.createQuery(query);
	lista.setParameter("rzSocial", "%" + rzSocial + "%");
	return lista.getResultList();
    }

    public List<PessoaContato> pessoaContatos(Pessoa pessoa) {
	String query = "Select c from PessoaContato c where c.pessoa = :pessoaId";
	Query lista = em.createQuery(query);
	lista.setParameter("pessoaId", "%" + pessoa.getId() + "%");
	return lista.getResultList();
    }

    public Long countPessoas() {
	String query = "select count(c) from Pessoa c";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Pessoa pessoaById(Long id) {
	return this.em.find(Pessoa.class, id);
    }

}
