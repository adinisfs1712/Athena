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

import br.com.master.entities.TipoFornecedor;
import br.com.master.repository.TipoFornecedorRepository;

@ManagedBean(name = "tipoFornecedorBean")
@ViewScoped
public class TipoFornecedorBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private TipoFornecedor tpFornecedor = new TipoFornecedor();
    private List<TipoFornecedor> listaTipoFornecedor;

    @PostConstruct
    public void init() {
	carregarTipoFornecedor();
    }

    public void carregarTipoFornecedor() {
	if (listaTipoFornecedor == null) {
	    TipoFornecedorRepository repository = new TipoFornecedorRepository(
		    getManager());
	    this.listaTipoFornecedor = repository.getTipoFornecedor();
	}
    }

    public String salvar() {
	TipoFornecedorRepository repository = new TipoFornecedorRepository(
		getManager());

	if (tpFornecedor.getId() == null) {
	    repository.salvar(tpFornecedor);

	} else {
	    repository.alterar(tpFornecedor);
	}
	this.tpFornecedor = new TipoFornecedor();
	this.listaTipoFornecedor = null;
	carregarTipoFornecedor();
	FacesContext.getCurrentInstance().addMessage(
		"anotherKey",
		new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Tipo de Fornecedor Atualizado", ""));
	return "tipoFornecedor";
    }

    public void excluir(TipoFornecedor TpFornecedor) {
	TipoFornecedorRepository repository = new TipoFornecedorRepository(
		this.getManager());
	repository.excluir(TpFornecedor);
	this.listaTipoFornecedor = null;
	this.tpFornecedor = new TipoFornecedor();
	carregarTipoFornecedor();
	FacesContext.getCurrentInstance().addMessage(
		"anotherKey",
		new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Tipo de Fornecedor Excluido", ""));
    }

    public String editar(TipoFornecedor TpFornecedor) {
	this.setTpFornecedor(TpFornecedor);
	return "tipoFornecedor?faces-redirect=true";
    }

    public String limpar() {
	this.tpFornecedor = new TipoFornecedor();
	this.listaTipoFornecedor = null;
	carregarTipoFornecedor();
	return "tipoFornecedor";
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public TipoFornecedor getTpFornecedor() {
	return tpFornecedor;
    }

    public void setTpFornecedor(TipoFornecedor tpFornecedor) {
	this.tpFornecedor = tpFornecedor;
    }

    public List<TipoFornecedor> getListaTipoFornecedor() {
	return listaTipoFornecedor;
    }

}
