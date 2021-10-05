package br.com.master.util.teste;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Prestador;

public class Teste {

    public static void main(String[] args) {

	Prestador prestador = new Prestador();

	// TODO Auto-generated method stub

    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

}
