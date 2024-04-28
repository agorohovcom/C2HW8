package com.agorohov.employeebookwithmap.service;

import com.agorohov.employeebookwithmap.exception.EmployeeAlreadyAddedException;
import com.agorohov.employeebookwithmap.exception.EmployeeNotFoundException;
import com.agorohov.employeebookwithmap.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        employees = new HashMap<>();
    }

    private String getFullName(Employee employee) {
        return employee.getFirstName() + ' ' + employee.getLastName();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(getFullName(employee))) {
            throw new EmployeeAlreadyAddedException(employee + " уже существует, добавление невозможно");
        }
        employees.put(getFullName(employee), employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(getFullName(employee))) {
            throw new EmployeeNotFoundException(employee + ": такого сотрудника нет, удаление невозможно");
        }
        employees.remove(getFullName(employee));
        return employee;
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (!employees.containsKey(getFullName(employee))) {
            throw new EmployeeNotFoundException(employee + ": такого сотрудника нет");
        }
        return employee;
    }

    @Override
    public Collection<Employee> findAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}