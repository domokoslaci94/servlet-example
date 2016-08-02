package com.first.examples.data;


import java.time.LocalDate;

public class User {
  private String name;
  private LocalDate birthDate;
  private double weight;

  public User() {
  }

  public User(String name, LocalDate birthDate, double weight) {
    this.name = name;
    this.birthDate = birthDate;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LocalDate getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    User user = (User) o;

    if (Double.compare(user.weight, weight) != 0) {
      return false;
    }
    if (name != null ? !name.equals(user.name) : user.name != null) {
      return false;
    }
    return birthDate != null ? birthDate.equals(user.birthDate) : user.birthDate == null;

  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = name != null ? name.hashCode() : 0;
    result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
    temp = Double.doubleToLongBits(weight);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    return result;
  }

  @Override
  public String toString() {
    return name + " " + birthDate + " " + weight;
  }
}
