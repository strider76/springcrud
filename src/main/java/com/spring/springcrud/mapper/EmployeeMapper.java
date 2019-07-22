package com.spring.springcrud.mapper;

import com.spring.springcrud.dto.EmployeeDTO;
import com.spring.springcrud.model.Employee;

import java.util.List;

public interface EmployeeMapper {

    Employee employeeDtoToDao (EmployeeDTO employeeConvert);
    EmployeeDTO employeeDaoToDto (Employee employeeConvert);
    List<Employee> employeeListDtoToDao(List<EmployeeDTO> employeesConvert);
    List<EmployeeDTO> employeeDaoListToDto(List<Employee> employeesConvert);

}
