package com.tpognant.demospringboot.person.service;

import com.tpognant.demospringboot.person.model.Person;
import com.tpognant.demospringboot.person.model.Person.Gender;
import com.tpognant.demospringboot.person.repository.FakePersonDAO;
import com.tpognant.demospringboot.person.repository.PersonDAO;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import javassist.NotFoundException;
import javax.swing.text.html.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Service
public class PersonService {

  private final PersonDAO personDAO;

  @Autowired
  public PersonService(FakePersonDAO personDAO) {
    this.personDAO = personDAO;
  }

  public List<Person> fetchAllPerson(Optional<String> gender) {

    if(gender.isEmpty()) {
      return personDAO.fetchAllPerson();
    }

    return personDAO.fetchAllPerson().stream()
        .filter(person -> person.getGender().name().equals(gender.get().toUpperCase()))
        .collect(Collectors.toList());
  }

  public Person fetchPerson(UUID userId) throws NotFoundException {
    return personDAO.fetchPerson(userId)
        .orElseThrow(() -> new NotFoundException("user " + userId + " not found"));
  }

  public void updatePerson(Person person) throws NotFoundException {
    personDAO.fetchPerson(person.getUserId())
        .orElseThrow(() -> new NotFoundException("User " + person.getUserId() + " not found"));

    personDAO.updatePerson(person);
  }

  public void deletePerson(UUID userUid) throws NotFoundException {
    Person person = personDAO.fetchPerson(userUid)
        .orElseThrow(() -> new NotFoundException("User " + userUid + " not found"));

    personDAO.deletePerson(person.getUserId());
  }

  public void insertPerson(Person person) {
    personDAO.insertPerson(person.getUserId() == null ? UUID.randomUUID() : person.getUserId(),
        person);
  }
}
