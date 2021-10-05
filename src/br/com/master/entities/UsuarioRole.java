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
 * The persistent class for the user_roles database table.
 * 
 */
@Entity
@Table(name = "usuario_permissao")
public class UsuarioRole extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    private String authority;

    // bi-directional many-to-one association to User
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public UsuarioRole() {
    }

    public Long getId() {
	return this.id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getAuthority() {
	return this.authority;
    }

    public void setAuthority(String authority) {
	this.authority = authority;
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

}