package ru.job4j.auth.service;

import ru.job4j.auth.model.Employee;

import java.util.Collection;
import java.util.Optional;

public interface ServiceEmployee {
    Collection<Employee> findAll();

    Optional<Employee> findById(int id);

    Employee save(Employee employee);

    void delete(Employee employee);
}

