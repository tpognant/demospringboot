package com.tpognant.demospringboot.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

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

  @NotNull
  public String getFirstName() {
    return firstName;
  }

  @NotNull
  public String getLastName() {
    return lastName;
  }

  @NotNull
  public Integer getAge() {
    return age;
  }

  @NotNull
  public Gender getGender() {
    return gender;
  }

  @NotNull
  @Email
  public String getEmail() {
    return email;
  }

  @NotNull
  public String getFullName() {
    return this.firstName + " " + this.lastName;
  }

  public enum Gender {
    MALE, FEMALE
  }
}
