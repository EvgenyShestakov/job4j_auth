package ru.job4j.auth.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.auth.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    @Query(value = "select distinct e from Employee e join fetch e.accounts ")
    Iterable<Employee> findAll();
}
