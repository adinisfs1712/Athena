package br.com.master.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Empresa;
import br.com.master.entities.Endereco;
import br.com.master.entities.Fornecedor;
import br.com.master.entities.FornecedorContato;
import br.com.master.entities.Municipio;
import br.com.master.entities.Situacao;
import br.com.master.entities.TipoFornecedor;
import br.com.master.enums.TipoEnderecoEnum;
import br.com.master.enums.TipoPessoaEnum;
import br.com.master.repository.EmpresaRepository;
import br.com.master.repository.FornecedorRepository;
import br.com.master.repository.MunicipioRepository;
import br.com.master.repository.SituacaoRepository;

@ManagedBean(name = "fornecedorBean")
@ViewScoped
public class FornecedorBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private static final String PESQUISAR_STATE = "pesquisar";
    private static final String ADICIONAR_STATE = "adicionar";
    private static final String EDITAR_STATE = "editar";
    private String currentState = PESQUISAR_STATE;

    private Empresa empresa = new Empresa();
    private Fornecedor fornecedor = new Fornecedor();
    private FornecedorContato fornecedorContato = new FornecedorContato();
    private Endereco endereco;

    private Long id;
    private String razaoSocial;
    private TipoPessoaEnum selectTipoPessoa;
    private List<TipoPessoaEnum> listaTipoPessoa;
    private List<TipoFornecedor> listaTipoFornecedores;
    private List<Fornecedor> filter;
    private List<Fornecedor> listaFornecedores;
    private List<FornecedorContato> listaContatosFornecedores;
    private Long selectMunicipio;
    private Long ufId;
    private List<Municipio> listaMunicipios;
    private Long selectTipoFornecedor;
    private Long selectFornecedor;

    @PostConstruct
    public void init() {
	this.listaTipoPessoa = Arrays.asList(TipoPessoaEnum.values());
    }

    public void criar() {
	fornecedor = new Fornecedor();
	fornecedorContato = new FornecedorContato();
	endereco = new Endereco();
	selectTipoPessoa = null;
	setCurrentState(EDITAR_STATE);
    }

    public void fornecedorById() {
	FornecedorRepository fornecedorRepository = new FornecedorRepository(
		this.getManager());
	Long id = fornecedor.getId();
	try {
	    if (id != null) {
		fornecedor = fornecedorRepository.fornecedorById(id);
	    }
	    if (fornecedor instanceof Fornecedor) {
		editar(fornecedor);
	    } else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Fornecedor Inexistente",
			""));
		limpar();
	    }

	} catch (Exception e) {
	    System.out.println("Pesquisa Fornecedor erro " + e.getCause());
	}
	setCurrentState(EDITAR_STATE);
    }

    public List<Fornecedor> listaFornecedorsByRazaoSocial() {
	String rzSocial = fornecedor.getRazaoSocial();
	this.listaFornecedores = null;
	if (listaFornecedores == null) {
	    FornecedorRepository repository = new FornecedorRepository(
		    this.getManager());
	    this.listaFornecedores = repository.fornecedoresByNome(rzSocial);
	}
	return listaFornecedores;
    }

    public List<Fornecedor> getListarAllFornecedores() {
	if (listaFornecedores == null) {
	    FornecedorRepository repository = new FornecedorRepository(
		    this.getManager());
	    this.listaFornecedores = repository.allFornecedores();
	}
	return listaFornecedores;
    }

    public List<Municipio> carregarCidades() {
	MunicipioBean municBean = new MunicipioBean();
	listaMunicipios = municBean.cargaCidades(ufId);
	return listaMunicipios;
    }

    public void listaOpcao() {
	System.out.println("opcao " + selectTipoPessoa);
    }

    public void carregarContatosFornecedors() {
	if (listaFornecedores == null) {
	    FornecedorRepository repository = new FornecedorRepository(
		    this.getManager());
	    this.listaFornecedores = repository.allFornecedores();
	}
    }

    public void salvarContato() {
	System.out.println("Fornecedor Bean salvando contato");
	/*
	 * if (listaContatosFornecedors == null) { if (fornecedor.getId() !=
	 * null) { System.out.println("fornecedor nao null");
	 * listaContatosFornecedors = fornecedor.getFornecedorContatos(); }
	 * System.out.println("entrou if salva"); }
	 * listaContatosFornecedors.add(fornecedorContato);
	 * System.out.println("saindo salva " +
	 * listaContatosFornecedors.size());
	 */
    }

    public void editar(Fornecedor fornecedor) {
	/*
	 * this.setFornecedor(fornecedor);
	 * this.setEndereco(fornecedor.getEndereco());
	 * this.setSelectMunicipio(endereco.getMunicipio().getId());
	 * this.setUfId(endereco.getMunicipio().getUf().getId());
	 * carregarCidades();
	 * this.setSelectCnae(fornecedor.getCnaeId().getId());
	 * this.setSelectTipoContrato(fornecedor.getTipoContratoId().getId());
	 * this.setSelectTipoRisco(fornecedor.getTipoRiscoId().getId());
	 * this.setSelectTipoFatura(fornecedor.getTipoFaturaId().getId());
	 * this.setSelectTipoCobranca(fornecedor.getTipoCobrancaId().getId());
	 * this.setSelectMes(fornecedor.getProgramacaoMes());
	 * this.selectMunicipioCob = null; this.ufIdCob = null;
	 * this.listaMunicipiosCob = null; this.excluiEndCob = false;
	 * this.setEnderecoCob(fornecedor.getEnderecoCob());
	 */

	setCurrentState(EDITAR_STATE);

    }

    public void selecionar(Fornecedor fornecedor) {
	this.setFornecedor(fornecedor);
	setCurrentState(EDITAR_STATE);
    }

    public void excluir(Fornecedor fornecedor) {
	FornecedorRepository fornecedorRepository = new FornecedorRepository(
		this.getManager());
	fornecedorRepository.excluir(fornecedor);
	this.fornecedor = new Fornecedor();
	this.listaFornecedores = null;
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("anotherKey", new FacesMessage(
		FacesMessage.SEVERITY_INFO, "Fornecedor Excluido", ""));
    }

    public Long getContarFornecedores() {
	FornecedorRepository repository = new FornecedorRepository(
		this.getManager());
	return repository.countFornecedores();
    }

    public void limpar() {
	criar();
	setCurrentState(PESQUISAR_STATE);

	/*
	 * fornecedor = new Fornecedor(); fornecedor.setId(null);
	 * listaFornecedors = null; listaContatosFornecedors = null;
	 * listaMunicipios = null;
	 * 
	 * endereco = new Endereco(); selectMunicipio = null; ufId = null;
	 * listaMunicipiosCob = null; enderecoCob = new Endereco();
	 * selectMunicipioCob = null; ufIdCob = null; selectMes = null;
	 * selectCnae = null; selectTipoContrato = null; selectTipoCobranca =
	 * null; selectTipoFatura = null; selectTipoRisco = null; excluiEndCob =
	 * false;
	 */

    }

    public void listar() {
	limpar();
	setCurrentState(PESQUISAR_STATE);
    }

    public void pesquisarFornecedor() {
	FornecedorRepository fornecedorRepository = new FornecedorRepository(
		this.getManager());
	Long id = fornecedor.getId();
	String razaoSocial = fornecedor.getRazaoSocial();
	try {
	    if (id != null) {
		fornecedor = fornecedorRepository.fornecedorById(id);
	    }
	    if (razaoSocial != null) {
		fornecedor = fornecedorRepository.fornecedorById(id); // trocar
								      // por
								      // nome
	    }
	    if (fornecedor instanceof Fornecedor) {
		editar(fornecedor);
	    } else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Fornecedor Inexistente",
			""));
		limpar();
	    }

	} catch (Exception e) {
	    System.out.println("Pesquisa Fornecedor erro " + e.getCause());
	}
	setCurrentState(EDITAR_STATE);
    }

    public void salvar() {
	EmpresaRepository empresaRepository = new EmpresaRepository(
		getManager());
	Empresa empresa = empresaRepository.empresaById(1L);
	fornecedor.setEmpresasId(empresa); // como pegar a empresa;
	FornecedorRepository fornecedorRepository = new FornecedorRepository(
		getManager());

	MunicipioRepository municRepository = new MunicipioRepository(
		getManager());
	Municipio munic = municRepository.municipioById(selectMunicipio);

	SituacaoRepository situacaoRepository = new SituacaoRepository(
		getManager());
	Situacao situacao = situacaoRepository.situacaoById(1l);

	endereco.setMunicipio(munic);
	endereco.setTipo(TipoEnderecoEnum.com);
	fornecedor.setEndereco(endereco);
	fornecedor.setSituacao(situacao);
	if (fornecedor.getId() == null) {
	    fornecedorRepository.salvar(fornecedor);
	} else {
	    fornecedorRepository.alterar(fornecedor);
	}
	limpar();
	setCurrentState(PESQUISAR_STATE);
    }

    // metodos contato fornecedor

    public void onEdit() {

    }

    public void onCancel() {

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

    public Empresa getEmpresa() {
	return empresa;
    }

    public void setEmpresa(Empresa empresa) {
	this.empresa = empresa;
    }

    public Fornecedor getFornecedor() {
	return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
	this.fornecedor = fornecedor;
    }

    public FornecedorContato getFornecedorContato() {
	return fornecedorContato;
    }

    public void setFornecedorContato(FornecedorContato fornecedorContato) {
	this.fornecedorContato = fornecedorContato;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getRazaoSocial() {
	return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
	this.razaoSocial = razaoSocial;
    }

    public TipoPessoaEnum getSelectTipoPessoa() {
	return selectTipoPessoa;
    }

    public void setSelectTipoPessoa(TipoPessoaEnum selectTipoPessoa) {
	this.selectTipoPessoa = selectTipoPessoa;
    }

    public List<Fornecedor> getFilter() {
	return filter;
    }

    public void setFilter(List<Fornecedor> filter) {
	this.filter = filter;
    }

    public Long getSelectMunicipio() {
	return selectMunicipio;
    }

    public void setSelectMunicipio(Long selectMunicipio) {
	this.selectMunicipio = selectMunicipio;
    }

    public Long getUfId() {
	return ufId;
    }

    public void setUfId(Long ufId) {
	this.ufId = ufId;
    }

    public Long getSelectFornecedor() {
	return selectFornecedor;
    }

    public void setSelectFornecedor(Long selectFornecedor) {
	this.selectFornecedor = selectFornecedor;
    }

    public List<TipoPessoaEnum> getListaTipoPessoa() {
	return listaTipoPessoa;
    }

    public List<Fornecedor> getListaFornecedores() {
	return listaFornecedores;
    }

    public List<FornecedorContato> getListaContatosFornecedores() {
	return listaContatosFornecedores;
    }

    public List<Municipio> getListaMunicipios() {
	return listaMunicipios;
    }

    public List<TipoFornecedor> getListaTipoFornecedores() {
	return listaTipoFornecedores;
    }

    public Long getSelectTipoFornecedor() {
	return selectTipoFornecedor;
    }

    public void setSelectTipoFornecedor(Long selectTipoFornecedor) {
	this.selectTipoFornecedor = selectTipoFornecedor;
    }

}
