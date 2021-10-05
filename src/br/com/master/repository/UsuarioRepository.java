package br.com.master.repository;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.master.entities.Usuario;

public class UsuarioRepository {

    private EntityManager em;

    public UsuarioRepository(EntityManager em) {
	this.em = em;
    }

    public void salvar(Usuario usuario) {
	this.em.persist(usuario);
	this.em.flush();
    }

    public void alterar(Usuario usuario) {
	this.em.merge(usuario);
	this.em.flush();
    }

    public void excluir(Usuario usuario) {
	Usuario usuarioTemp = new Usuario();
	usuarioTemp = this.em.find(Usuario.class, usuario.getId());
	this.em.remove(usuarioTemp);
    }

    public List<Usuario> allUsuariosByNome(String name) {
	String query = "Select c from Usuario c order by c.nome where c.nome like name";
	return this.em.createQuery(query).getResultList();
    }

    public Long countUsuarios() {
	String query = "select count(c) from Usuario c";
	return (Long) this.em.createQuery(query).getSingleResult();
    }

    public Usuario usuarioById(Long id) {
	Usuario usuarioTemp = new Usuario();
	System.out.println("reposit indo");
	return this.em.find(Usuario.class, id);
    }

    public Long findByNome(String name) {
	String query = "select u from Usuario u where upper(u.nome) like upper(name)";
	return (Long) this.em.createQuery(query).getSingleResult();
    }
}
