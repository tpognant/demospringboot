package com.tpognant.demospringboot.person.controller;

import com.tpognant.demospringboot.person.model.Person;
import com.tpognant.demospringboot.person.model.Person.Gender;
import com.tpognant.demospringboot.person.service.PersonService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

  private final PersonService personService;

  @Autowired
  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @RequestMapping(
      method = RequestMethod.GET
  )
  public ResponseEntity<List<Person>> fetchAllPerson(@RequestParam Gender gender) {
    return ResponseEntity.ok(personService.fetchAllPerson(Optional.of(gender)));
  }

  @RequestMapping(
      method = RequestMethod.GET,
      path = "{userUid}"
  )
  public ResponseEntity<Person> fetchPerson(@PathVariable UUID userUid) {
    return personService.fetchPerson(userUid).map(ResponseEntity::ok)
        .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @RequestMapping(
      method = RequestMethod.PUT
  )
  public ResponseEntity updatePerson(@RequestBody Person person) {
    return personService.updatePerson(person) == 1 ? ResponseEntity.ok().build()
        : ResponseEntity.notFound().build();
  }

  @RequestMapping(
      method = RequestMethod.DELETE,
      path = "{userUid}"
  )
  public ResponseEntity deletePerson(@PathVariable UUID userUid) {
    return personService.deletePerson(userUid) == 1 ? ResponseEntity.ok().build()
        : ResponseEntity.notFound().build();
  }

  @RequestMapping(
      method = RequestMethod.POST
  )
  public ResponseEntity insertPerson(@RequestBody Person person) {
    personService.insertPerson(person);
    return ResponseEntity.ok().build();
  }
}
