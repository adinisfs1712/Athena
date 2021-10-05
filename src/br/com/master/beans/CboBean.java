package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Cbo;
import br.com.master.repository.CboRepository;

@ManagedBean(name = "cboBean")
@ViewScoped
public class CboBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	private static final String PESQUISAR_STATE = "pesquisar";
	private static final String ADICIONAR_STATE = "adicionar";
	private static final String EDITAR_STATE = "editar";
	private String currentState = PESQUISAR_STATE;

	private Cbo cbo;
	private List<Cbo> listaCbo;
	private List<Cbo> listaCboCliente;
	private Long selectCbo;

	@PostConstruct
	public void init() {
		carregarCbo();
	}

	public void carregarCbo() {
		if (listaCbo == null) {
			CboRepository repository = new CboRepository(this.getManager());
			this.listaCbo = repository.allCboByNome();
		}
	}

	public String limpa() {
		this.cbo = new Cbo();
		this.listaCbo = null;
		carregarCbo();
		return "setor";
	}

	public Long getContarCbo() {
		CboRepository repository = new CboRepository(this.getManager());
		return repository.countCbo();
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

	public Cbo getCbo() {
		return cbo;
	}

	public void setCbo(Cbo setor) {
		this.cbo = setor;
	}

	public List<Cbo> getListaCbo() {
		setCurrentState(EDITAR_STATE);
		return listaCbo;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public boolean isPesquisarState() {
		String state = this.getCurrentState();
		return (state == null || PESQUISAR_STATE.equals(state));
	}

	public boolean isEditarState() {
		return EDITAR_STATE.equals(this.getCurrentState());
	}

	public Long getSelectCbo() {
		return selectCbo;
	}

	public void setSelectCbo(Long selectCbo) {
		this.selectCbo = selectCbo;
	}

	public List<Cbo> getListaCboCliente() {
		setCurrentState(EDITAR_STATE);
		return listaCboCliente;
	}

}
