package com.first.examples.data;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public enum TokenStorage {
  INSTANCE;

  private final ConcurrentHashMap<String, String> tokens = new ConcurrentHashMap<>();

  public String getToken(String sessionId) {
    if (tokens.containsKey(sessionId)) {
      return tokens.get(sessionId);
    } else {
      return null;
    }
  }

  public void addToken(String sessionId) {
    String id = UUID.randomUUID().toString();
    System.out.println("id: " + id);
    tokens.put(sessionId, id);
  }

  public String removeToken(String sessionId) {
    if (tokens.containsKey(sessionId)) {
      String result = tokens.get(sessionId);
      tokens.remove(sessionId);
      return result;
    } else {
      return null;
    }
  }

}
