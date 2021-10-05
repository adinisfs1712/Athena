package br.com.master.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.master.enums.TipoPessoaEnum;

@Entity
@Table(name = "fornecedores")
@Inheritance(strategy = InheritanceType.JOINED)
public class Fornecedor extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "situacao_id")
    private Situacao situacao;

    @ManyToOne
    @JoinColumn(name = "tipos_fornecedor_id")
    private TipoFornecedor tipoFornecedor;

    @Column(name = "tipo_pessoa", nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoPessoaEnum pessoa;

    @Column(name = "razao_social")
    private String razaoSocial;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "data_exclusao")
    private Date dataExclusao;

    @ManyToOne
    @JoinColumn(name = "motivos_exclusao_id")
    private MotivoExclusao motivoExclusaoId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enderecos_id")
    private Endereco endereco;

    @ManyToOne
    @JoinColumn(name = "empresas_id")
    private Empresa empresasId;

    @OneToMany(mappedBy = "fornecedor")
    private List<FornecedorContato> fornecedorContatos;

    public Fornecedor() {
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public TipoPessoaEnum getPessoa() {
	return pessoa;
    }

    public void setPessoa(TipoPessoaEnum pessoa) {
	this.pessoa = pessoa;
    }

    public Situacao getSituacao() {
	return situacao;
    }

    public void setSituacao(Situacao situacao) {
	this.situacao = situacao;
    }

    public String getRazaoSocial() {
	return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
	this.razaoSocial = razaoSocial;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Date getDataInclusao() {
	return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
	this.dataInclusao = dataInclusao;
    }

    public String getCnpj() {
	return cnpj;
    }

    public void setCnpj(String cnpj) {
	this.cnpj = cnpj;
    }

    public String getCpf() {
	return cpf;
    }

    public void setCpf(String cpf) {
	this.cpf = cpf;
    }

    public String getCei() {
	return cei;
    }

    public void setCei(String cei) {
	this.cei = cei;
    }

    public String getInscEstadual() {
	return inscEstadual;
    }

    public void setInscEstadual(String inscEstadual) {
	this.inscEstadual = inscEstadual;
    }

    public String getInscMunicipal() {
	return inscMunicipal;
    }

    public void setInscMunicipal(String inscMunicipal) {
	this.inscMunicipal = inscMunicipal;
    }

    public Date getDataExclusao() {
	return dataExclusao;
    }

    public void setDataExclusao(Date dataExclusao) {
	this.dataExclusao = dataExclusao;
    }

    public MotivoExclusao getMotivoExclusaoId() {
	return motivoExclusaoId;
    }

    public void setMotivoExclusaoId(MotivoExclusao motivoExclusaoId) {
	this.motivoExclusaoId = motivoExclusaoId;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public void setEndereco(Endereco endereco) {
	this.endereco = endereco;
    }

    public Empresa getEmpresasId() {
	return empresasId;
    }

    public void setEmpresasId(Empresa empresasId) {
	this.empresasId = empresasId;
    }

    public List<FornecedorContato> getFornecedorContatos() {
	return fornecedorContatos;
    }

    public void setFornecedorContatos(List<FornecedorContato> fornecedorContatos) {
	this.fornecedorContatos = fornecedorContatos;
    }

    public TipoFornecedor getTipoFornecedor() {
	return tipoFornecedor;
    }

    public void setTipofornecedor(TipoFornecedor tipoFornecedor) {
	this.tipoFornecedor = tipoFornecedor;
    }

}