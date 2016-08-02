package com.first.examples.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Csaba_Lamfalusi on 8/2/2016.
 */
@WebServlet("/first")
public class FirstServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    HttpSession session = req.getSession();
    String name = req.getParameter("name");
    if (name == null) {
      name = "World";
    } else {
      session.setAttribute("name", name);
    }
    resp.getWriter().print("Hello "+name+"!");
  }

}
