package com.first.examples.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * Created by Csaba_Lamfalusi on 8/2/2016.
 */
@WebFilter("/first")
public class FirstFilter implements Filter {

  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("Init called!");
  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    System.out.println("DoFilter called! Name parameter: "+servletRequest.getParameter("name"));
    filterChain.doFilter(servletRequest,servletResponse);
  }

  public void destroy() {
    System.out.println("Destroy called!");
  }
}
