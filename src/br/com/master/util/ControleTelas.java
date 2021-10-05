package br.com.master.util;

import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FlowEvent;

import br.com.master.entities.Usuario;

public class ControleTelas {

    private boolean skip;

    private Usuario usuario = new Usuario();
    private static Logger logger = Logger.getLogger(Usuario.class.getName());

    public void save(ActionEvent actionEvent) {
	// Persist user

	FacesMessage msg = new FacesMessage("Successful", "Welcome :"
		+ usuario.getNome());
	FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public String onFlowProcess(FlowEvent event) {
	logger.info("Current wizard step:" + event.getOldStep());
	logger.info("Next step:" + event.getNewStep());

	if (skip) {
	    skip = false; // reset in case user goes back
	    return "confirm";
	} else {
	    return event.getNewStep();
	}
    }

    public Usuario getUsuario() {
	return usuario;
    }

    public void setUsuario(Usuario usuario) {
	this.usuario = usuario;
    }

    public static Logger getLogger() {
	return logger;
    }

    public static void setLogger(Logger logger) {
	ControleTelas.logger = logger;
    }

    public boolean isSkip() {
	return skip;
    }

    public void setSkip(boolean skip) {
	this.skip = skip;
    }
}
