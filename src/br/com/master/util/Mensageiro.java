package br.com.master.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensageiro {

    private static ResourceBundle bundle = ResourceBundle.getBundle(
	    "br.com.master.util.idiomas.mensagens", FacesContext
		    .getCurrentInstance().getViewRoot().getLocale());

    public static void errorMsg(String msg) {
	msg = bundle.getString(msg);
	FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg,
		msg);
	FacesContext fc = FacesContext.getCurrentInstance();
	fc.addMessage("erro", fm);
    }

    public static void infoMsg(String msg) {
	msg = bundle.getString(msg);
	FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
	FacesContext fc = FacesContext.getCurrentInstance();
	fc.addMessage("info", fm);
    }
}
