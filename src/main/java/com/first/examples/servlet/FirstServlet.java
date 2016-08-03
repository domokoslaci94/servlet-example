package com.first.examples.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.examples.data.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user")
public class FirstServlet extends HttpServlet {


  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    HttpSession session = req.getSession();
    PrintWriter writer = resp.getWriter();
    resp.setCharacterEncoding("UTF-8");
    User user = (User) session.getAttribute("userObject");

    ObjectMapper mapper = new ObjectMapper();
    String response = mapper.writeValueAsString(user);

    writer.write(response);

    System.out.println("doGet called");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    HttpSession session = req.getSession();
    PrintWriter writer = resp.getWriter();
    resp.setCharacterEncoding("UTF-8");

    String name = req.getParameter("name");
    LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
    double weight = Double.parseDouble(req.getParameter("weight"));
    User user = new User(name, birthday, weight);

    session.setAttribute("userObject", user);
    writer.write("User created with arguments: " + user);

    System.out.println("doPost called");
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    PrintWriter writer = resp.getWriter();
    resp.setCharacterEncoding("UTF-8");

    session.removeAttribute("userObject");
    writer.write("User deleted");

    System.out.println("doDelete called");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    PrintWriter writer = resp.getWriter();
    resp.setCharacterEncoding("UTF-8");

    String name = req.getParameter("name");
    LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
    double weight = Double.parseDouble(req.getParameter("weight"));

    User user = new User(name, birthday, weight);
    session.setAttribute("userObject", user);
    writer.write("User Modified");

    System.out.println("doPut called");
  }
}
