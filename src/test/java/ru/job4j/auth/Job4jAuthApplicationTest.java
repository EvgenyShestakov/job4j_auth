package ru.job4j.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.service.PersonService;
import java.util.*;


import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Job4jAuthApplication.class)
@AutoConfigureMockMvc
public class Job4jAuthApplicationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private PersonService personService;

    @Test
    public void getAllPerson() throws Exception {
        Collection<Person> records = new ArrayList<>(Arrays.
                asList(new Person(1, "John", "123"),
                        new Person(2, "Alex", "456")));
        Mockito.when(personService.findAll()).thenReturn(records);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/person/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].login", is("John")));
    }

    @Test
    public void getFoundPatientById() throws Exception {
        Mockito.when(personService.findById(2)).
                thenReturn(java.util.Optional.of(new Person(2, "Alex", "123")));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/person/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.login", is("Alex")));
    }

    @Test
    public void getNotFoundPatientById() throws Exception {
        Mockito.when(personService.findById(2)).
                thenReturn(java.util.Optional.empty());
        mockMvc.perform(MockMvcRequestBuilders
                .get("/person/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createPerson() throws Exception {
        Person person = new Person("John", "123");
        Mockito.when(personService.save(person)).thenReturn(person);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person));
        mockMvc.perform(mockRequest)
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.login", is("John")));
    }

    @Test
    public void updatePerson() throws Exception {
        Person person = new Person(1, "John", "123");
        Mockito.when(personService.save(person)).thenReturn(person);
        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/person/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(person));
        mockMvc.perform(mockRequest)
                .andExpect(status().isOk());
    }

    @Test
    public void deletePersonByIdSuccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/person/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}