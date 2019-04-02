package com.mschmidl.jpademo.controller;

import com.mschmidl.jpademo.model.Person;
import com.mschmidl.jpademo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("persons")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person findById(@PathVariable Long id) {
        return personService.findById(id);
    }

    @GetMapping(value = "", params = "role")
    public List<Person> personByRole(@RequestParam String role) {
        return personService.findByRole(role);
    }

    @PostMapping
    public Person create(@Valid @RequestBody Person person) {
        return personService.save(person);
    }

    @PutMapping
    public Person update(@Valid @RequestBody Person person) {
        return personService.save(person);
    }

    @DeleteMapping("/{id}")
    public void setInactiveById(@PathVariable Long id) {
        personService.setInactiveById(id);
    }


}
