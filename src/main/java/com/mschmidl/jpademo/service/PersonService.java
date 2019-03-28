package com.mschmidl.jpademo.service;

import com.mschmidl.jpademo.model.Person;
import com.mschmidl.jpademo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> fetchPersons() {
        return personRepository.findAll();
    }

    public List<Person> findByRole(String role) {
        return personRepository.findByRole(role);
    }

    public Person addPerson(Person person) {
        return personRepository.save(person);
    }
}
