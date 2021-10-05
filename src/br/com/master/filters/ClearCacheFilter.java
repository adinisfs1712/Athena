package br.com.master.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class ClearCacheFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response,
	    FilterChain chain) throws IOException, ServletException {
	HttpServletResponse httpResponse = (HttpServletResponse) response;

	httpResponse.setHeader("Expires", "Sat, 1 Jan 1990 12:00:00 GMT");
	httpResponse.setHeader("Cache-Control",
		"no-store, no-cache, must-revalidate");
	httpResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
	httpResponse.setHeader("Pragma", "no-cache");

	chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
    }

}