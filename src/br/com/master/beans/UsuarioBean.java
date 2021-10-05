package br.com.master.beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;

import br.com.master.entities.Usuario;
import br.com.master.repository.UsuarioRepository;

@ManagedBean(name = "usuarioBean")
@ViewScoped
public class UsuarioBean {

    private String nome;
    private Usuario usuario;
    private UIPanel panelForm;
    private List<Usuario> usuarios = new ArrayList<Usuario>();
    private UsuarioRepository usuarioRepository;

    /*
     * Pesquisa Usuario por nome
     */
    public void pesquisar() {
	this.usuarios = usuarioRepository.allUsuariosByNome(this.nome);
    }

    /*
     * Preapra para adicionar novo
     */
    public void prepareAdicionar() {
	this.clear();
    }

    /*
     * referente botao voltar
     */
    public void voltar() {
	this.clear();
	this.pesquisar();
    }

    /*
     * limpar atributos
     */
    public void clear() {
	this.nome = null;
	this.usuario = new Usuario();
	this.cleanSubmittedValues(this.panelForm);
    }

    /*
     * Limpa os componentes filhos para que depois eles possam ser recriados
     * 
     * @param component
     */
    public void cleanSubmittedValues(UIComponent component) {
	component.getChildren().clear();
    }

    /*
     * Salva o usuario
     */
    public void salvar() {
	if (usuario.getId() == null) {
	    usuarioRepository.salvar(usuario);
	} else {
	    usuarioRepository.alterar(usuario);
	}
	this.pesquisar();

    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public UIPanel getPanelForm() {
	return panelForm;
    }

    public void setPanelForm(UIPanel panelForm) {
	this.panelForm = panelForm;
    }

    public UsuarioRepository getUsuarioRepository() {
	return usuarioRepository;
    }

    public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
	this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios() {
	return usuarios;
    }

}
