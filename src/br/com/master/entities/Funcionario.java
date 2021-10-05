package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.master.enums.SexoEnum;

/**
 * The persistent class for the funcionarios database table.
 * 
 */
@Entity
@Table(name = "funcionarios")
public class Funcionario extends BaseEntity {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nome;

    private byte aposentado;

    private String area;

    @Column(name = "codigo_pis_pasep")
    private String codigoPisPasep;

    @Column(nullable = false)
    private String cpf;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_admissao", nullable = false)
    private Date dataAdmissao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_demissao")
    private Date dataDemissao;

    @Column(name = "cart_profissional")
    private String cartProfissional;

    @ManyToOne
    @JoinColumn(name = "estado_emissor_prof_id")
    private UnidFederacao estadoEmissorProf;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissao_prof")
    private Date dataEmissaoProf;

    private String identidade;

    @ManyToOne
    @JoinColumn(name = "estado_emissor_ident_id")
    private UnidFederacao estadoEmissorIdent;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissao_ident")
    private Date dataEmissaoIdent;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inclusao", nullable = false)
    private Date dataInclusao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", nullable = false)
    private Date dataNascimento;

    @Column(name = "filiacao_previdencia")
    private String filiacaoPrevidencia;

    @Column(nullable = false)
    private String matricula;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_exclusao")
    private Date dataExclusao;

    @Column(name = "motivo_exclusao")
    private String motivoExclusao;

    @Column(name = "nome_mae")
    private String nomeMae;

    @Column(name = "orgao_emissor_ident")
    private String orgaoEmissorIdent;

    @Column(name = "serie_prof")
    private String serieProf;

    @Column(name = "sexo", nullable = false)
    @Enumerated(EnumType.STRING)
    private SexoEnum sexo;

    @ManyToOne
    @JoinColumn(name = "situacao_id")
    private Situacao situacao;

    @Column(name = "situacao_laboral")
    private String situacaoLaboral;

    // bi-directional many-to-one association to Cliente
    @ManyToOne
    @JoinColumn(name = "clientes_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tipos_riscos_id", nullable = false)
    private TipoRisco tipoRiscoId;

    // bi-directional many-to-one association to Endereco
    @ManyToOne
    @JoinColumn(name = "naturalidade_id")
    private UnidFederacao naturalidade;

    // bi-directional many-to-one association to EstadoCivil
    @ManyToOne
    @JoinColumn(name = "estado_civil_id", nullable = false)
    private EstadoCivil estadoCivil;

    @ManyToOne
    @JoinColumn(name = "turnos_id")
    private Turno turno;

    public Funcionario() {
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

    public byte getAposentado() {
	return aposentado;
    }

    public void setAposentado(byte aposentado) {
	this.aposentado = aposentado;
    }

    public String getArea() {
	return area;
    }

    public void setArea(String area) {
	this.area = area;
    }

    public String getCodigoPisPasep() {
	return codigoPisPasep;
    }

    public void setCodigoPisPasep(String codigoPisPasep) {
	this.codigoPisPasep = codigoPisPasep;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public Date getDataAdmissao() {
	return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
	this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
	return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
	this.dataDemissao = dataDemissao;
    }

    public String getCartProfissional() {
	return cartProfissional;
    }

    public void setCartProfissional(String cartProfissional) {
	this.cartProfissional = cartProfissional;
    }

    public UnidFederacao getEstadoEmissorProf() {
	return estadoEmissorProf;
    }

    public void setEstadoEmissorProf(UnidFederacao estadoEmissorProf) {
	this.estadoEmissorProf = estadoEmissorProf;
    }

    public Date getDataEmissaoProf() {
	return dataEmissaoProf;
    }

    public void setDataEmissaoProf(Date dataEmissaoProf) {
	this.dataEmissaoProf = dataEmissaoProf;
    }

    public String getIdentidade() {
	return identidade;
    }

    public void setIdentidade(String identidade) {
	this.identidade = identidade;
    }

    public UnidFederacao getEstadoEmissorIdent() {
	return estadoEmissorIdent;
    }

    public void setEstadoEmissorIdent(UnidFederacao estadoEmissorIdent) {
	this.estadoEmissorIdent = estadoEmissorIdent;
    }

    public Date getDataEmissaoIdent() {
	return dataEmissaoIdent;
    }

    public void setDataEmissaoIdent(Date dataEmissaoIdent) {
	this.dataEmissaoIdent = dataEmissaoIdent;
    }

    public Date getDataInclusao() {
	return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
	this.dataInclusao = dataInclusao;
    }

    public Date getDataNascimento() {
	return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
	this.dataNascimento = dataNascimento;
    }

    public String getFiliacaoPrevidencia() {
	return filiacaoPrevidencia;
    }

    public void setFiliacaoPrevidencia(String filiacaoPrevidencia) {
	this.filiacaoPrevidencia = filiacaoPrevidencia;
    }

    public String getMatricula() {
	return matricula;
    }

    public void setMatricula(String matricula) {
	this.matricula = matricula;
    }

    public Date getDataExclusao() {
	return dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
	this.dataExclusao = dataExclusao;
    }

    public String getMotivoExclusao() {
	return motivoExclusao;
    }

    public void setMotivoExclusao(String motivoExclusao) {
	this.motivoExclusao = motivoExclusao;
    }

    public String getNomeMae() {
	return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
	this.nomeMae = nomeMae;
    }

    public String getOrgaoEmissorIdent() {
	return orgaoEmissorIdent;
    }

    public void setOrgaoEmissorIdent(String orgaoEmissorIdent) {
	this.orgaoEmissorIdent = orgaoEmissorIdent;
    }

    public String getSerieProf() {
	return serieProf;
    }

    public void setSerieProf(String serieProf) {
	this.serieProf = serieProf;
    }

    public SexoEnum getSexo() {
	return sexo;
    }

    public void setSexo(SexoEnum sexo) {
	this.sexo = sexo;
    }

    public Situacao getSituacao() {
	return situacao;
    }

    public void setSituacao(Situacao situacao) {
	this.situacao = situacao;
    }

    public String getSituacaoLaboral() {
	return situacaoLaboral;
    }

    public void setSituacaoLaboral(String situacaoLaboral) {
	this.situacaoLaboral = situacaoLaboral;
    }

    public Cliente getCliente() {
	return cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

    public TipoRisco getTipoRiscoId() {
	return tipoRiscoId;
    }

    public void setTipoRiscoId(TipoRisco tipoRiscoId) {
	this.tipoRiscoId = tipoRiscoId;
    }

    public UnidFederacao getNaturalidade() {
	return naturalidade;
    }

    public void setNaturalidade(UnidFederacao naturalidade) {
	this.naturalidade = naturalidade;
    }

    public EstadoCivil getEstadoCivil() {
	return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
	this.estadoCivil = estadoCivil;
    }

    public Turno getTurno() {
	return turno;
    }

    public void setTurno(Turno turno) {
	this.turno = turno;
    }

}