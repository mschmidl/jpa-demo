package com.mschmidl.jpademo.repository;

import com.mschmidl.jpademo.model.Person;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@DataJpaTest
public class PersonRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void whenFindAll_thenReturnsMockData() {
        Person matthias = new Person();
        matthias.setName("Matthias Schmidl");
        matthias.setRole("dev");
        manager.persist(matthias);
        manager.flush();

        List<Person> all = personRepository.findAll();

        Assert.assertNotNull(all);
        Assert.assertEquals(1, all.size());
        Person p = all.get(0);
        Assert.assertEquals("Matthias Schmidl", p.getName());

    }
}
