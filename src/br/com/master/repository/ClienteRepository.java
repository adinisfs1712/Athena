package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.master.entities.Cliente;
import br.com.master.entities.ClienteContato;

public class ClienteRepository {

    private EntityManager em;

    public ClienteRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Cliente cliente) {
	this.em.persist(cliente);
	this.em.flush();
    }

    public void alterar(Cliente cliente) {
	this.em.merge(cliente);
	this.em.flush();
    }

    public void excluir(Cliente cliente) {
	Cliente clienteTemp = new Cliente();
	clienteTemp = this.em.find(Cliente.class, cliente.getId());
	this.em.remove(clienteTemp);
    }

    public List<Cliente> allClientes() {
	String query = "Select c from Cliente c";
	return this.em.createQuery(query).getResultList();
    }

    public List<Cliente> clientesByNome(String rzSocial) {
	String query = "Select c from Cliente c where c.razaoSocial like :rzSocial";
	Query lista = em.createQuery(query);
	lista.setParameter("rzSocial", "%" + rzSocial + "%");
	return lista.getResultList();
    }

    public List<ClienteContato> clienteContatos(Cliente cliente) {
	String query = "Select c from ClienteContato c where c.cliente = :clienteId";
	Query lista = em.createQuery(query);
	lista.setParameter("clienteId", cliente);
	return lista.getResultList();
    }

    public Long countClientes() {
	String query = "select count(c) from Cliente c";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Cliente clienteById(Long id) {
	return this.em.find(Cliente.class, id);
    }

}
