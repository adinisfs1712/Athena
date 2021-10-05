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
import br.com.master.entities.Municipio;
import br.com.master.entities.Prestador;
import br.com.master.entities.Situacao;
import br.com.master.enums.TipoEnderecoEnum;
import br.com.master.enums.TipoPessoaEnum;
import br.com.master.repository.EmpresaRepository;
import br.com.master.repository.MunicipioRepository;
import br.com.master.repository.PrestadorRepository;
import br.com.master.repository.SituacaoRepository;

@ManagedBean(name = "prestadorBean")
@ViewScoped
public class PrestadorBean extends BaseBean {

    private static final long serialVersionUID = 1L;
    private static final String PESQUISAR_STATE = "pesquisar";
    private static final String ADICIONAR_STATE = "adicionar";
    private static final String EDITAR_STATE = "editar";
    private String currentState = PESQUISAR_STATE;

    private Empresa empresa = new Empresa();
    private Fornecedor fornecedor;
    private Prestador prestador;
    private Endereco endereco;

    private Long id;
    private String razaoSocial;
    private TipoPessoaEnum selectTipoPessoa;
    private List<TipoPessoaEnum> listaTipoPessoa;
    private List<Prestador> filter;
    private List<Prestador> listaPrestadores;
    private Long selectMunicipio;
    private Long ufId;
    private List<Municipio> listaMunicipios;
    private Long selectPrestador;

    @PostConstruct
    public void init() {
	this.listaTipoPessoa = Arrays.asList(TipoPessoaEnum.values());
    }

    public void criar() {
	// fornecedor = new Fornecedor();
	prestador = new Prestador();
	endereco = new Endereco();
	selectTipoPessoa = null;
	setCurrentState(EDITAR_STATE);
    }

