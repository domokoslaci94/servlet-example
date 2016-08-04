package com.first.examples.listener;

import com.first.examples.data.TokenStorage;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class FirstListener implements HttpSessionAttributeListener {

  public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
    if (httpSessionBindingEvent.getName().equals("userObject") == true) {
      TokenStorage.INSTANCE.addToken(httpSessionBindingEvent.getSession().getId());
    }

  }

  public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    if (httpSessionBindingEvent.getName().equals("userObject") == true) {
      TokenStorage.INSTANCE.removeToken(httpSessionBindingEvent.getSession().getId());
    }
  }

  public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

  }

}
