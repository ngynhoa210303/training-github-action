package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "EMPLOYEE_ID", nullable = false, length = 36)
    private String employeeId;

    @Size(max = 100)
    @NotNull
    @Column(name = "USERNAME", nullable = false, length = 100)
    private String username;

    @Size(max = 255)
    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Size(max = 255)
    @Nationalized
    @Column(name = "FULLNAME")
    private String fullName;

    @Size(max = 255)
    @Column(name = "EMAIL")
    private String email;

    @Size(max = 20)
    @Column(name = "PHONE_NUMBER", length = 20)
    private String phoneNumber;

    @Size(max = 255)
    @Nationalized
    @Column(name = "ADDRESS")
    private String address;

    @Size(max = 50)
    @Column(name = "ROLE", length = 50)
    private String rule;

    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @Column(name = "STATUS")
    private Short status;

    @Column(name = "DELETED")
    private Integer deleted;

}