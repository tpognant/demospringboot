package com.tpognant.demospringboot.person.service;

import com.tpognant.demospringboot.person.model.Person;
import com.tpognant.demospringboot.person.model.Person.Gender;
import com.tpognant.demospringboot.person.repository.FakePersonDAO;
import com.tpognant.demospringboot.person.repository.PersonDAO;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.swing.text.html.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  private final PersonDAO personDAO;

  @Autowired
  public PersonService(FakePersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  public List<Person> fetchAllPerson(Optional<Gender> gender) {
    return personDAO.fetchAllPerson();
  }

  public Optional<Person> fetchPerson(UUID userId) {
    return personDAO.fetchPerson(userId);
  }

  public Integer updatePerson(Person person) {
    if (personDAO.fetchPerson(person.getUserId()).isPresent()) {
      personDAO.updatePerson(person);
      return 1;
    }
    return 0;
  }

  public Integer deletePerson(UUID userUid) {
    if (personDAO.fetchPerson(userUid).isPresent()) {
      personDAO.deletePerson(userUid);
      return 1;
    }
    return 0;
  }

  public void insertPerson(Person person) {
    personDAO.insertPerson(person);
  }
}
