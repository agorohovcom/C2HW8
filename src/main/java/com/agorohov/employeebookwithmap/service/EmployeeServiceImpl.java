package com.agorohov.employeebookspringwebapp.service;

import com.agorohov.employeebookspringwebapp.exception.EmployeeAlreadyAddedException;
import com.agorohov.employeebookspringwebapp.exception.EmployeeNotFoundException;
import com.agorohov.employeebookspringwebapp.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final List<Employee> employees;

    public EmployeeServiceImpl() {
        employees = new ArrayList<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException(employee + " уже существует, добавление невозможно");
        }
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException(employee + ": такого сотрудника нет, удаление невозможно");
        }
        employees.remove(employee);
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.contains(employee)) {
            throw new EmployeeNotFoundException(employee + ": такого сотрудника нет");
        }
        return employee;
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return Collections.unmodifiableList(employees);
    }
}