package com.tpognant.demospringboot.person.repository;

import com.tpognant.demospringboot.person.model.Person;
import com.tpognant.demospringboot.person.model.Person.Gender;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class FakePersonDAO implements PersonDAO {

  Map<UUID, Person> persons;

  public FakePersonDAO() {
    this.persons = new HashMap<>();

    Person thomas = new Person(UUID.randomUUID(), "Thomas", "Pognant", 33, Gender.MALE, "thomaspognant@gmail.com");
    persons.put(thomas.getUserId(), thomas);
  }

  @Override
  public List<Person> fetchAllPerson() {
    return new ArrayList<>(persons.values());
  }

  @Override
  public Optional<Person> fetchPerson(UUID userId) {
    return Optional.of(persons.get(userId));
  }

  @Override
  public Integer updatePerson(Person person) {
    fetchPerson(person.getUserId());
    return 1;
  }

  @Override
  public Integer deletePerson(UUID userUid) {
    persons.remove(userUid);
    return 1;
  }

  @Override
  public void insertPerson(Person person) {
    persons.put(person.getUserId() == null ? UUID.randomUUID() : person.getUserId(), person);
  }
}
