package com.spring.springcrud.mapper.impl;

import com.spring.springcrud.dto.EmployeeDTO;
import com.spring.springcrud.mapper.EmployeeMapper;
import com.spring.springcrud.model.Employee;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Autowired
    DozerBeanMapper mapper;

    @Override
    public Employee employeeDtoToDao(EmployeeDTO employeeConvert) {

        return mapper.map(employeeConvert, Employee.class);
    }

    @Override
    public EmployeeDTO employeeDaoToDto(Employee employeeConvert) {
        return mapper.map(employeeConvert, EmployeeDTO.class);
    }

    @Override
    public List<Employee> employeeListDtoToDao(List<EmployeeDTO> employeesConvert) {
        return employeesConvert.stream()
                .map(employeeDTO -> mapper.map(employeeDTO, Employee.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> employeeDaoListToDto(List<Employee> employeesConvert) {
        return employeesConvert.stream()
                .map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }
}
