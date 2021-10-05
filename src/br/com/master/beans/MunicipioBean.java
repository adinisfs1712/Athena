package br.com.master.beans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Municipio;
import br.com.master.entities.UnidFederacao;
import br.com.master.repository.MunicipioRepository;
import br.com.master.repository.UnidFederacaoRepository;

@ManagedBean(name = "municipioBean")
@ViewScoped
public class MunicipioBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private Municipio municipio = new Municipio();
    MunicipioBean municBean;
    private List<Municipio> listaMunicipios;
    private Long municipioId;
    private Long ufId;

    public List<Municipio> carregarCidades(Long ufId) {
	System.out.println("munic bean " + ufId);
	municBean = new MunicipioBean();
	listaMunicipios = municBean.cargaCidades(ufId);
	System.out.println("munic bean " + listaMunicipios.size());
	return listaMunicipios;
    }

    public List<Municipio> completaMunic(String query) {
	this.listaMunicipios = municBean.cargaCidades(ufId);
	if (!query.isEmpty()) {
	    List<Municipio> sugestoes = new ArrayList<Municipio>();
	    for (Municipio mun : this.listaMunicipios) {
		if (mun.getNome().toLowerCase().startsWith(query.toLowerCase())) {
		    sugestoes.add(mun);
		}
	    }
	    return sugestoes;
	}
	return null;
    }

    public List<Municipio> cargaCidades(Long ufId) {
	this.listaMunicipios = null;
	if (ufId != null) {
	    MunicipioRepository municipioRepository = new MunicipioRepository(
		    getManager());
	    UnidFederacaoRepository unidFederacaoRepository = new UnidFederacaoRepository(
		    getManager());
	    UnidFederacao uF = unidFederacaoRepository.unidFederacaoById(ufId);
	    String query = "select m from Municipio m where m.uf = :uF order by m.nome";
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("uF", uF);
	    listaMunicipios = municipioRepository.findByParam(query, params);
	}
	return listaMunicipios;
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public Municipio getMunicipio() {
	return municipio;
    }

    public void setMunicipio(Municipio municipio) {
	this.municipio = municipio;
    }

    public Long getMunicipioId() {
	return municipioId;
    }

    public void setMunicipioId(Long municipioId) {
	this.municipioId = municipioId;
    }

    public Long getUfId() {
	return ufId;
    }

    public void setUfId(Long ufId) {
	this.ufId = ufId;
    }

    public List<Municipio> getListaMunicipios() {
	return listaMunicipios;
    }

}
