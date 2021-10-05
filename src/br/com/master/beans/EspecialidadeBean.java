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

import br.com.master.entities.Especialidade;
import br.com.master.repository.EspecialidadeRepository;

@ManagedBean(name = "especialidadeBean")
@ViewScoped
public class EspecialidadeBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	private static final String PESQUISAR_STATE = "pesquisar";
	private static final String ADICIONAR_STATE = "adicionar";
	private static final String EDITAR_STATE = "editar";
	private String currentState = PESQUISAR_STATE;

	private Especialidade especialidade = new Especialidade();
	private List<Especialidade> listaEspecialidades;
	private List<Especialidade> listaEspecialidadesCliente;
	private Long selectEspecialidade;

	@PostConstruct
	public void init() {
		carregarEspecialidades();
	}

	public void carregarEspecialidades() {
		if (listaEspecialidades == null) {
			EspecialidadeRepository repository = new EspecialidadeRepository(this.getManager());
			this.listaEspecialidades = repository.allEspecialidadesByNome();
		}
	}

	public Long getContarEspecialidade() {
		EspecialidadeRepository repository = new EspecialidadeRepository(getManager());
		return repository.countEspecialidades();
	}

	public void limpar() {
		this.especialidade = new Especialidade();
		this.listaEspecialidades = null;
		carregarEspecialidades();
	}

	public void salvar() {
		EspecialidadeRepository especialidadeRepository = new EspecialidadeRepository(getManager());

		if (especialidade.getId() == null) {
			especialidadeRepository.salvar(especialidade);

		} else {
			especialidadeRepository.alterar(especialidade);
		}
		this.especialidade = new Especialidade();
		this.listaEspecialidades = null;
		carregarEspecialidades();
		FacesContext.getCurrentInstance().addMessage("anotherKey",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Especialidade Atualizada", ""));
	}

	public void excluir(Especialidade especialidade) {
		EspecialidadeRepository especialidadeRepository = new EspecialidadeRepository(this.getManager());
		especialidadeRepository.excluir(especialidade);
		this.listaEspecialidades = null;
		this.especialidade = new Especialidade();
		carregarEspecialidades();
		FacesContext.getCurrentInstance().addMessage("anotherKey",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Especialidade Excluida", ""));
	}

	public String editar(Especialidade especialidade) {
		this.setEspecialidade(especialidade);
		return "especialidade?faces-redirect=true";
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public List<Especialidade> getListaEspecialidades() {
		setCurrentState(EDITAR_STATE);
		return listaEspecialidades;
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

	public Long getSelectEspecialidade() {
		return selectEspecialidade;
	}

	public void setSelectEspecialidade(Long selectEspecialidade) {
		this.selectEspecialidade = selectEspecialidade;
	}

	public List<Especialidade> getListaEspecialidadesCliente() {
		setCurrentState(EDITAR_STATE);
		return listaEspecialidadesCliente;
	}

}
