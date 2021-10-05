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

import br.com.master.entities.Setor;
import br.com.master.repository.SetorRepository;

@ManagedBean(name = "setorBean")
@ViewScoped
public class SetorBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private static final String PESQUISAR_STATE = "pesquisar";
    private static final String ADICIONAR_STATE = "adicionar";
    private static final String EDITAR_STATE = "editar";
    private String currentState = PESQUISAR_STATE;

    private Setor setor = new Setor();
    private List<Setor> listaSetores;
    private List<Setor> listaSetoresCliente;
    private Long selectSetor;

    @PostConstruct
    public void init() {
	carregarSetores();
    }

    public void carregarSetores() {
	if (listaSetores == null) {
	    SetorRepository repository = new SetorRepository(this.getManager());
	    this.listaSetores = repository.allSetoresByNome();
	}
    }

    public Long getContarSetor() {
	SetorRepository repository = new SetorRepository(getManager());
	return repository.countSetores();
    }

    public String limpar() {
	this.setor = new Setor();
	this.listaSetores = null;
	carregarSetores();
	return "setor";
    }

    public String salvar() {
	SetorRepository setorRepository = new SetorRepository(getManager());

	if (setor.getId() == null) {
	    setorRepository.salvar(setor);

	} else {
	    setorRepository.alterar(setor);
	}
	this.setor = new Setor();
	this.listaSetores = null;
	carregarSetores();
	FacesContext.getCurrentInstance().addMessage(
		"anotherKey",
		new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Setor Atualizado", ""));
	return "setor";
    }

    public void excluir(Setor setor) {
	SetorRepository setorRepository = new SetorRepository(this.getManager());
	setorRepository.excluir(setor);
	this.listaSetores = null;
	this.setor = new Setor();
	carregarSetores();
	FacesContext.getCurrentInstance().addMessage(
		"anotherKey",
		new FacesMessage(FacesMessage.SEVERITY_INFO, "Setor Excluido",
			""));
    }

    public String editar(Setor setor) {
	setCurrentState(EDITAR_STATE);
	this.setSetor(setor);
	return "setor?faces-redirect=true";
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public Setor getSetor() {
	return setor;
    }

    public void setSetor(Setor setor) {
	this.setor = setor;
    }

    public List<Setor> getListaSetores() {
	setCurrentState(EDITAR_STATE);
	return listaSetores;
    }

    public String getCurrentState() {
	return currentState;
    }

    public void setCurrentState(String currentState) {
	this.currentState = currentState;
    }

    public boolean isPesquisarState() {
	String state = this.getCurrentState();
	return (state == null || PESQUISAR_STATE.equals(state));
    }

    public boolean isEditarState() {
	return EDITAR_STATE.equals(this.getCurrentState());
    }

    public Long getSelectSetor() {
	return selectSetor;
    }

    public void setSelectSetor(Long selectSetor) {
	this.selectSetor = selectSetor;
    }

    public List<Setor> getListaSetoresCliente() {
	setCurrentState(EDITAR_STATE);
	return listaSetoresCliente;
    }

}
