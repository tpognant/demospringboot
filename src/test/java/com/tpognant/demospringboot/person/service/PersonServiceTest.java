package com.tpognant.demospringboot.person.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.tpognant.demospringboot.person.model.Person;
import com.tpognant.demospringboot.person.model.Person.Gender;
import com.tpognant.demospringboot.person.repository.FakePersonDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

class PersonServiceTest {

  private PersonService personService;

  @Mock
  private FakePersonDAO fakePersonDAO;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    personService = new PersonService(fakePersonDAO);
  }

  @Test
  void shouldFetchAllPerson() {
    Person thomas = new Person(UUID.randomUUID(), "Thomas", "Pognant", 33, Gender.MALE,
        "thomaspognant@gmail.com");

    when(fakePersonDAO.fetchAllPerson()).thenReturn(Collections.singletonList(thomas));

    List<Person> people = personService.fetchAllPerson(Optional.empty());

    verify(fakePersonDAO).fetchAllPerson();

    assertThat(people).hasSize(1);

    Person person = people.get(0);
    assertThatIsThisUser(person, "Thomas", "Pognant", 33, Gender.MALE, "thomaspognant@gmail.com");
  }

  @Test
  void shouldFetchAllPersonMale() {

    Person thomas = new Person(UUID.randomUUID(), "Thomas", "Pognant", 33, Gender.MALE,
        "thomaspognant@gmail.com");

    when(fakePersonDAO.fetchAllPerson()).thenReturn(Collections.singletonList(thomas));

    List<Person> people = personService.fetchAllPerson(Optional.of("male"));

    verify(fakePersonDAO).fetchAllPerson();

    assertThat(people).hasSize(1);

    Person person = people.get(0);
    assertThatIsThisUser(person, "Thomas", "Pognant", 33, Gender.MALE, "thomaspognant@gmail.com");
  }

  @Test
  void shouldFetchPerson() throws NotFoundException {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    doNothing().when(fakePersonDAO).insertPerson(any(UUID.class), any(Person.class));

    personService.insertPerson(ninon);

    verify(fakePersonDAO).insertPerson(uuid, ninon);
  }

  @Test
  void shouldFetchPersonNotFound() {
    assertThatThrownBy(() -> personService.fetchPerson(UUID.randomUUID().toString()))
        .isInstanceOf(NotFoundException.class);
  }

  @Test
  void shouldUpdatePerson() throws NotFoundException {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    when(fakePersonDAO.fetchPerson(any(UUID.class))).thenReturn(Optional.of(ninon));
    when(fakePersonDAO.updatePerson(any(Person.class))).thenReturn(1);

    Person ninonOlder = new Person(uuid, "Ninon", "Pognant Lussier", 15, Gender.FEMALE,
        "ninonpognant@gmail.com");

    personService.updatePerson(ninonOlder);

    verify(fakePersonDAO).fetchPerson(uuid);

    verify(fakePersonDAO).updatePerson(ninonOlder);
  }

  @Test
  void shouldDeletePerson() throws NotFoundException {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    when(fakePersonDAO.fetchPerson(any(UUID.class))).thenReturn(Optional.of(ninon));
    when(fakePersonDAO.deletePerson(any(UUID.class))).thenReturn(1);

    personService.deletePerson(uuid.toString());

    verify(fakePersonDAO).fetchPerson(uuid);
    verify(fakePersonDAO).deletePerson(uuid);
  }

  @Test
  void shouldDeletePersonNotFound() {
    assertThatThrownBy(() -> personService.deletePerson(UUID.randomUUID().toString()))
        .isInstanceOf(NotFoundException.class);
  }

  @Test
  void shouldInsertPerson() {
    UUID uuid = UUID.randomUUID();
    Person ninon = new Person(uuid, "Ninon", "Pognant", 1, Gender.FEMALE,
        "ninonpognant@gmail.com");

    doNothing().when(fakePersonDAO).insertPerson(any(UUID.class), any(Person.class));

    personService.insertPerson(ninon);

    verify(fakePersonDAO).insertPerson(uuid, ninon);
  }

  private void assertThatIsThisUser(Person person, String firstName, String lastName, int age,
      Gender female, String email) {
    assertThat(person).extracting("userId").isNotNull();
    assertThat(person).extracting("firstName").isEqualTo(firstName);
    assertThat(person).extracting("lastName").isEqualTo(lastName);
    assertThat(person).extracting("age").isEqualTo(age);
    assertThat(person).extracting("gender").isEqualTo(female);
    assertThat(person).extracting("email").isEqualTo(email);
  }
}