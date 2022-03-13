package ru.job4j.auth.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.PersonRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRep;

    public PersonServiceImpl(PersonRepository personRep) {
        this.personRep = personRep;
    }

    @Override
    public Collection<Person> findAll() {
        List<Person> res = new ArrayList<>();
        personRep.findAll().forEach(res::add);
        return res;
    }

    @Override
    public Optional<Person> findById(int id) {
        return personRep.findById(id);
    }

    @Override
    public Person save(Person person) {
    return personRep.save(person);
    }

    @Override
    public void delete(Person person) {
    personRep.delete(person);
    }
}
