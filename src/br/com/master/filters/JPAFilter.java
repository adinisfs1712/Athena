package br.com.master.filters;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(servletNames = { "Faces Servlet" })
public class JPAFilter implements Filter {

    private EntityManagerFactory emf;

    @Override
    public void destroy() {
	this.emf.close();
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc)
	    throws IOException, ServletException {
	EntityManager em = this.emf.createEntityManager();
	req.setAttribute("entityManager", em);
	em.getTransaction().begin();
	fc.doFilter(req, res);
	try {
	    em.getTransaction().commit();
	} catch (Exception e) {
	    em.getTransaction().rollback();
	    throw new ServletException(e.toString());
	} finally {
	    em.close();
	}
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
	this.emf = Persistence.createEntityManagerFactory("masterPU");

    }

}
