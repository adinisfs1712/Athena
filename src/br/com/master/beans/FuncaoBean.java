package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Funcao;
import br.com.master.repository.FuncaoRepository;

@ManagedBean(name = "funcaoBean")
@ViewScoped
public class FuncaoBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private Funcao funcao = new Funcao();
    private List<Funcao> listaFuncoes;
    private Funcao selectFuncao;

    @PostConstruct
    public void init() {
	carregarFuncoes();
    }

    public void carregarFuncoes() {
	if (listaFuncoes == null) {
	    FuncaoRepository repository = new FuncaoRepository(
		    this.getManager());
	    this.listaFuncoes = repository.allFuncoesByNome();
	}
    }

    public String limpar() {
	this.funcao = new Funcao();
	this.listaFuncoes = null;
	carregarFuncoes();
	return "funcao";
    }

    public String salvar() {
	FuncaoRepository funcaoRepository = new FuncaoRepository(getManager());
	if (funcao.getId() == null) {
	    funcaoRepository.salvar(funcao);
	} else {
	    funcaoRepository.alterar(funcao);
	}
	this.funcao = new Funcao();
	this.listaFuncoes = null;
	carregarFuncoes();
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("anotherKey", new FacesMessage(
		FacesMessage.SEVERITY_INFO, "Função Atualizada", ""));
	return "funcao";
    }

    public void excluir(Funcao funcao) {
	FuncaoRepository funcaoRepository = new FuncaoRepository(
		this.getManager());
	funcaoRepository.excluir(funcao);
	this.funcao = new Funcao();
	this.listaFuncoes = null;
	carregarFuncoes();
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("anotherKey", new FacesMessage(
		FacesMessage.SEVERITY_INFO, "Função Excluida", ""));
    }

    public String editar(Funcao funcao) {
	this.setFuncao(funcao);
	return "funcao?faces-redirect=true";
    }

    public Long getContarFuncao() {
	FuncaoRepository repository = new FuncaoRepository(this.getManager());
	return repository.countFuncoes();
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public Funcao getFuncao() {
	return funcao;
    }

    public void setFuncao(Funcao funcao) {
	this.funcao = funcao;
    }

    public List<Funcao> getListaFuncoes() {
	return listaFuncoes;
    }

    public Funcao getSelectFuncao() {
	return selectFuncao;
    }

    public void setSelectFuncao(Funcao selectFuncao) {
	this.selectFuncao = selectFuncao;
    }

}
