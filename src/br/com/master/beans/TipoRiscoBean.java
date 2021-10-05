package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.TipoRisco;
import br.com.master.repository.TipoRiscoRepository;

@ManagedBean(name = "tipoRiscoBean")
@ViewScoped
public class TipoRiscoBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private TipoRisco risco;
    private List<TipoRisco> listaTipoRisco;

    @PostConstruct
    public void init() {
	carregarTipoRisco();
    }

    public void carregarTipoRisco() {
	if (listaTipoRisco == null) {
	    TipoRiscoRepository repository = new TipoRiscoRepository(
		    getManager());
	    this.listaTipoRisco = repository.getTipoRisco();
	}
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public List<TipoRisco> getListaTipoRisco() {
	return listaTipoRisco;
    }

    public TipoRisco getRisco() {
	return risco;
    }

    public void setRisco(TipoRisco risco) {
	this.risco = risco;
    }

}
