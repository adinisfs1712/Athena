package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.UnidFederacao;
import br.com.master.repository.UnidFederacaoRepository;

@ManagedBean(name = "unidFederacaoBean")
@ViewScoped
public class UnidFederacaoBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private UnidFederacao uF;
    private List<UnidFederacao> listaUFs;

    @PostConstruct
    public void init() {
	carregaUFs();
    }

    public void carregaUFs() {
	if (listaUFs == null) {
	    UnidFederacaoRepository repository = new UnidFederacaoRepository(
		    getManager());
	    this.listaUFs = repository.getUfs();
	}
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public UnidFederacao getuF() {
	return uF;
    }

    public void setuF(UnidFederacao uF) {
	this.uF = uF;
    }

    public List<UnidFederacao> getListaUFs() {
	return listaUFs;
    }

}
