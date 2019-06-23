package com.spring.springcrud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id","name","surname","age","salary"})
public class EmployeeDTO {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name_employee")
    private String name;
    @JsonProperty("surname_employee")
    private String surname;
    private Integer age;
    private double salary;
    @JsonIgnore
    private Boolean activo;


}
