package br.com.master.beans;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIPanel;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Cliente;
import br.com.master.entities.ClienteContato;
import br.com.master.entities.Cnae;
import br.com.master.entities.Empresa;
import br.com.master.entities.Endereco;
import br.com.master.entities.Municipio;
import br.com.master.entities.SituacaoCliente;
import br.com.master.entities.TipoCobranca;
import br.com.master.entities.TipoContrato;
import br.com.master.entities.TipoFatura;
import br.com.master.entities.TipoRisco;
import br.com.master.enums.MesesEnum;
import br.com.master.enums.TipoEnderecoEnum;
import br.com.master.repository.ClienteRepository;
import br.com.master.repository.CnaeRepository;
import br.com.master.repository.EmpresaRepository;
import br.com.master.repository.EnderecoRepository;
import br.com.master.repository.MunicipioRepository;
import br.com.master.repository.SituacaoClienteRepository;
import br.com.master.repository.TipoCobrancaRepository;
import br.com.master.repository.TipoContratoRepository;
import br.com.master.repository.TipoFaturaRepository;
import br.com.master.repository.TipoRiscoRepository;

@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean extends BaseBean {

	private static final long serialVersionUID = 1L;

	private static final String PESQUISAR_STATE = "pesquisar";
	private static final String ADICIONAR_STATE = "adicionar";
	private static final String EDITAR_STATE = "editar";
	private String currentState = PESQUISAR_STATE;

	private UIPanel panelForm;

	private Empresa empresa = new Empresa();
	private Cliente cliente = new Cliente();
	private ClienteContato clienteContato = new ClienteContato();
	private Endereco endereco;
	private Endereco enderecoCobranca;

	private Long id;
	private String razaoSocial;
	private Long selectMunicipio;
	private Long ufId;
	private Long selectMunicipioCob;
	private Long ufIdCob;
	private Cnae cnae = new Cnae();
	private String cnaeDescricao;
	private Long selectCliente;
	private Long selectCnae;
	private Long selectTipoContrato;
	private Long selectTipoCobranca;
	private Long selectTipoFatura;
	private Long selectTipoRisco;
	private MesesEnum selectMes;
	private List<MesesEnum> listaMeses;
	private List<Cliente> filter;
	private List<Cliente> listaClientes;
	private List<ClienteContato> listaClienteContatos;
	private List<Municipio> listaMunicipios;
	private List<Municipio> listaMunicipiosCob;
	private Boolean excluiEndCob = false;
	private Boolean excluiContato = false;

	@PostConstruct
	public void init() {
		// carregarClientes();
		System.out.println("inicia ClienteBean");
		this.listaMeses = Arrays.asList(MesesEnum.values());

	}

	public void selecionar(Cliente cliente) {
		this.setCliente(cliente);
		setCurrentState(EDITAR_STATE);
	}

	public void criar() {
		cliente = new Cliente();
		clienteContato = new ClienteContato();
		endereco = new Endereco();
		enderecoCobranca = new Endereco();
		this.setCurrentState(ADICIONAR_STATE);
	}

	public void limpar() {
		criar();
		// endereco = null;
		selectMunicipio = null;
		ufId = null;
		listaMunicipios = null;
		listaClientes = null;
		selectCnae = null;
		selectTipoContrato = null;
		selectTipoRisco = null;
		selectTipoFatura = null;
		selectTipoCobranca = null;
		selectMes = null;
		selectMunicipioCob = null;
		ufIdCob = null;
		listaMunicipiosCob = null;
		listaClienteContatos = null;
		this.setCurrentState(PESQUISAR_STATE);
	}

	/*
	 * cliente = new Cliente(); cliente.setId(null); listaContatosClientes = null;
	 * listaMunicipios = null;
	 * 
	 * endereco = new Endereco(); selectMunicipio = null; ufId = null;
	 * listaMunicipiosCob = null; enderecoCob = new Endereco(); selectMunicipioCob =
	 * null; ufIdCob = null; selectMes = null; selectCnae = null; selectTipoContrato
	 * = null; selectTipoCobranca = null; selectTipoFatura = null; selectTipoRisco =
	 * null; excluiEndCob = false;
	 */

	public Long getContarClientes() {
		ClienteRepository repository = new ClienteRepository(this.getManager());
		return repository.countClientes();
	}

	public void listar() {
		limpar();
		setCurrentState(PESQUISAR_STATE);
	}

	public void pesquisarCliente() {
		ClienteRepository clienteRepository = new ClienteRepository(this.getManager());
		Long id = cliente.getId();
		String razaoSocial = cliente.getRazaoSocial();
		try {
			if (id != null) {
				cliente = clienteRepository.clienteById(id);
			}
			if (razaoSocial != null) {
				cliente = clienteRepository.clienteById(id); // trocar por nome
			}
			if (cliente instanceof Cliente) {
				editar(cliente);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("anotherKey",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", ""));
				limpar();
			}

		} catch (Exception e) {
			System.out.println("Pesquisa Cliente erro " + e.getCause());
		}
		setCurrentState(EDITAR_STATE);
	}

	public void clienteById() {
		ClienteRepository clienteRepository = new ClienteRepository(this.getManager());
		Long id = cliente.getId();
		try {
			if (id != null) {
				cliente = clienteRepository.clienteById(id);
			}
			if (cliente instanceof Cliente) {
				editar(cliente);
			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage("anotherKey",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cliente Inexistente", ""));
				limpar();
			}

		} catch (Exception e) {
			System.out.println("Pesquisa Cliente erro " + e.getCause());
		}
		setCurrentState(EDITAR_STATE);
	}

	public List<Cliente> listaClientesByRazaoSocial() {
		String rzSocial = cliente.getRazaoSocial();
		this.listaClientes = null;
		if (listaClientes == null) {
			ClienteRepository repository = new ClienteRepository(this.getManager());
			this.listaClientes = repository.clientesByNome(rzSocial);
		}
		return listaClientes;
	}

	public List<Cliente> getListarAllClientes() {
		if (listaClientes == null) {
			ClienteRepository repository = new ClienteRepository(this.getManager());
			this.listaClientes = repository.allClientes();
		}
		return listaClientes;
	}

	public List<Municipio> carregarCidades() {
		MunicipioBean municBean = new MunicipioBean();
		System.out.println("municipios"+ufId);
		listaMunicipios = municBean.cargaCidades(ufId);
		return listaMunicipios;
	}

	public List<Municipio> carregarCidadesCob() {
		MunicipioBean municBean = new MunicipioBean();
		listaMunicipiosCob = municBean.cargaCidades(ufIdCob);
		return listaMunicipiosCob;
	}

	public List<ClienteContato> carregarContatosClientes(Cliente cliente) {
		ClienteRepository repository = new ClienteRepository(this.getManager());
		this.listaClienteContatos = repository.clienteContatos(cliente);
		return listaClienteContatos;
	}

	public void editarContato(ClienteContato clienteContato) {
		this.setClienteContato(clienteContato);
	}

	public void editar(Cliente cliente) {
		this.setCliente(cliente);
		this.setEndereco(cliente.getEndereco());
		this.setEnderecoCobranca(enderecoCobranca);
		this.setSelectMunicipio(endereco.getMunicipio().getId());
		this.setUfId(endereco.getMunicipio().getUf().getId());
		carregarCidades();
		carregarContatosClientes(cliente);
		this.setSelectCnae(cliente.getCnaeId().getId());
		this.setSelectTipoContrato(cliente.getTipoContratoId().getId());
		this.setSelectTipoRisco(cliente.getTipoRiscoId().getId());
		this.setSelectTipoFatura(cliente.getTipoFaturaId().getId());
		this.setSelectTipoCobranca(cliente.getTipoCobrancaId().getId());
		this.setSelectMes(cliente.getProgramacaoMes());
		this.excluiContato = false;
		this.selectMunicipioCob = null;
		this.ufIdCob = null;
		this.listaMunicipiosCob = null;
		this.excluiEndCob = false;

		setCurrentState(EDITAR_STATE);

	}

	public void excluir(Cliente cliente) {
		ClienteRepository clienteRepository = new ClienteRepository(this.getManager());
		clienteRepository.excluir(cliente);
		this.cliente = new Cliente();
		this.listaClientes = null;
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(FacesMessage.SEVERITY_INFO, "Cliente Excluido", ""));
	}

	public void excluirEndCob() {
		excluiEndCob = true;

	}

	public void salvarContato() {
		if (listaClienteContatos == null) {
			if (cliente.getId() != null) {
				System.out.println("cliente nao null " + cliente.getId());
				listaClienteContatos = cliente.getClienteContatos();
			}
		}
		listaClienteContatos.add(clienteContato);
		System.out.println("saindo salva " + clienteContato.getCliente().getId());
		System.out.println("saindo salva " + listaClienteContatos.size());

	}

	public void salvar() {
		try {
			EmpresaRepository empresaRepository = new EmpresaRepository(getManager());
			Empresa empresa = empresaRepository.empresaById(1L);
			cliente.setEmpresasId(empresa); // como pegar a empresa;
			ClienteRepository clienteRepository = new ClienteRepository(getManager());

			MunicipioRepository municRepository = new MunicipioRepository(getManager());
			Municipio munic = municRepository.municipioById(selectMunicipio);

			CnaeRepository cnaeRepository = new CnaeRepository(getManager());
			Cnae cnae = cnaeRepository.cnaeById(selectCnae);

			TipoRiscoRepository tpRiscoRepository = new TipoRiscoRepository(getManager());
			TipoRisco tpRisco = tpRiscoRepository.tipoRiscoById(selectTipoRisco);
			TipoFaturaRepository tpFaturaRepository = new TipoFaturaRepository(getManager());
			TipoFatura tpFatura = tpFaturaRepository.tipoFaturaById(selectTipoFatura);
			TipoContratoRepository tpContratoRepository = new TipoContratoRepository(getManager());
			TipoContrato tpContrato = tpContratoRepository.tipoContratoById(selectTipoContrato);
			TipoCobrancaRepository tpCobrancaRepository = new TipoCobrancaRepository(getManager());
			TipoCobranca tpCobranca = tpCobrancaRepository.tipoCobrancaById(selectTipoCobranca);
			SituacaoClienteRepository situacaoRepository = new SituacaoClienteRepository(getManager());
			SituacaoCliente situacao = situacaoRepository.situacaoById(1l);

			if (excluiEndCob == true) {
				EnderecoRepository endCob = new EnderecoRepository(getManager());
				endCob.excluir(enderecoCobranca);
				enderecoCobranca = new Endereco();
				cliente.setEnderecoCobranca(null);
			}
			if (enderecoCobranca.getLogradouro() != null && !"".equals(enderecoCobranca.getLogradouro())) {
				Municipio municCob = municRepository.municipioById(selectMunicipioCob);
				enderecoCobranca.setMunicipio(municCob);
				enderecoCobranca.setTipo(TipoEnderecoEnum.cob);
				cliente.setEnderecoCobranca(enderecoCobranca);
			} else {
				enderecoCobranca = new Endereco();
			}
			if (excluiContato == true) {

			}
			endereco.setMunicipio(munic);
			endereco.setTipo(TipoEnderecoEnum.com);
			cliente.setEndereco(endereco);
			cliente.setEnderecoCobranca(enderecoCobranca);
			cliente.setProgramacaoMes(selectMes);
			cliente.setCnaeId(cnae);
			cliente.setTipoRiscoId(tpRisco);
			cliente.setTipoFaturaId(tpFatura);
			cliente.setTipoContratoId(tpContrato);
			cliente.setTipoCobrancaId(tpCobranca);
			cliente.setSituacao(situacao);
			cliente.setClienteContatos(listaClienteContatos);
			System.out.println("salvar " + listaClienteContatos.size());

			if (cliente.getId() == null) {
				clienteRepository.salvar(cliente);
			} else {
				clienteRepository.alterar(cliente);
			}
			limpar();
		} catch (Exception e) {
			System.out.println("erro" + e.getMessage());

		}
	}

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

	public UIPanel getPanelForm() {
		return panelForm;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public ClienteContato getClienteContato() {
		return clienteContato;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public Long getId() {
		return id;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public Long getSelectMunicipio() {
		return selectMunicipio;
	}

	public Long getUfId() {
		return ufId;
	}

	public Long getSelectMunicipioCob() {
		return selectMunicipioCob;
	}

	public Long getUfIdCob() {
		return ufIdCob;
	}

	public Cnae getCnae() {
		return cnae;
	}

	public String getCnaeDescricao() {
		return cnaeDescricao;
	}

	public Long getSelectCliente() {
		return selectCliente;
	}

	public Long getSelectCnae() {
		return selectCnae;
	}

	public Long getSelectTipoContrato() {
		return selectTipoContrato;
	}

	public Long getSelectTipoCobranca() {
		return selectTipoCobranca;
	}

	public Long getSelectTipoFatura() {
		return selectTipoFatura;
	}

	public Long getSelectTipoRisco() {
		return selectTipoRisco;
	}

	public MesesEnum getSelectMes() {
		return selectMes;
	}

	public List<MesesEnum> getListaMeses() {
		return listaMeses;
	}

	public List<Cliente> getFilter() {
		return filter;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public List<ClienteContato> getListaClienteContatos() {
		return listaClienteContatos;
	}

	public List<Municipio> getListaMunicipios() {
		return listaMunicipios;
	}

	public List<Municipio> getListaMunicipiosCob() {
		return listaMunicipiosCob;
	}

	public Boolean getExcluiEndCob() {
		return excluiEndCob;
	}

	public Boolean getExcluiContato() {
		return excluiContato;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	public void setPanelForm(UIPanel panelForm) {
		this.panelForm = panelForm;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setClienteContato(ClienteContato clienteContato) {
		this.clienteContato = clienteContato;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setSelectMunicipio(Long selectMunicipio) {
		this.selectMunicipio = selectMunicipio;
	}

	public void setUfId(Long ufId) {
		this.ufId = ufId;
	}

	public void setSelectMunicipioCob(Long selectMunicipioCob) {
		this.selectMunicipioCob = selectMunicipioCob;
	}

	public void setUfIdCob(Long ufIdCob) {
		this.ufIdCob = ufIdCob;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	public void setCnaeDescricao(String cnaeDescricao) {
		this.cnaeDescricao = cnaeDescricao;
	}

	public void setSelectCliente(Long selectCliente) {
		this.selectCliente = selectCliente;
	}

	public void setSelectCnae(Long selectCnae) {
		this.selectCnae = selectCnae;
	}

	public void setSelectTipoContrato(Long selectTipoContrato) {
		this.selectTipoContrato = selectTipoContrato;
	}

	public void setSelectTipoCobranca(Long selectTipoCobranca) {
		this.selectTipoCobranca = selectTipoCobranca;
	}

	public void setSelectTipoFatura(Long selectTipoFatura) {
		this.selectTipoFatura = selectTipoFatura;
	}

	public void setSelectTipoRisco(Long selectTipoRisco) {
		this.selectTipoRisco = selectTipoRisco;
	}

	public void setSelectMes(MesesEnum selectMes) {
		this.selectMes = selectMes;
	}

	public void setListaMeses(List<MesesEnum> listaMeses) {
		this.listaMeses = listaMeses;
	}

	public void setFilter(List<Cliente> filter) {
		this.filter = filter;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public void setListaClienteContatos(List<ClienteContato> listaClienteContatos) {
		this.listaClienteContatos = listaClienteContatos;
	}

	public void setListaMunicipios(List<Municipio> listaMunicipios) {
		this.listaMunicipios = listaMunicipios;
	}

	public void setListaMunicipiosCob(List<Municipio> listaMunicipiosCob) {
		this.listaMunicipiosCob = listaMunicipiosCob;
	}

	public void setExcluiEndCob(Boolean excluiEndCob) {
		this.excluiEndCob = excluiEndCob;
	}

	public void setExcluiContato(Boolean excluiContato) {
		this.excluiContato = excluiContato;
	}

	
}
