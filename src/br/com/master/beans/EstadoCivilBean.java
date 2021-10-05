package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.EstadoCivil;
import br.com.master.repository.EstadoCivilRepository;

@ManagedBean(name = "estadoCivilBean")
@ViewScoped
public class EstadoCivilBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private EstadoCivil eC;
    private List<EstadoCivil> listaEstadoCivil;

    @PostConstruct
    public void init() {
	carregaEstCivil();
    }

    public void carregaEstCivil() {
	if (listaEstadoCivil == null) {
	    EstadoCivilRepository repository = new EstadoCivilRepository(
		    getManager());
	    this.listaEstadoCivil = repository.getEstadoCivil();
	}
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public EstadoCivil geteC() {
	return eC;
    }

    public void seteC(EstadoCivil eC) {
	this.eC = eC;
    }

    public List<EstadoCivil> getListaEstadoCivil() {
	return listaEstadoCivil;
    }

}
