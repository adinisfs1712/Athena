package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.TipoCobranca;
import br.com.master.repository.TipoCobrancaRepository;

@ManagedBean(name = "tipoCobrancaBean")
@ViewScoped
public class TipoCobrancaBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private TipoCobranca tpCb;
    private List<TipoCobranca> listaTipoCobranca;

    @PostConstruct
    public void init() {
	carregaTipoCobranca();
    }

    public void carregaTipoCobranca() {
	if (listaTipoCobranca == null) {
	    TipoCobrancaRepository repository = new TipoCobrancaRepository(
		    getManager());
	    this.listaTipoCobranca = repository.getTipoCobranca();
	}
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public TipoCobranca getTpCb() {
	return tpCb;
    }

    public void setTpCb(TipoCobranca tpCb) {
	this.tpCb = tpCb;
    }

    public List<TipoCobranca> getListaTipoCobranca() {
	return listaTipoCobranca;
    }

}
