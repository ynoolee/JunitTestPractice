package com.example.test.test.learnjava.optional;

public class User {
  private Long id;
  private String name;
  private int age;

  public User(String name) {
    this.name = name;
    this.age = 0;
  }

  public User(String name, int age){
    this.name = name;
    this.age = age;
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
