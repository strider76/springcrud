package com.spring.springcrud.controller;

import com.spring.springcrud.dto.EmployeeDTO;
import com.spring.springcrud.exceptions.EmployeeNotfoundException;
import com.spring.springcrud.mapper.EmployeeMapper;
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
    private final EmployeeMapper employeeMapper;

    public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
        this.employeeService = employeeService;
        this.employeeMapper = employeeMapper;
    }


    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EmployeeDTO> getAll(@RequestParam(defaultValue = "0", required = false) Integer page,
                                 @RequestParam(defaultValue = "10", required = false) Integer size) {
        return  employeeMapper.employeeDaoListToDto(this.employeeService.findAll(PageRequest.of(page,size)));
    }

    @GetMapping(value = "/{id}")
    public EmployeeDTO getById(@PathVariable("id") Long idEmployee) throws EmployeeNotfoundException {
        return employeeMapper.employeeDaoToDto(this.employeeService.findById(idEmployee));
    }

    @PostMapping
    public EmployeeDTO add(@RequestBody EmployeeDTO employeeDTO) {

        return employeeMapper.employeeDaoToDto(
                this.employeeService.create(this.employeeMapper.employeeDtoToDao(employeeDTO))
        );
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
    public List<EmployeeDTO> countEmployeesMinSalary(@PathVariable("min") Double minSalary,
                                        @PathVariable("max") Double maxSalary) {
        return employeeMapper.employeeDaoListToDto(this.employeeService.findAllActivesWithSalaryBetween(minSalary, maxSalary));
    }


}
