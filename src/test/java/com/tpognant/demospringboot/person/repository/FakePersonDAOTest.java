package com.tpognant.demospringboot.person.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.tpognant.demospringboot.person.model.Person;
import com.tpognant.demospringboot.person.model.Person.Gender;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FakePersonDAOTest {

  private PersonDAO personDAO;

  @BeforeEach
  void setUp() {
    personDAO = new FakePersonDAO();
  }

  @Test
  void shouldFetchAllPerson() {
    List<Person> people = personDAO.fetchAllPerson();

    assertThat(people).hasSize(1);

    Person person = people.get(0);
    assertThat(person).extracting("userId").isNotNull();
    assertThat(person).extracting("firstName").isEqualTo("Thomas");
    assertThat(person).extracting("lastName").isEqualTo("Pognant");
    assertThat(person).extracting("age").isEqualTo(33);
    assertThat(person).extracting("gender").isEqualTo(Gender.MALE);
    assertThat(person).extracting("email").isEqualTo("thomaspognant@gmail.com");
  }

  @Test
  void shouldFetchPerson() {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    personDAO.insertPerson(uuid, ninon);

    Optional<Person> ninonFetched = personDAO.fetchPerson(uuid);

    assertThat(ninonFetched).isNotNull();

    assertThat(ninonFetched.get()).extracting("userId").isNotNull();
    assertThat(ninonFetched.get()).extracting("firstName").isEqualTo("Ninon");
    assertThat(ninonFetched.get()).extracting("lastName").isEqualTo("Pognant");
    assertThat(ninonFetched.get()).extracting("age").isEqualTo(1);
    assertThat(ninonFetched.get()).extracting("gender").isEqualTo(Gender.FEMALE);
    assertThat(ninonFetched.get()).extracting("email").isEqualTo("ninonpognant@gmail.com");
  }

  @Test
  void shouldUpdatePerson() {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    personDAO.insertPerson(uuid, ninon);

    Person ninonOlder = new Person(uuid, "Ninon", "Pognant Lussier", 15, Gender.FEMALE,
        "ninonpognantlussier@gmail.com");

    personDAO.updatePerson(ninonOlder);

    Optional<Person> ninonFetched = personDAO.fetchPerson(uuid);

    assertThat(ninonFetched).isNotNull();

    assertThat(ninonFetched.get()).extracting("userId").isNotNull();
    assertThat(ninonFetched.get()).extracting("firstName").isEqualTo("Ninon");
    assertThat(ninonFetched.get()).extracting("lastName").isEqualTo("Pognant Lussier");
    assertThat(ninonFetched.get()).extracting("age").isEqualTo(15);
    assertThat(ninonFetched.get()).extracting("gender").isEqualTo(Gender.FEMALE);
    assertThat(ninonFetched.get()).extracting("email").isEqualTo("ninonpognantlussier@gmail.com");
  }

  @Test
  void shouldDeletePerson() {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    personDAO.insertPerson(uuid, ninon);

    Optional<Person> ninonFetched = personDAO.fetchPerson(uuid);

    assertThat(ninonFetched).isNotNull();

    personDAO.deletePerson(uuid);

    Optional<Person> ninonFetched2 = personDAO.fetchPerson(uuid);

    assertThat(ninonFetched2).isNotPresent();
  }

  @Test
  void shouldInsertPerson() {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    personDAO.insertPerson(uuid, ninon);

    Optional<Person> ninonFetched = personDAO.fetchPerson(uuid);

    assertThat(ninonFetched).isNotNull();

    assertThat(ninonFetched.get()).extracting("userId").isNotNull();
    assertThat(ninonFetched.get()).extracting("firstName").isEqualTo("Ninon");
    assertThat(ninonFetched.get()).extracting("lastName").isEqualTo("Pognant");
    assertThat(ninonFetched.get()).extracting("age").isEqualTo(1);
    assertThat(ninonFetched.get()).extracting("gender").isEqualTo(Gender.FEMALE);
    assertThat(ninonFetched.get()).extracting("email").isEqualTo("ninonpognant@gmail.com");
  }
}