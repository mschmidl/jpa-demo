package com.mschmidl.jpademo.config;

import com.mschmidl.jpademo.service.PersonService;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class TestContextConfiguration {

    @Bean
    public PersonService personService() {
        return new PersonService();
    }
}
