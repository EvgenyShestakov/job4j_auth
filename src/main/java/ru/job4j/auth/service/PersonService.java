package ru.job4j.auth.service;

import ru.job4j.auth.model.Person;

import java.util.Collection;
import java.util.Optional;

public interface PersonService {
Collection<Person> findAll();

Optional<Person> findById(int id);

Person save(Person person);

void delete(Person person);
}
