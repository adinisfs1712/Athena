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

import br.com.master.entities.TipoContrato;
import br.com.master.repository.TipoContratoRepository;

@ManagedBean(name = "tipoContratoBean")
@ViewScoped
public class TipoContratoBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private TipoContrato tpContrato = new TipoContrato();
    private List<TipoContrato> listaTipoContrato;

    @PostConstruct
    public void init() {
	carregarTipoContrato();
    }

    public void carregarTipoContrato() {
	if (listaTipoContrato == null) {
	    TipoContratoRepository repository = new TipoContratoRepository(
		    getManager());
	    this.listaTipoContrato = repository.getTipoContrato();
	}
    }

    public String salvar() {
	System.out.println("salvar entrou");
	TipoContratoRepository tipoContratoRepository = new TipoContratoRepository(
		getManager());

	if (tpContrato.getId() == null) {
	    tipoContratoRepository.salvar(tpContrato);

	} else {
	    tipoContratoRepository.alterar(tpContrato);
	}
	this.tpContrato = new TipoContrato();
	this.listaTipoContrato = null;
	carregarTipoContrato();
	FacesContext.getCurrentInstance().addMessage(
		"anotherKey",
		new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Tipo de Contrato Atualizado", ""));
	return "tipoContrato";
    }

    public void excluir(TipoContrato tpContrato) {
	TipoContratoRepository tpContratoRepository = new TipoContratoRepository(
		this.getManager());
	tpContratoRepository.excluir(tpContrato);
	this.listaTipoContrato = null;
	this.tpContrato = new TipoContrato();
	carregarTipoContrato();
	FacesContext.getCurrentInstance().addMessage(
		"anotherKey",
		new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Tipo de Contrato Excluido", ""));
    }

    public String editar(TipoContrato tpContrato) {
	this.setTpContrato(tpContrato);
	return "tipoContrato?faces-redirect=true";
    }

    public String limpar() {
	this.tpContrato = new TipoContrato();
	this.listaTipoContrato = null;
	carregarTipoContrato();
	return "tipoContrato";
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public TipoContrato getTpContrato() {
	return tpContrato;
    }

    public void setTpContrato(TipoContrato tpContrato) {
	this.tpContrato = tpContrato;
    }

    public List<TipoContrato> getListaTipoContrato() {
	return listaTipoContrato;
    }

}
