package ru.job4j.auth.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private int inn;

    private Date hireDate = new Date(System.currentTimeMillis());

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<Person> accounts = new ArrayList<>();

    public static Employee of(int id, String firstName, String lastName, int inn) {
        Employee employee = new Employee();
        employee.id = id;
        employee.firstName = firstName;
        employee.lastName = lastName;
        employee.inn = inn;
        return employee;
    }

    public void addPerson(Person person) {
    accounts.add(person);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public List<Person> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Person> accounts) {
        this.accounts = accounts;
    }
}
