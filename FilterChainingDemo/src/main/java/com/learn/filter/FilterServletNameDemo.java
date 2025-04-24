package com.learn.filter;

import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class FilterServletNameDemo
 */
@WebFilter(servletNames = {"demo","login", "welcome"})
public class FilterServletNameDemo implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public FilterServletNameDemo() {
        super();
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		System.out.println("Entered into FilterServletNameDemo destroy() method");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("Entered into FilterServletNameDemo filter");
		chain.doFilter(request, response);
		System.out.println("Exiting from FilterServletNameDemo filter");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Entered into FilterServletNameDemo init() method");
	}

}
