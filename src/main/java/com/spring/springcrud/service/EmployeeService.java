package com.spring.springcrud.service;


import com.spring.springcrud.dto.EmployeeDTO;
import com.spring.springcrud.exceptions.EmployeeNotfoundException;
import com.spring.springcrud.model.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    Employee create (Employee employeeData);

    Employee findById (Long idEmployee) throws EmployeeNotfoundException;

    List<Employee> findAll(Pageable page);

    List<Employee> findAllActivesWithSalaryBetween(Double min, Double max);

    Long CountEmployeeWithSalaryLess(Double minSalary);

    void update(Long idEmployee, EmployeeDTO employeeData) throws EmployeeNotfoundException;

    void delete(Long idEmployee) throws EmployeeNotfoundException;



}
