package br.com.master.entities;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * The persistent class for the nota_fiscal_itens database table.
 * 
 */
@Entity
@Table(name = "nota_fiscal_itens")
public class NotaFiscalItens extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "codigo_produto")
    private String codigoProduto;

    private String quantidade;

    @Column(name = "valor_desconto")
    private String valorDesconto;

    @Column(name = "valor_unitario")
    private String valorUnitario;

    // bi-directional many-to-one association to NotasFiscai
    @ManyToOne
    @JoinColumn(name = "notas_fiscais_id")
    private NotaFiscal notasFiscais;

    public NotaFiscalItens() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getCodigoProduto() {
	return this.codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
	this.codigoProduto = codigoProduto;
    }

    public String getQuantidade() {
	return this.quantidade;
    }

    public void setQuantidade(String quantidade) {
	this.quantidade = quantidade;
    }

    public String getValorDesconto() {
	return this.valorDesconto;
    }

    public void setValorDesconto(String valorDesconto) {
	this.valorDesconto = valorDesconto;
    }

    public String getValorUnitario() {
	return this.valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
	this.valorUnitario = valorUnitario;
    }

    public NotaFiscal getNotasFiscais() {
	return this.notasFiscais;
    }

    public void setNotasFiscais(NotaFiscal notasFiscais) {
	this.notasFiscais = notasFiscais;
    }

}