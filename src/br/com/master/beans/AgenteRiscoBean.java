package br.com.master.beans;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.AgenteRisco;
import br.com.master.entities.TipoRisco;
import br.com.master.repository.AgenteRiscoRepository;
import br.com.master.repository.TipoRiscoRepository;

@ManagedBean(name = "agenteRiscoBean")
@ViewScoped
public class AgenteRiscoBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private AgenteRisco agenteRisco = new AgenteRisco();
    private TipoRisco tipoRisco = new TipoRisco();
    private List<AgenteRisco> listaAgenteRiscos;
    private Long agenteRiscoId;
    private Long tipoRiscoId;
    private Long selectTipoRisco;

    @PostConstruct
    public void init() {
	carregarAllAgenteRiscos();
    }

    public void limpar() {
	agenteRisco = new AgenteRisco();
	agenteRisco.setDescricao(null);
	selectTipoRisco = null;
    }

    public List<AgenteRisco> carregarAllAgenteRiscos() {
	this.listaAgenteRiscos = null;
	AgenteRiscoRepository agenteRiscoRepository = new AgenteRiscoRepository(
		getManager());
	String query = "Select e from AgenteRisco e ";
	Map<String, Object> params = new HashMap<String, Object>();
	listaAgenteRiscos = agenteRiscoRepository.findByParam(query, params);
	return listaAgenteRiscos;
    }

    public List<AgenteRisco> carregarAgenteRiscos(Long tipoRiscoId) {
	this.listaAgenteRiscos = null;

	AgenteRiscoRepository agenteRiscoRepository = new AgenteRiscoRepository(
		getManager());

	if (tipoRiscoId != null) {
	    TipoRiscoRepository tipoRiscoRepository = new TipoRiscoRepository(
		    getManager());
	    String query = "select m from AgenteRisco m where m.tipo_riscos_id = :tR order by m.descricao";
	    System.out.println("lista query " + query);
	    Map<String, Object> params = new HashMap<String, Object>();
	    params.put("tR", tipoRiscoId);
	    listaAgenteRiscos = agenteRiscoRepository
		    .findByParam(query, params);
	    System.out.println("lista volta " + listaAgenteRiscos.size());
	}
	return listaAgenteRiscos;
    }

    public Long getContarAgenteRisco() {
	AgenteRiscoRepository repository = new AgenteRiscoRepository(
		getManager());
	return repository.countAgenteRiscos();
    }

    public String editar(AgenteRisco agenteRisco) {
	this.setAgenteRisco(agenteRisco);
	this.setSelectTipoRisco(agenteRisco.getTipoRisco().getId());
	return "agenteRisco?faces-redirect=true";
    }

    public void excluir(AgenteRisco agenteRisco) {
	AgenteRiscoRepository repository = new AgenteRiscoRepository(
		getManager());
	repository.excluir(agenteRisco);
	this.agenteRisco = new AgenteRisco();
	this.listaAgenteRiscos = null;
	carregarAllAgenteRiscos();
	FacesContext.getCurrentInstance().addMessage(
		"anotherKey",
		new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Agente de Risco Excluido", ""));
    }

    public void salvar() {
	TipoRiscoRepository tpRiscoRepository = new TipoRiscoRepository(
		getManager());
	TipoRisco tpRisco = tpRiscoRepository.tipoRiscoById(selectTipoRisco);
	agenteRisco.setTipoRisco(tpRisco);
	AgenteRiscoRepository repository = new AgenteRiscoRepository(
		getManager());
	if (agenteRisco.getId() == null) {
	    repository.salvar(agenteRisco);
	    FacesContext.getCurrentInstance().addMessage(
		    "anotherKey",
		    new FacesMessage(FacesMessage.SEVERITY_INFO,
			    "Agente de Risco Incluido", ""));

	} else {
	    repository.alterar(agenteRisco);
	    FacesContext.getCurrentInstance().addMessage(
		    "anotherKey",
		    new FacesMessage(FacesMessage.SEVERITY_INFO,
			    "Agente de Risco Alterado", ""));

	}
	this.listaAgenteRiscos = null;
	carregarAllAgenteRiscos();
	agenteRisco = new AgenteRisco();
	selectTipoRisco = null;
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

    public AgenteRisco getAgenteRisco() {
	return agenteRisco;
    }

    public void setAgenteRisco(AgenteRisco agenteRisco) {
	this.agenteRisco = agenteRisco;
    }

    public Long getAgenteRiscoId() {
	return agenteRiscoId;
    }

    public void setAgenteRiscoId(Long agenteRiscoId) {
	this.agenteRiscoId = agenteRiscoId;
    }

    public Long getTipoRiscoId() {
	return tipoRiscoId;
    }

    public void setTipoRiscoId(Long tipoRiscoId) {
	this.tipoRiscoId = tipoRiscoId;
    }

    public List<AgenteRisco> getListaAgenteRiscos() {
	return listaAgenteRiscos;
    }

    public TipoRisco getTipoRisco() {
	return tipoRisco;
    }

    public void setTipoRisco(TipoRisco tipoRisco) {
	this.tipoRisco = tipoRisco;
    }

    public Long getSelectTipoRisco() {
	return selectTipoRisco;
    }

    public void setSelectTipoRisco(Long selectTipoRisco) {
	this.selectTipoRisco = selectTipoRisco;
    }

}
