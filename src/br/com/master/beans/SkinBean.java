package br.com.master.beans;

import java.util.Map;
import java.util.TreeMap;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "skinBean")
@SessionScoped
public class SkinBean {
    private String meuTema;
    private Map<String, String> themes;

    public SkinBean() {
	themes = new TreeMap<String, String>();
	themes.put("Afternoon", "afternoon");
	themes.put("Afterwork", "afterwork");
	themes.put("Black-Tie", "black-tie");
	themes.put("Bluesky", "bluesky");
	themes.put("Bootstrap", "bootstrap");
	themes.put("Casablanca", "casablanca");
	themes.put("Cupertino", "cupertino");
	themes.put("Glass X", "glass-x");
	themes.put("Le Frog", "le-frog");
	themes.put("Overcast", "overcast");
	themes.put("Pepper", "pepper-grinder");
	themes.put("Redmond", "redmond");
	themes.put("Sam", "sam");
	themes.put("Smoothness", "smoothness");
	themes.put("Start", "start");
	themes.put("Sunny", "sunny");
	themes.put("Swank-Purse", "swanky-purse");
	themes.put("Ui-Lightness", "ui-lightness");
	themes.put("Vader", "vader");

    }

    public void salvarTema() {

    }

    public String getMeuTema() {
	if (meuTema == null)
	    meuTema = "cupertino";
	return meuTema;
    }

    public void setMeuTema(String meuTema) {
	this.meuTema = meuTema;
    }

    public Map<String, String> getThemes() {
	return themes;
    }

    public void setThemes(Map<String, String> themes) {
	this.themes = themes;
    }

}
