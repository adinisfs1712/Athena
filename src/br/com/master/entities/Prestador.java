package br.com.master.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "prestadores")
@PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
public class Prestador extends Fornecedor {

    private static final long serialVersionUID = 1L;

    @Column(name = "tipo_documento")
    private String tipoDocumento;
    @Column(name = "numero_documento")
    private String numeroDocumento;
    @ManyToOne
    @JoinColumn(name = "especialidades_id")
    private Especialidade especialidade;

    public String getTipoDocumento() {
	return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
	return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
	this.numeroDocumento = numeroDocumento;
    }

    public Especialidade getEspecialidade() {
	return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
	this.especialidade = especialidade;
    }

}
