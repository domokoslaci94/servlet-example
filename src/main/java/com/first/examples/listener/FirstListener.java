package com.first.examples.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Csaba_Lamfalusi on 8/2/2016.
 */
@WebListener
public class FirstListener implements HttpSessionAttributeListener {

  public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
    System.out.println("AttributeAdded called: " + httpSessionBindingEvent.getName() + " "
        + httpSessionBindingEvent.getValue());
  }

  public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    System.out.println("AttributeRemoved called: " + httpSessionBindingEvent.getName() + " "
        + httpSessionBindingEvent.getValue());
  }

  public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
    System.out.println(
        "AttributeReplaced called: " + httpSessionBindingEvent.getSession().getAttribute("name")
            + " " + httpSessionBindingEvent.getValue());
  }

}
