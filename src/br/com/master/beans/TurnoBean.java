package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Turno;
import br.com.master.repository.TurnoRepository;

@ManagedBean
@ViewScoped
public class TurnoBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private Turno turno;
    private List<Turno> listaTurno;

    @PostConstruct
    public void init() {
	carregarTurno();
    }

    public void carregarTurno() {
	if (listaTurno == null) {
	    TurnoRepository repository = new TurnoRepository(getManager());
	    this.listaTurno = repository.getTurno();
	}
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public Turno getTurno() {
	return turno;
    }

    public void setTurno(Turno turno) {
	this.turno = turno;
    }

    public List<Turno> getListaTurno() {
	return listaTurno;
    }

}
