package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Nationalized;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "CUSTOMERS")
public class Customer {
    @Id
    @Size(max = 36)
    @ColumnDefault("SYS_GUID()")
    @Column(name = "CUSTOMER_ID", nullable = false, length = 36)
    private String customerId;

    @Size(max = 100)
    @NotNull
    @Column(name = "CUSTOMER_NAME", nullable = false, length = 100)
    private String customerName;

    @Size(max = 255)
    @NotNull
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Size(max = 255)
    @Nationalized
    @Column(name = "FULL_NAME")
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

    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @Column(name = "STATUS")
    private Short status;

}