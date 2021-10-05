package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Empresa;
import br.com.master.entities.Endereco;

public class EmpresaRepository {

    private EntityManager em;

    public EmpresaRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Empresa empresa) {
	this.em.persist(empresa);
	this.em.flush();
    }

    public void alterar(Empresa empresa) {
	this.em.merge(empresa);
	this.em.flush();
    }

    public void excluir(Empresa empresa) {
	Empresa empresaTemp = new Empresa();
	empresaTemp = this.em.find(Empresa.class, empresa.getId());
	Endereco enderecoTemp = new Endereco();
	enderecoTemp = this.em.find(Endereco.class, empresa.getEndereco()
		.getId());
	this.em.remove(empresaTemp);
    }

    public List allEmpresasByNome() {
	String query = "Select e from Empresa e order by e.razaoSocial";
	return this.em.createQuery(query).getResultList();
    }

    public Long countEmpresas() {
	String query = "select count(e) from Empresa e";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Empresa empresaById(Long id) {
	return this.em.find(Empresa.class, id);
    }

}
