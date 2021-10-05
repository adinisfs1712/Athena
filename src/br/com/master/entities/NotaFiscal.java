package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the notas_fiscais database table.
 * 
 */
@Entity
@Table(name = "notas_fiscais")
public class NotaFiscal extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissao")
    private Date dataEmissao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_vencimento")
    private Date dataVencimento;

    // bi-directional many-to-one association to NotaFiscalItens
    @OneToMany(mappedBy = "notasFiscais")
    private List<NotaFiscalItens> notaFiscalItens;

    // bi-directional many-to-one association to Cliente
    @ManyToOne
    @JoinColumn(name = "clientes_id")
    private Cliente cliente;

    public NotaFiscal() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Date getDataEmissao() {
	return this.dataEmissao;
    }

    public void setDataEmissao(Date dataEmissao) {
	this.dataEmissao = dataEmissao;
    }

    public Date getDataVencimento() {
	return this.dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
	this.dataVencimento = dataVencimento;
    }

    public List<NotaFiscalItens> getNotaFiscalItens() {
	return this.notaFiscalItens;
    }

    public void setNotaFiscalItens(List<NotaFiscalItens> notaFiscalItens) {
	this.notaFiscalItens = notaFiscalItens;
    }

    public Cliente getCliente() {
	return this.cliente;
    }

    public void setCliente(Cliente cliente) {
	this.cliente = cliente;
    }

}