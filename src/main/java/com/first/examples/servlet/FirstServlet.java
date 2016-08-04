package com.first.examples.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.first.examples.data.TokenStorage;
import com.first.examples.data.User;
import com.first.examples.data.ValidType;

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

  private final Double MAX_WEIGHT = 1000d;
  private final Double MIN_WEIGHT = 0.1d;

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {

    HttpSession session = req.getSession();
    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();

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
    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();

    boolean isValidInput = true;

    String name = req.getParameter("name");
    String birthday = req.getParameter("birthday");
    String weight = req.getParameter("weight");

    LocalDate parsedBirthday;
    Double parsedWeight;

    if (isValidUserName(name) == ValidType.MISSING_ARGUMENT) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      writer.write("Username can not be empty!\n");
      isValidInput = false;
    }

    switch (isValidBirthDate(birthday)) {
      case MISSING_ARGUMENT:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("Birth date can not be empty!\n");
        isValidInput = false;
        break;

      case INVALID_ARGUMENT:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("The given birth date is not a valid date0\n!");
        isValidInput = false;
        break;

      case ARGUMENT_OVER_BOUNDARY:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("Are you sure you was born in the future?\n");
        isValidInput = false;
        break;

      case ARGUMENT_UNDER_BOUNDARY:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write(
            "Are you sure you are " + (LocalDate.now().getYear() - LocalDate.parse(birthday)
                .getYear()) + " years old?\n");
        isValidInput = false;
        break;
    }

    switch (isValidWeight(weight)) {
      case MISSING_ARGUMENT:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("Weight can not be empty!\n");
        isValidInput = false;
        break;

      case INVALID_ARGUMENT:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("The given weight is not a valid weight!\n");
        isValidInput = false;
        break;

      case ARGUMENT_OVER_BOUNDARY:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("Are you sure you are the heaviest man ever with " + Double.parseDouble(weight)
            + " kgs?\n");
        isValidInput = false;
        break;

      case ARGUMENT_UNDER_BOUNDARY:
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        writer.write("Do you even exist? You are only " + Double.parseDouble(weight) + " kg?\n");
        isValidInput = false;
        break;
    }

    if (isValidInput) {
      parsedBirthday = LocalDate.parse(birthday);
      parsedWeight = Double.parseDouble(weight);

      User user = new User(name, parsedBirthday, parsedWeight);
      session.setAttribute("userObject", user);
      resp.setStatus(HttpServletResponse.SC_OK);
      writer.write("User created with arguments: " + user);
      writer.write("\n\nToken ID: " + TokenStorage.INSTANCE.getToken(session.getId()) + "\n");
    }

    System.out.println("doPost called");
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();

    session.removeAttribute("userObject");
    writer.write("User deleted!\n");

    System.out.println("doDelete called");
  }

  @Override
  protected void doPut(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    HttpSession session = req.getSession();
    resp.setContentType("text/html; charset=UTF-8");
    PrintWriter writer = resp.getWriter();

    String name = req.getParameter("name");
    LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
    double weight = Double.parseDouble(req.getParameter("weight"));

    User user = new User(name, birthday, weight);
    session.setAttribute("userObject", user);
    writer.write("User modified!\n");

    System.out.println("doPut called");
  }

  public ValidType isValidUserName(String userName) {
    if (userName.isEmpty() || userName == null || userName.length() == 0 || userName.equals("")) {
      return ValidType.MISSING_ARGUMENT;
    } else {
      return ValidType.VALID;
    }
  }

  public ValidType isValidWeight(String weight) {
    if (weight.isEmpty() || weight == null || weight.length() == 0 || weight.equals("")) {
      return ValidType.MISSING_ARGUMENT;
    } else {
      Double parsedWeight;
      try {
        parsedWeight = Double.parseDouble(weight);
      } catch (Exception ex) {
        return ValidType.INVALID_ARGUMENT;
      }

      if (parsedWeight > MAX_WEIGHT) {
        return ValidType.ARGUMENT_OVER_BOUNDARY;
      } else if (parsedWeight < MIN_WEIGHT) {
        return ValidType.ARGUMENT_UNDER_BOUNDARY;
      } else {
        return ValidType.VALID;
      }
    }
  }

  public ValidType isValidBirthDate(String birthDate) {
    if (birthDate.isEmpty() || birthDate == null || birthDate.length() == 0 || birthDate
        .equals("")) {
      return ValidType.MISSING_ARGUMENT;
    } else {
      LocalDate parsedBirthDate;
      try {
        parsedBirthDate = LocalDate.parse(birthDate);
      } catch (Exception ex) {
        return ValidType.INVALID_ARGUMENT;
      }

      if (parsedBirthDate.isAfter(LocalDate.now())) {
        return ValidType.ARGUMENT_OVER_BOUNDARY;
      } else if ((LocalDate.now().getYear() - parsedBirthDate.getYear()) > 150) {
        return ValidType.ARGUMENT_UNDER_BOUNDARY;
      } else {
        return ValidType.VALID;
      }
    }
  }
}