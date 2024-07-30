package com.workintech.s19d4.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee", schema = "s19d4")
public class Employee {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private Double salary;
}
