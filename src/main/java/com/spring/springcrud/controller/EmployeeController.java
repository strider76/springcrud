package com.spring.springcrud.controller;

import com.spring.springcrud.dto.EmployeeDTO;
import com.spring.springcrud.exceptions.EmployeeNotfoundException;
import com.spring.springcrud.model.Employee;
import com.spring.springcrud.service.EmployeeService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {


    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @GetMapping(value = "/all",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<Employee> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                 @RequestParam(defaultValue = "10", required = false) Integer size) {
        return  this.employeeService.findAll(PageRequest.of(page,size));
    }

    @GetMapping(value = "/{id}")
    public Employee getById(@PathVariable("id") Long idEmployee) throws EmployeeNotfoundException {
        return this.employeeService.findById(idEmployee);
    }

    @PostMapping(value = "/add")
    public Employee add(@RequestBody Employee employeeDTO) {
        return this.employeeService.create(employeeDTO);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeData) throws EmployeeNotfoundException {
        this.employeeService.update(id,employeeData);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) throws EmployeeNotfoundException {
        this.employeeService.delete(id);
    }

    @GetMapping(value = "/countminsalary/{salary}")
    public Long countEmployeesMinSalary(@PathVariable("salary") Double minSalary) {
        return this.employeeService.CountEmployeeWithSalaryLess(minSalary);
    }

    @GetMapping(value = "/withsalarybetween/{min}/{max}")
    public List<Employee> countEmployeesMinSalary(@PathVariable("min") Double minSalary,
                                        @PathVariable("max") Double maxSalary) {
        return this.employeeService.findAllActivesWithSalaryBetween(minSalary, maxSalary);
    }


}
