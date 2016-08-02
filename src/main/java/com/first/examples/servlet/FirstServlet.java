package com.first.examples.servlet;

import com.first.examples.data.User;

import java.io.IOException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/user")
public class FirstServlet extends HttpServlet {

  User testUser;

  @Override
  public void init() throws ServletException {
    super.init();
    testUser = new User("Laci", LocalDate.of(1994, 8, 15), 60);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    String name = req.getParameter("name");
    if (name.equalsIgnoreCase(testUser.getName())) {
      System.out.println(testUser);
    } else {
      System.out.println("user not found");
    }
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

  }
}
