package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {

    private String productId;

    private String productName;

    private String description;

    private BigDecimal price;

    private Long quantity;

    private String categoryId;

    private String employeeId;

    private LocalDate createdDate;

    private Short status;

}