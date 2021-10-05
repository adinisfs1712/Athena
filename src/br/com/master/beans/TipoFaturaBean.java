package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.TipoFatura;
import br.com.master.repository.TipoFaturaRepository;

@ManagedBean(name = "tipoFaturaBean")
@ViewScoped
public class TipoFaturaBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private TipoFatura tpFt;
    private List<TipoFatura> listaTipoFatura;

    @PostConstruct
    public void init() {
	carregaTipoFatura();
    }

    public void carregaTipoFatura() {
	if (listaTipoFatura == null) {
	    TipoFaturaRepository repository = new TipoFaturaRepository(
		    getManager());
	    this.listaTipoFatura = repository.getTipoFatura();
	}
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public TipoFatura getTpFt() {
	return tpFt;
    }

    public void setTpFt(TipoFatura tpFt) {
	this.tpFt = tpFt;
    }

    public List<TipoFatura> getListaTipoFatura() {
	return listaTipoFatura;
    }

}