    public void prestadorById() {
	PrestadorRepository prestadorRepository = new PrestadorRepository(
		this.getManager());
	Long id = prestador.getId();
	try {
	    if (id != null) {
		prestador = prestadorRepository.prestadorById(id);
	    }
	    if (prestador instanceof Prestador) {
		editar(prestador);
	    } else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Prestador Inexistente",
			""));
		limpar();
	    }

	} catch (Exception e) {
	    System.out.println("Pesquisa Prestador erro " + e.getCause());
	}
	setCurrentState(EDITAR_STATE);
    }

    public List<Prestador> listaPrestadoresByRazaoSocial() {
	String rzSocial = prestador.getRazaoSocial();
	this.listaPrestadores = null;
	if (listaPrestadores == null) {
	    PrestadorRepository repository = new PrestadorRepository(
		    this.getManager());
	    this.listaPrestadores = repository.prestadoresByNome(rzSocial);
	}
	return listaPrestadores;
    }

    public List<Prestador> getListarAllPrestadores() {
	if (listaPrestadores == null) {
	    PrestadorRepository repository = new PrestadorRepository(
		    this.getManager());
	    this.listaPrestadores = repository.allPrestadores();
	}
	return listaPrestadores;
    }

    public List<Municipio> carregarCidades() {
	MunicipioBean municBean = new MunicipioBean();
	listaMunicipios = municBean.cargaCidades(ufId);
	return listaMunicipios;
    }

    public void listaOpcao() {
	System.out.println("opcao " + selectTipoPessoa);
    }

    public void editar(Prestador prestador) {
	/*
	 * this.setPrestador(prestador);
	 * this.setEndereco(prestador.getEndereco());
	 * this.setSelectMunicipio(endereco.getMunicipio().getId());
	 * this.setUfId(endereco.getMunicipio().getUf().getId());
	 * carregarCidades(); this.setSelectCnae(prestador.getCnaeId().getId());
	 * this.setSelectTipoContrato(prestador.getTipoContratoId().getId());
	 * this.setSelectTipoRisco(prestador.getTipoRiscoId().getId());
	 * this.setSelectTipoFatura(prestador.getTipoFaturaId().getId());
	 * this.setSelectTipoCobranca(prestador.getTipoCobrancaId().getId());
	 * this.setSelectMes(prestador.getProgramacaoMes());
	 * this.selectMunicipioCob = null; this.ufIdCob = null;
	 * this.listaMunicipiosCob = null; this.excluiEndCob = false;
	 * this.setEnderecoCob(prestador.getEnderecoCob());
	 */

	setCurrentState(EDITAR_STATE);

    }

    public void selecionar(Prestador prestador) {

	this.setPrestador(prestador);
	setCurrentState(EDITAR_STATE);
    }

    public void excluir(Prestador prestador) {
	PrestadorRepository prestadorRepository = new PrestadorRepository(
		this.getManager());
	prestadorRepository.excluir(prestador);
	this.prestador = new Prestador();
	this.listaPrestadores = null;
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("anotherKey", new FacesMessage(
		FacesMessage.SEVERITY_INFO, "Prestador Excluido", ""));
    }

    public Long getContarPrestadores() {
	PrestadorRepository repository = new PrestadorRepository(
		this.getManager());
	return repository.countPrestadores();
    }

    public void limpar() {
	criar();
	setCurrentState(PESQUISAR_STATE);

	/*
	 * prestador = new Prestador(); prestador.setId(null); listaPrestadors =
	 * null; listaContatosPrestadors = null; listaMunicipios = null;
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

    public void pesquisarPrestador() {
	PrestadorRepository prestadorRepository = new PrestadorRepository(
		this.getManager());
	Long id = prestador.getId();
	String razaoSocial = prestador.getRazaoSocial();
	try {
	    if (id != null) {
		prestador = prestadorRepository.prestadorById(id);
	    }
	    if (razaoSocial != null) {
		prestador = prestadorRepository.prestadorById(id); // trocar
								   // por
								   // nome
	    }
	    if (prestador instanceof Prestador) {
		editar(prestador);
	    } else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(
			FacesMessage.SEVERITY_ERROR, "Prestador Inexistente",
			""));
		limpar();
	    }

	} catch (Exception e) {
	    System.out.println("Pesquisa Prestador erro " + e.getCause());
	}
	setCurrentState(EDITAR_STATE);
    }

    public void salvar() {
	EmpresaRepository empresaRepository = new EmpresaRepository(
		getManager());
	Empresa empresa = empresaRepository.empresaById(1L);
	prestador.setEmpresasId(empresa); // como pegar a empresa;
	PrestadorRepository prestadorRepository = new PrestadorRepository(
		getManager());

	MunicipioRepository municRepository = new MunicipioRepository(
		getManager());
	Municipio munic = municRepository.municipioById(selectMunicipio);

	SituacaoRepository situacaoRepository = new SituacaoRepository(
		getManager());
	Situacao situacao = situacaoRepository.situacaoById(1l);

	endereco.setMunicipio(munic);
	endereco.setTipo(TipoEnderecoEnum.com);
	prestador.setEndereco(endereco);
	prestador.setSituacao(situacao);

	System.out.println("salva forn antes repos");
	if (prestador.getId() == null) {
	    prestadorRepository.salvar(prestador);
	} else {
	    prestadorRepository.alterar(prestador);
	}
	limpar();
	setCurrentState(PESQUISAR_STATE);
    }

    // metodos contato prestador

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

    public Prestador getPrestador() {
	return prestador;
    }

    public void setPrestador(Prestador prestador) {
	this.prestador = prestador;
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

    public List<Prestador> getFilter() {
	return filter;
    }

    public void setFilter(List<Prestador> filter) {
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

    public Long getSelectPrestador() {
	return selectPrestador;
    }

    public void setSelectPrestador(Long selectPrestador) {
	this.selectPrestador = selectPrestador;
    }

    public List<TipoPessoaEnum> getListaTipoPessoa() {
	return listaTipoPessoa;
    }

    public List<Prestador> getListaPrestadores() {
	return listaPrestadores;
    }

    public List<Municipio> getListaMunicipios() {
	return listaMunicipios;
    }

}
