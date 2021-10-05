package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Email;

import br.com.master.enums.MesesEnum;

@Entity
@Table(name = "clientes")
public class Cliente extends BaseEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cliente_id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "situacao_cliente_id")
	private SituacaoCliente situacao;

	@Column(name = "razao_social")
	private String razaoSocial;

	@Column(name = "nome_fantasia")
	private String nomeFantasia;

	@Email
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inclusao")
	private Date dataInclusao;

	private String cnpj;

	private String cpf;

	private String cei;

	@Column(name = "insc_estadual")
	private String inscEstadual;

	@Column(name = "insc_municipal")
	private String inscMunicipal;

	@Column(name = "periodico")
	@Enumerated(EnumType.STRING)
	private MesesEnum programacaoMes;

	@Column(name = "numero_contrato")
	private String numeroContrato;

	@Column(name = "dia_fatura")
	private String diaFatura;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_inicio_tp_fatura")
	private Date dataInicioTpFatura;

	@Column(name = "tabela_preco")
	private String tabelaPreco;

	@Column(name = "numero_func_limite")
	private String numeroFuncLimite;

	@Column(name = "valor_acima_limite")
	private BigDecimal valorAcimaLimite;

	@Column(name = "valor_mensal")
	private BigDecimal valorMensal;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_exclusao")
	private Date dataExclusao;

	@ManyToOne
	@JoinColumn(name = "motivos_exclusao_id")
	private MotivoExclusao motivoExclusaoId;

	@ManyToOne
	@JoinColumn(name = "tipos_cobranca_id")
	private TipoCobranca tipoCobrancaId;

	@ManyToOne
	@JoinColumn(name = "tipos_contrato_id")
	private TipoContrato tipoContratoId;

	@ManyToOne
	@JoinColumn(name = "tipos_fatura_id")
	private TipoFatura tipoFaturaId;

	@ManyToOne
	@JoinColumn(name = "tipos_riscos_id")
	private TipoRisco tipoRiscoId;

	@ManyToOne
	@JoinColumn(name = "cnae_id")
	private Cnae cnaeId;

	private Endereco endereco;

	private Endereco enderecoCobranca;

	@ManyToOne
	@JoinColumn(name = "empresas_id")
	private Empresa empresasId;

	@OneToMany(mappedBy = "cliente", targetEntity = ClienteContato.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ClienteContato> clienteContatos;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "clientes_setores", joinColumns = { @JoinColumn(name = "clientes_id") }, inverseJoinColumns = {
			@JoinColumn(name = "setores_id") })
	private List<Setor> setores;

	public Cliente() {
	}

	public Long getId() {
		return id;
	}

	public SituacaoCliente getSituacao() {
		return situacao;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public String getEmail() {
		return email;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public String getCei() {
		return cei;
	}

	public String getInscEstadual() {
		return inscEstadual;
	}

	public String getInscMunicipal() {
		return inscMunicipal;
	}

	public MesesEnum getProgramacaoMes() {
		return programacaoMes;
	}

	public String getNumeroContrato() {
		return numeroContrato;
	}

	public String getDiaFatura() {
		return diaFatura;
	}

	public Date getDataInicioTpFatura() {
		return dataInicioTpFatura;
	}

	public String getTabelaPreco() {
		return tabelaPreco;
	}

	public String getNumeroFuncLimite() {
		return numeroFuncLimite;
	}

	public BigDecimal getValorAcimaLimite() {
		return valorAcimaLimite;
	}

	public BigDecimal getValorMensal() {
		return valorMensal;
	}

	public Date getDataExclusao() {
		return dataExclusao;
	}

	public MotivoExclusao getMotivoExclusaoId() {
		return motivoExclusaoId;
	}

	public TipoCobranca getTipoCobrancaId() {
		return tipoCobrancaId;
	}

	public TipoContrato getTipoContratoId() {
		return tipoContratoId;
	}

	public TipoFatura getTipoFaturaId() {
		return tipoFaturaId;
	}

	public TipoRisco getTipoRiscoId() {
		return tipoRiscoId;
	}

	public Cnae getCnaeId() {
		return cnaeId;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Endereco getEnderecoCobranca() {
		return enderecoCobranca;
	}

	public Empresa getEmpresasId() {
		return empresasId;
	}

	public List<ClienteContato> getClienteContatos() {
		return clienteContatos;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSituacao(SituacaoCliente situacao) {
		this.situacao = situacao;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setCei(String cei) {
		this.cei = cei;
	}

	public void setInscEstadual(String inscEstadual) {
		this.inscEstadual = inscEstadual;
	}

	public void setInscMunicipal(String inscMunicipal) {
		this.inscMunicipal = inscMunicipal;
	}

	public void setProgramacaoMes(MesesEnum programacaoMes) {
		this.programacaoMes = programacaoMes;
	}

	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}

	public void setDiaFatura(String diaFatura) {
		this.diaFatura = diaFatura;
	}

	public void setDataInicioTpFatura(Date dataInicioTpFatura) {
		this.dataInicioTpFatura = dataInicioTpFatura;
	}

	public void setTabelaPreco(String tabelaPreco) {
		this.tabelaPreco = tabelaPreco;
	}

	public void setNumeroFuncLimite(String numeroFuncLimite) {
		this.numeroFuncLimite = numeroFuncLimite;
	}

	public void setValorAcimaLimite(BigDecimal valorAcimaLimite) {
		this.valorAcimaLimite = valorAcimaLimite;
	}

	public void setValorMensal(BigDecimal valorMensal) {
		this.valorMensal = valorMensal;
	}

	public void setDataExclusao(Date dataExclusao) {
		this.dataExclusao = dataExclusao;
	}

	public void setMotivoExclusaoId(MotivoExclusao motivoExclusaoId) {
		this.motivoExclusaoId = motivoExclusaoId;
	}

	public void setTipoCobrancaId(TipoCobranca tipoCobrancaId) {
		this.tipoCobrancaId = tipoCobrancaId;
	}

	public void setTipoContratoId(TipoContrato tipoContratoId) {
		this.tipoContratoId = tipoContratoId;
	}

	public void setTipoFaturaId(TipoFatura tipoFaturaId) {
		this.tipoFaturaId = tipoFaturaId;
	}

	public void setTipoRiscoId(TipoRisco tipoRiscoId) {
		this.tipoRiscoId = tipoRiscoId;
	}

	public void setCnaeId(Cnae cnaeId) {
		this.cnaeId = cnaeId;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void setEnderecoCobranca(Endereco enderecoCobranca) {
		this.enderecoCobranca = enderecoCobranca;
	}

	public void setEmpresasId(Empresa empresasId) {
		this.empresasId = empresasId;
	}

	public void setClienteContatos(List<ClienteContato> clienteContatos) {
		this.clienteContatos = clienteContatos;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	
	

}