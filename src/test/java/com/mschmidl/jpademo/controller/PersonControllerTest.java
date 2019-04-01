package com.mschmidl.jpademo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mschmidl.jpademo.config.TestContextConfiguration;
import com.mschmidl.jpademo.model.Person;
import com.mschmidl.jpademo.service.PersonService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PersonService personService;

    @Test
    public void whenFindAll_thenReturnsMockData() throws Exception {
        ArrayList<Person> persons = new ArrayList<>();
        Person matthias = new Person();
        matthias.setName("Matthias Schmidl");
        matthias.setRole("admin");
        matthias.setActive(true);
        persons.add(matthias);
        Mockito.when(personService.findAll()).thenReturn(persons);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.get("/persons").accept(MediaType.APPLICATION_JSON));
        MvcResult mvcResult = result.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String content = mvcResult.getResponse().getContentAsString();

        Assert.assertEquals(objectMapper.writeValueAsString(persons), content);
    }

    @Test
    public void whenCreatePerson_thenReturnsOk() throws Exception {
        Person matthias = new Person();
        matthias.setName("Matthias Schmidl");
        matthias.setRole("admin");
        matthias.setActive(true);

        mvc.perform(MockMvcRequestBuilders.post("/persons")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(matthias)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void whenCreatePerson_thenReturnsOkAnArgumentsMatch() throws Exception {
        Person matthias = new Person();
        matthias.setName("Matthias Schmidl");
        matthias.setRole("admin");
        matthias.setActive(true);

        mvc.perform(MockMvcRequestBuilders.post("/persons")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(matthias)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        ArgumentCaptor<Person> captor = ArgumentCaptor.forClass(Person.class);
        Mockito.verify(personService, times(1)).save(captor.capture());
        Assert.assertEquals("Matthias Schmidl", captor.getValue().getName());
        Assert.assertEquals("admin", captor.getValue().getRole());
    }
}
