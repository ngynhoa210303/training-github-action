package com.example.demo.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter

public class EmployeeRequest {

    private String employeeId;

    private String username;

    private String password;

    private String fullName;

    private String email;

    private String phoneNumber;

    private String address;

    private String rule;

    private LocalDate createdDate;

    private Short status;

}