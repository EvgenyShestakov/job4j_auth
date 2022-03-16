package ru.job4j.auth.service;

import ru.job4j.auth.model.Employee;
import ru.job4j.auth.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class EmployeeServiceImpl implements ServiceEmployee {
    private final EmployeeRepository empRep;

    public EmployeeServiceImpl(EmployeeRepository empRep) {
        this.empRep = empRep;
    }

    @Override
    public Collection<Employee> findAll() {
        List<Employee> res = new ArrayList<>();
        empRep.findAll().forEach(res::add);
        return res;
    }

    @Override
    public Optional<Employee> findById(int id) {
        return empRep.findById(id);
    }

    @Override
    public Employee save(Employee employee) {
        return empRep.save(employee);
    }

    @Override
    public void delete(Employee employee) {
    empRep.delete(employee);
    }
}
