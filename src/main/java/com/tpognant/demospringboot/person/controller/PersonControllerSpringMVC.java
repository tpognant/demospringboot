package com.tpognant.demospringboot.person.controller;

import com.tpognant.demospringboot.person.model.Person;
import com.tpognant.demospringboot.person.model.Person.Gender;
import com.tpognant.demospringboot.person.service.PersonService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
public class PersonControllerSpringMVC {

  private final PersonService personService;

  @Autowired
  public PersonControllerSpringMVC(PersonService personService) {
    this.personService = personService;
  }

  @RequestMapping(
      method = RequestMethod.GET
  )
  public List<Person> fetchAllPerson(@RequestParam Optional<String> gender) {
    return personService.fetchAllPerson(gender);
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{userUid}"
  )
  public Person fetchPerson(@PathVariable String userUid) throws NotFoundException {
    return personService.fetchPerson(userUid);
  }

  @RequestMapping(
      method = RequestMethod.PUT
  )
  public void updatePerson(@RequestBody Person person) throws NotFoundException {
    personService.updatePerson(person);
  }

  @RequestMapping(
      method = RequestMethod.DELETE,
      path = "{userUid}"
  )
  public void deletePerson(@PathVariable String userUid) throws NotFoundException {
    personService.deletePerson(userUid);
  }

  @RequestMapping(
      method = RequestMethod.POST
  )
  public void insertPerson(@RequestBody Person person) {
    personService.insertPerson(person);
  }
}
