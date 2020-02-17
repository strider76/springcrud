package com.spring.springcrud.service.impl;

import com.spring.springcrud.dao.EmployeeDAO;
import com.spring.springcrud.dto.EmployeeDTO;
import com.spring.springcrud.exceptions.EmployeeNotfoundException;
import com.spring.springcrud.model.Employee;
import com.spring.springcrud.service.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }


    @Override
    public Employee create(Employee employeeData) {
        employeeData.setActive(true);
        return this.employeeDAO.save(employeeData);
    }

    @Override
    public Employee findById(Long idEmployee) throws EmployeeNotfoundException {
        return this.employeeDAO
                .findById(idEmployee)
                .orElseThrow(()->new EmployeeNotfoundException(String.format("Employee with id '%d'' not found", idEmployee )));
    }

    @Override
    public List<Employee> findAll(Pageable page) {
        int numPage = page.getPageNumber();
        int sizePage = page.getPageSize();
        return this.employeeDAO.findAll(PageRequest.of(numPage,sizePage)).stream().collect(Collectors.toList());
    }

    @Override
    public List<Employee> findAllActivesWithSalaryBetween(Double min, Double max) {
        return this.employeeDAO.findEmployeesByActiveTrueAndSalaryBetweenOrderByNameAscSurnameAsc(min, max);
    }

    @Override
    public Long CountEmployeeWithSalaryLess(Double minSalary) {
        return this.employeeDAO.totalEmployeeSalaryLess(minSalary);
    }

    @Override
    public void update(Long idEmployee, EmployeeDTO employeeData) throws EmployeeNotfoundException {
        Employee employeeSearch = this.findById(idEmployee);

        employeeSearch.setName(employeeData.getName());
        employeeSearch.setSurname(employeeData.getSurname());
        employeeSearch.setAge(employeeData.getAge());
        employeeSearch.setSalary(employeeData.getSalary());
        employeeSearch.setDateUpdate(new Date());

        this.employeeDAO.save(employeeSearch);

    }

    @Override
    public void delete(Long idEmployee) throws EmployeeNotfoundException {

        Employee employeeSearch = this.findById(idEmployee);
        this.employeeDAO.delete(employeeSearch);

    }
}
