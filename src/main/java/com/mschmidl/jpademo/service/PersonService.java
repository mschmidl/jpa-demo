package com.mschmidl.jpademo.service;

import com.mschmidl.jpademo.model.Person;
import com.mschmidl.jpademo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(long id) {
        return personRepository.getOne(id);
    }

    public List<Person> findByRole(String role) {
        return personRepository.findByRole(role);
    }


    public Person save(Person person) {
        return personRepository.save(person);
    }

    @Transactional
    public void setInactiveById(Long id) {
        personRepository.setInactiveById(id);
    }
}
