package com.tpognant.demospringboot.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public class Person {

  private final UUID userId;
  private final String firstName;
  private final String lastName;
  private final Integer age;
  private final Gender gender;
  private final String email;

  public Person (
      @JsonProperty("userId") UUID userId,
      @JsonProperty("firstName") String firstName,
      @JsonProperty("lastName") String lastName,
      @JsonProperty("age") Integer age,
      @JsonProperty("gender") Gender gender,
      @JsonProperty("email") String email
  ){
    this.userId = userId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.gender = gender;
    this.email = email;
  }

  public UUID getUserId() {
    return userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public Integer getAge() {
    return age;
  }

  public Gender getGender() {
    return gender;
  }

  public String getEmail() {
    return email;
  }

  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  public enum Gender {
    MALE, FEMALE
  }
}
