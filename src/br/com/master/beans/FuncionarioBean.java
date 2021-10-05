package br.com.master.beans;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Cliente;
import br.com.master.entities.EstadoCivil;
import br.com.master.entities.Funcionario;
import br.com.master.entities.Situacao;
import br.com.master.entities.Turno;
import br.com.master.entities.UnidFederacao;
import br.com.master.enums.SexoEnum;
import br.com.master.repository.EstadoCivilRepository;
import br.com.master.repository.FuncionarioRepository;
import br.com.master.repository.SituacaoRepository;
import br.com.master.repository.TurnoRepository;
import br.com.master.repository.UnidFederacaoRepository;

@ManagedBean
@ViewScoped
public class FuncionarioBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private static final String EXCLUIR_STATE = "excluir";
    private static final String PESQUISAR_STATE = "pesquisar";
    private static final String ADICIONAR_STATE = "adicionar";
    private static final String EDITAR_STATE = "editar";
    private String currentState = PESQUISAR_STATE;

    private Cliente cliente;
    private Funcionario funcionario;
    private Long id;
    private String nome;
    private SexoEnum selectSexo;
    private Long selectUfId;
    private Long selectEstCivil;
    private Long selectTipoRisco;
    private Long selectSetor;
    private Long selectFuncao;
    private Long selectTurno;
    private Long selectCbo;

    private List<Funcionario> listaFuncionarios;
    private List<Funcionario> filter;
    private List<SexoEnum> listaSexo;
    private List<EstadoCivil> listaEstCivil;

    private Date dataAtual;

    @PostConstruct
    public void init() {
	this.listaSexo = Arrays.asList(SexoEnum.values());
    }

    public List<Funcionario> funcionariosByCliente(Cliente cliente) {
	this.listaFuncionarios = null;
	if (listaFuncionarios == null) {
	    FuncionarioRepository repository = new FuncionarioRepository(
		    getManager());
	    this.listaFuncionarios = repository.funcionariosByCliente(cliente);
	}
	return listaFuncionarios;
    }

    public Long contarFuncionarios(Cliente cliente) {
	FuncionarioRepository repository = new FuncionarioRepository(
		getManager());
	return repository.countFuncionarios(cliente);
    }

    public void criar(Cliente cliente) {
	funcionario = new Funcionario();
	funcionario.setCliente(cliente);
	setCurrentState(ADICIONAR_STATE);
    }

    public void editar(Funcionario funcionario) {
	this.setFuncionario(funcionario);
	this.setSelectSexo(funcionario.getSexo());
	this.setSelectEstCivil(funcionario.getEstadoCivil().getId());
	setCurrentState(EDITAR_STATE);
    }

    public void excluir(Funcionario funcionario) {
	FuncionarioRepository funcionarioRepository = new FuncionarioRepository(
		getManager());
	funcionarioRepository.excluir(funcionario);
	this.funcionario = new Funcionario();
	this.listaFuncionarios = null;
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("anotherKey", new FacesMessage(
		FacesMessage.SEVERITY_INFO, "Funcionario Excluido", ""));
    }

    public void limpar() {
	criar(cliente);
	funcionario.setId(null);
	selectUfId = null;
	selectSexo = null;
	selectEstCivil = null;
	selectTipoRisco = null;
	selectSetor = null;
	selectFuncao = null;
	selectTurno = null;
	selectCbo = null;
	setCurrentState(PESQUISAR_STATE);
    }

    public void limpaCliente() {
	cliente = new Cliente();
	funcionario = new Funcionario();
	setCurrentState(PESQUISAR_STATE);

    }

    public void listar() {
	limpar();
	setCurrentState(PESQUISAR_STATE);
    }

    public void salvar() {
	// CboRepository cboRepository = new CboRepository(getManager());
	// Cbo cbo = cboRepository.cboById(selectCbo);
	EstadoCivilRepository ecRepository = new EstadoCivilRepository(
		getManager());
	EstadoCivil estCivil = ecRepository.estadoCivilById(selectEstCivil);
	SituacaoRepository sitRepository = new SituacaoRepository(getManager());
	Situacao situacao = sitRepository.situacaoById(1l); // seta ativo
	// SetorRepository setorRepository = new SetorRepository(getManager());
	// Setor setor = setorRepository.setorById(selectSetor);
	// TipoRiscoRepository tpRiscoRepository = new
	// TipoRiscoRepository(getManager());
	// TipoRisco tpRisco = tpRiscoRepository.tipoRiscoById(selectTipoRisco);
	TurnoRepository turnoRepository = new TurnoRepository(getManager());
	Turno turno = turnoRepository.turnoById(selectTurno);
	UnidFederacaoRepository ufRepository = new UnidFederacaoRepository(
		getManager());
	UnidFederacao ufNatural = ufRepository.unidFederacaoById(selectUfId);
	dataAtual = new Date();
	funcionario.setDataInclusao(dataAtual);
	funcionario.setSexo(selectSexo);
	funcionario.setSituacao(situacao);
	funcionario.setNaturalidade(ufNatural);
	funcionario.setEstadoCivil(estCivil);
	funcionario.setTurno(turno);
	// funcionario.setTipoRiscos(tpRisco);

	// funcionario.setSetor(selectSetor);
	/*
	 * private Long selectFuncao; private Long selectCbo;
	 */

	FuncionarioRepository funcionarioRepository = new FuncionarioRepository(
		getManager());
	if (funcionario.getId() == null) {
	    funcionarioRepository.salvar(funcionario);
	} else {
	    funcionarioRepository.alterar(funcionario);
	}
	limpar();
	setCurrentState(PESQUISAR_STATE);
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
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

    public boolean isAdicionarState() {
	return ADICIONAR_STATE.equals(this.getCurrentState());
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public Funcionario getFuncionario() {
	return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
	this.funcionario = funcionario;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNome() {
	return nome;
    }

    public void setNome(String nome) {
	this.nome = nome;
    }

    public Long getUfId() {
	return selectUfId;
    }

    public void setUfId(Long ufId) {
	this.selectUfId = ufId;
    }

    public SexoEnum getSelectSexo() {
	return selectSexo;
    }

    public void setSelectSexo(SexoEnum selectSexo) {
	this.selectSexo = selectSexo;
    }

    public List getListaSexo() {
	return listaSexo;
    }

    public Long getSelectTipoRisco() {
	return selectTipoRisco;
    }

    public void setSelectTipoRisco(Long selectTipoRisco) {
	this.selectTipoRisco = selectTipoRisco;
    }

    public Long getSelectFuncao() {
	return selectFuncao;
    }

    public void setSelectFuncao(Long selectFuncao) {
	this.selectFuncao = selectFuncao;
    }

    public List getListaEstCivil() {
	return listaEstCivil;
    }

    public Long getSelectEstCivil() {
	return selectEstCivil;
    }

    public void setSelectEstCivil(Long selectEstCivil) {
	this.selectEstCivil = selectEstCivil;
    }

    public Long getSelectSetor() {
	return selectSetor;
    }

    public void setSelectSetor(Long selectSetor) {
	this.selectSetor = selectSetor;
    }

    public Long getSelectTurno() {
	return selectTurno;
    }

    public void setSelectTurno(Long selectTurno) {
	this.selectTurno = selectTurno;
    }

    public Long getSelectUfId() {
	return selectUfId;
    }

    public void setSelectUfId(Long selectUfId) {
	this.selectUfId = selectUfId;
    }

    public Date getDataAtual() {
	return dataAtual;
    }

    public void setDataAtual(Date dataAtual) {
	this.dataAtual = dataAtual;
    }

    public List<Funcionario> getListaFuncionarios() {
	return listaFuncionarios;
    }

    public Long getSelectCbo() {
	return selectCbo;
    }

    public void setSelectCbo(Long selectCbo) {
	this.selectCbo = selectCbo;
    }

    public List<Funcionario> getFilter() {
	return filter;
    }

    public void setFilter(List<Funcionario> filter) {
	this.filter = filter;
    }

}
