package com.tpognant.demospringboot.person.repository;

import com.tpognant.demospringboot.person.model.Person;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonDAO {

  List<Person> fetchAllPerson();
  Optional<Person> fetchPerson(UUID userId);
  Integer updatePerson(Person person);
  Integer deletePerson(UUID userUid);
  void insertPerson(UUID userUid, Person person);

}
