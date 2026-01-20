package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @Size(max = 36)
    @ColumnDefault("SYS_GUID()")
    @Column(name = "ORDER_ID", nullable = false, length = 36)
    private String orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @NotNull
    @Column(name = "CREATED_DATE", nullable = false)
    private LocalDate createdDate;

    @NotNull
    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @Size(max = 50)
    @Column(name = "ORDERTYPE", length = 50)
    private String ordertype;

    @Size(max = 255)
    @Column(name = "IMAGE")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "STATUS")
    private Short status;

}