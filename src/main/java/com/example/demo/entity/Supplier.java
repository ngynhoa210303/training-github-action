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
@Table(name = "SUPPLIERS")
public class Supplier {
    @Id
    @Size(max = 36)
    @ColumnDefault("SYS_GUID()")
    @Column(name = "SUPPLIER_ID", nullable = false, length = 36)
    private String supplierId;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "SUPPLIER_NAME", nullable = false)
    private String supplierName;

    @Size(max = 500)
    @Nationalized
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "CREATED_DATE")
    private LocalDate createdDate;

    @Column(name = "STATUS")
    private Short status;

}