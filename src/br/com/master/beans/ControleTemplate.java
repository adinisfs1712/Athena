package br.com.master.beans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class ControleTemplate {

    private String template;

    public String getTemplate() {
	return "../templates/padraoh.xhtml";

    }

    public void setTemplate(String template) {
	this.template = template;
    }

}
