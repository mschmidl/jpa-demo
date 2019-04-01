package com.mschmidl.jpademo.service;

import com.mschmidl.jpademo.config.TestContextConfiguration;
import com.mschmidl.jpademo.model.Person;
import com.mschmidl.jpademo.repository.PersonRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@Import(TestContextConfiguration.class)
public class PersonServiceTest {

    @MockBean
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @Before
    public void before() {
        Person person = new Person();
        person.setName("Matthias Schmidl");
        ArrayList<Person> persons = new ArrayList<>();
        persons.add(person);
        Mockito.when(personRepository.findAll()).thenReturn(persons);
    }

    @Test
    public void whenFindAll_thenReturnsMockData() {
        List<Person> all = personService.findAll();

        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
        Person p = all.get(0);
        Assert.assertEquals("Matthias Schmidl", p.getName());
    }

}
