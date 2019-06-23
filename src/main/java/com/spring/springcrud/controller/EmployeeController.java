package com.spring.springcrud.controller;

import com.spring.springcrud.dto.EmployeeDTO;
import com.spring.springcrud.exceptions.EmployeeNotfoundException;
import com.spring.springcrud.exceptions.ErrorDeletingEmployeeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private List<EmployeeDTO> employeeList;


    @PostConstruct
    private void initializeEmployeeList() {
        employeeList = new ArrayList<EmployeeDTO>(){
            {
                add(new EmployeeDTO(1L,"Juan","Marquez", 23, 1200.0, true));
                add(new EmployeeDTO(2L,"Eloy","Perez", 17, 930.50,true));
                add(new EmployeeDTO(3L,"Loli","Sumariva", 43, 1800.0, true));
                add(new EmployeeDTO(4L,"Ana","Mateo", 18, 1100.0, false));
            }
        };
    }

    @GetMapping(value = "/all",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<EmployeeDTO> getAll(){
        return  employeeList;
    }

    @GetMapping(value = "/{id}")
    public EmployeeDTO getById(@PathVariable("id") Long idEmployee) throws Exception {
        return employeeList.stream()
                .filter(user -> user.getId().equals(idEmployee))
                .findFirst()
                .orElseThrow(()->new Exception("Id no valido"));
    }

    @PostMapping(value = "/add")
    public EmployeeDTO add(@RequestBody EmployeeDTO employeeDTO) {
        return addEmployee(employeeDTO);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeData) throws EmployeeNotfoundException {
        EmployeeDTO employeeSearch = findById(id);
        updateElement(employeeSearch, employeeData);

    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) throws EmployeeNotfoundException, ErrorDeletingEmployeeException {
        EmployeeDTO employeeSearch = findById(id);
        deleteEmployee(employeeSearch);
    }


    private EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
        employeeDTO.setId(new Long(employeeList.size()+1));
        employeeDTO.setActivo(true);
        employeeList.add(employeeDTO);
        return employeeDTO;
    }

    private EmployeeDTO findById(Long id) throws EmployeeNotfoundException {
        return employeeList.stream()
                .filter(employeeDTO -> employeeDTO.getId().equals(id))
                .findFirst()
                .orElseThrow(()-> new EmployeeNotfoundException(String.format("Id %d Inexistente", id)));
    }

    private void updateElement(EmployeeDTO employeeSearch, EmployeeDTO employeeDTO) {
        employeeList = employeeList
                        .stream()
                        .map(currentEmployee -> {
                            if (currentEmployee.equals(employeeSearch))
                               return updateField(currentEmployee,employeeDTO);
                            else
                                return currentEmployee;
                            })
                        .collect(Collectors.toList());

    }

    private EmployeeDTO updateField(EmployeeDTO employeeSearch, EmployeeDTO employeeData) {

        employeeData.setId(employeeSearch.getId());

        return employeeData;
    }

    private void deleteEmployee(EmployeeDTO employee) throws ErrorDeletingEmployeeException {
        if (!employeeList.remove(employee))
            throw new ErrorDeletingEmployeeException("Error al borrar Employee");
    }


}
