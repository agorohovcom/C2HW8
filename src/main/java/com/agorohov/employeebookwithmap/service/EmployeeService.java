package com.agorohov.employeebookspringwebapp.service;

import com.agorohov.employeebookspringwebapp.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Collection<Employee> findAllEmployees();
}