package br.com.master.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Cnae;
import br.com.master.repository.CnaeRepository;

@ManagedBean(name = "cnaeBean")
@ViewScoped
public class CnaeBean extends BaseBean {

	private static final long serialVersionUID = 1L;
	private Cnae cnae = new Cnae();
	private List<Cnae> listaCnae;
	private Long selectCnae;

	@PostConstruct
	public void init() {
		carregarCnae();
	}

	public void carregarCnae() {
		if (listaCnae == null) {
			CnaeRepository repository = new CnaeRepository(this.getManager());			
			this.listaCnae = repository.allCnae();
		}
	}

	public String limpar() {
		this.cnae = new Cnae();
		this.listaCnae = null;
		carregarCnae();
		return "cnae";
	}

	public String salvar() {
		CnaeRepository cnaeRepository = new CnaeRepository(getManager());
		if (cnae.getId() == null) {
			cnaeRepository.salvar(cnae);
		} else {
			cnaeRepository.alterar(cnae);
		}
		this.cnae = new Cnae();
		this.listaCnae = null;
		carregarCnae();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("someKey", new FacesMessage(FacesMessage.SEVERITY_INFO, "CNAE Atualizado", ""));
		return "cnae";
	}

	public void excluir(Cnae cnae) {
		CnaeRepository cnaeRepository = new CnaeRepository(this.getManager());
		cnaeRepository.excluir(cnae);
		this.cnae = new Cnae();
		this.listaCnae = null;
		carregarCnae();
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("anotherKey", new FacesMessage(FacesMessage.SEVERITY_INFO, "CNAE Excluido", ""));
	}

	public String editar(Cnae cnae) {
		this.setCnae(cnae);
		return "cnae?faces-redirect=true";
	}

	public String descricaoCnae(Long id) {
		String descricao = null;
		if (id != 0) {
			CnaeBean cnaeBean = new CnaeBean();
			Cnae cnae = cnaeBean.findById(id);
			descricao = cnae.getDescricao();
		}
		return descricao;
	}

	public Long getCount() {
		CnaeRepository repository = new CnaeRepository(this.getManager());
		return repository.countCnae();
	}

	public Cnae findById(Long id) {
		CnaeRepository repository = new CnaeRepository(getManager());
		cnae = repository.cnaeById(id);
		return cnae;
	}

	private EntityManager getManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		return (EntityManager) request.getAttribute("entityManager");
	}

	public Cnae getCnae() {
		return cnae;
	}

	public void setCnae(Cnae cnae) {
		this.cnae = cnae;
	}

	public List<Cnae> getListaCnae() {
		return listaCnae;
	}

	public Long getSelectCnae() {
		return selectCnae;
	}

	public void setSelectCnae(Long selectCnae) {
		this.selectCnae = selectCnae;
	}

}
