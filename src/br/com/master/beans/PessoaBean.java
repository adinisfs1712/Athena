package br.com.master.beans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import br.com.master.entities.Pessoa;
import br.com.master.entities.PessoaContato;
import br.com.master.repository.EmpresaRepository;
import br.com.master.repository.PessoaRepository;

@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean extends BaseBean {

    private static final long serialVersionUID = 1L;

    private Pessoa pessoa = new Pessoa();
    private PessoaContato pessoaContato = new PessoaContato();

    private Long id;
    private String nome;
    private List<PessoaContato> listaPessoasContatos;

    private List<Pessoa> listaPessoas;

    private List<PessoaContato> listaPessoaContatos;

    public void criar() {
	pessoa = new Pessoa();
	pessoaContato = new PessoaContato();
    }

    public void limpar() {
	criar();
	// listaPessoas = null;
	listaPessoasContatos = null;
    }

    public Long getContarPessoas() {
	PessoaRepository repository = new PessoaRepository(this.getManager());
	return repository.countPessoas();
    }

    public void listar() {
	limpar();
    }

    public List<Pessoa> getListarAllPessoas() {
	if (listaPessoas == null) {
	    PessoaRepository repository = new PessoaRepository(
		    this.getManager());
	    this.listaPessoas = repository.allPessoas();
	}
	return listaPessoas;
    }

    public void carregarContatosPessoas(Pessoa pessoa) {
	PessoaRepository repository = new PessoaRepository(this.getManager());
	this.listaPessoaContatos = repository.pessoaContatos(pessoa);
	System.out.println("pessoas cont" + listaPessoaContatos.size());
    }

    public void excluir(Pessoa pessoa) {
	PessoaRepository pessoaRepository = new PessoaRepository(
		this.getManager());
	pessoaRepository.excluir(pessoa);
	this.pessoa = new Pessoa();
	this.listaPessoas = null;
	FacesContext context = FacesContext.getCurrentInstance();
	context.addMessage("anotherKey", new FacesMessage(
		FacesMessage.SEVERITY_INFO, "Pessoa Excluido", ""));
    }

    public void salvarContato() {
	System.out.println("Pessoa Bean salvando contato");

	if (listaPessoaContatos == null) {
	    if (pessoa.getId() != null) {
		System.out.println("pessoa nao null" + pessoa.getId());
		listaPessoaContatos = pessoa.getPessoaContatos();
	    }
	    System.out.println("entrou if salva");
	}
	listaPessoaContatos.add(pessoaContato);
	System.out.println("saindo salva " + listaPessoaContatos.size());

    }

    public void salvar() {
	EmpresaRepository empresaRepository = new EmpresaRepository(
		getManager());
	PessoaRepository pessoaRepository = new PessoaRepository(getManager());
    }

    private EntityManager getManager() {
	FacesContext fc = FacesContext.getCurrentInstance();
	ExternalContext ec = fc.getExternalContext();
	HttpServletRequest request = (HttpServletRequest) ec.getRequest();
	return (EntityManager) request.getAttribute("entityManager");
    }

}
