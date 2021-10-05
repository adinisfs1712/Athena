package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.master.entities.Cliente;
import br.com.master.entities.Funcionario;

public class FuncionarioRepository {

    private EntityManager em;

    public FuncionarioRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Funcionario funcionario) {
	this.em.persist(funcionario);
	this.em.flush();
    }

    public void alterar(Funcionario funcionario) {
	this.em.merge(funcionario);
	this.em.flush();
    }

    public void excluir(Funcionario funcionario) {
	Funcionario funcionarioTemp = new Funcionario();
	funcionarioTemp = this.em.find(Funcionario.class, funcionario.getId());
	this.em.remove(funcionarioTemp);
    }

    public List<Funcionario> allFuncionarios() {
	String query = "Select c from Funcionario c order by c.nome";
	return this.em.createQuery(query).getResultList();
    }

    public List<Funcionario> funcionariosByCliente(Cliente cliente) {
	String query = "Select c from Funcionario c where c.cliente = :cliente order by c.nome";
	Query lista = em.createQuery(query);
	lista.setParameter("cliente", cliente);
	return lista.getResultList();
    }

    public Long countFuncionarios(Cliente cliente) {
	String query = "select count(c) from Funcionario c where c.cliente = :cliente";
	Query lista = em.createQuery(query);
	lista.setParameter("cliente", cliente);
	return (Long) lista.getSingleResult();
    }

    public Funcionario funcionarioById(Long id) {
	return this.em.find(Funcionario.class, id);
    }

}
