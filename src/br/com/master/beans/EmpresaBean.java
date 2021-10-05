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

import br.com.master.entities.Empresa;
import br.com.master.entities.Endereco;
import br.com.master.entities.Municipio;
import br.com.master.enums.TipoEnderecoEnum;
import br.com.master.repository.EmpresaRepository;
import br.com.master.repository.EnderecoRepository;
import br.com.master.repository.MunicipioRepository;

@ManagedBean(name = "empresaBean")
@ViewScoped
public class EmpresaBean extends BaseBean {
    private static final long serialVersionUID = 1L;
    private static final String PESQUISAR_STATE = "pesquisar";
    private static final String ADICIONAR_STATE = "adicionar";
    private static final String EDITAR_STATE = "editar";
    private String currentState = PESQUISAR_STATE;

    private Empresa empresa = new Empresa();
    private Endereco endereco = new Endereco();
    private Long selectMunicipio;
    private Long ufId;
    private List<Empresa> listaEmpresas;
    private List<Municipio> listaMunicipios;

    @PostConstruct
    public void init() {
	carregarEmpresas();
    }

    public void carregarEmpresas() {
	if (listaEmpresas == null) {
	    EmpresaRepository repository = new EmpresaRepository(
		    this.getManager());
	    this.listaEmpresas = repository.allEmpresasByNome();
	}
    }

    public List<Municipio> carregarCidades() {
	MunicipioBean municBean = new MunicipioBean();
	listaMunicipios = municBean.cargaCidades(ufId);
	return listaMunicipios;
    }

    public void limpar() {
	this.empresa = new Empresa();
	this.endereco = new Endereco();
	this.listaEmpresas = null;
	this.endereco = null;
	this.selectMunicipio = null;
	listaMunicipios = null;
	this.ufId = null;
    }

    public String salvar() {
	MunicipioRepository municRepository = new MunicipioRepository(
		getManager());
	EmpresaRepository empresaRepository = new EmpresaRepository(
		getManager());
	Municipio munic = municRepository.municipioById(selectMunicipio);
	endereco.setMunicipio(munic);
	TipoEnderecoEnum tipo = TipoEnderecoEnum.com;
	endereco.setTipo(tipo);
	empresa.setEndereco(endereco);
	if (empresa.getId() == null) {
	    empresaRepository.salvar(empresa);
	} else {
	    empresaRepository.alterar(empresa);
	}
	limpar();
	return "empresa_list";
    }

    public void excluir(Empresa empresa) {
	EmpresaRepository empresaRepository = new EmpresaRepository(
		this.getManager());
	EnderecoRepository enderecoRepository = new EnderecoRepository(
		getManager());
	empresaRepository.excluir(empresa);
	limpar();
	FacesContext.getCurrentInstance().addMessage(
		null,
		new FacesMessage(FacesMessage.SEVERITY_INFO,
			"Empresa Excluido", ""));
    }

    public void pesquisarEmpresa() {
	EmpresaRepository empresaRepository = new EmpresaRepository(
		this.getManager());
	Long id = empresa.getId();
	try {
	    empresa = empresaRepository.empresaById(id);
	    if (empresa instanceof Empresa) {
		editar(empresa);
	    } else {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(
			FacesMessage.SEVERITY_INFO, "Empresa Inexistente", ""));
		limpar();
	    }
	} catch (Exception e) {
	    System.out.println("erro " + e.getCause());
	}

    }

    public String editar(Empresa empresa) {
	System.out.println("empresa editarS");
	this.setEmpresa(empresa);
	this.setEndereco(empresa.getEndereco());
	this.setSelectMunicipio(endereco.getMunicipio().getId());
	this.setUfId(endereco.getMunicipio().getUf().getId());
	carregarCidades();
	return "empresa_edit?faces-redirect=true";
    }

    public Long getContarEmpresa() {
	EmpresaRepository repository = new EmpresaRepository(this.getManager());
	return repository.countEmpresas();
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

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
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

    public List<Empresa> getListaEmpresas() {
	return listaEmpresas;
    }

    public List<Municipio> getListaMunicipios() {
	return listaMunicipios;
    }

}
