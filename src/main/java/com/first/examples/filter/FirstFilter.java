package com.first.examples.filter;

import com.first.examples.data.TokenStorage;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/user")
public class FirstFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    System.out.println("Init called");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                       FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) servletRequest;
    PrintWriter writer = ((HttpServletResponse) servletResponse).getWriter();

    if (req.getMethod().equals("POST")) {
      filterChain.doFilter(servletRequest, servletResponse);
    } else {
      String token = req.getHeader("token");
      if (token.equals(TokenStorage.INSTANCE.getToken(req.getSession().getId()))) {
        writer.write("Authorized");
        filterChain.doFilter(servletRequest, servletResponse);
      } else {
        writer.write("Unauthorized!");
      }
    }
    System.out.println("doFilter called");
  }

  @Override
  public void destroy() {
    System.out.println("Destroy called");
  }
}
