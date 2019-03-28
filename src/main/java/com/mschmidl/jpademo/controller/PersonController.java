package com.mschmidl.jpademo.controller;

import com.mschmidl.jpademo.model.Person;
import com.mschmidl.jpademo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping("/persons")
    public List<Person> persons() {
        return personService.fetchPersons();
    }

    @GetMapping(value = "/persons", params = "role")
    public List<Person> personByRole(@RequestParam String role) {
        return personService.findByRole(role);
    }

    @PostMapping("persons")
    public Long addPersons(@RequestBody Person person) {
        Person savedPerson = personService.addPerson(person);
        return savedPerson.getId();
    }
}
