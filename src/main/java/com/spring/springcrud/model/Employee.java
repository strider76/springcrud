package com.spring.springcrud.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.util.Date;

@Entity
@Table(name = "employee")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Min(16)
    private Integer age;
    @DecimalMin("900.00")
    private Double salary;
    private Boolean active;
    @Temporal(TemporalType.DATE)
    private Date dateCreate = new Date();
    @Temporal(TemporalType.DATE)
    private Date dateUpdate;


}
