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
import java.time.LocalDateTime;
import java.util.UUID;
@Getter
@Setter
@Entity
@Table(name = "CATEGORIES")
public class Category {
    @Id
    @Size(max = 36)
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "CATEGORY_ID", nullable = false, length = 36)
    private String categoryId;

    @Size(max = 255)
    @NotNull
    @Nationalized
    @Column(name = "CATEGORY_NAME", nullable = false)
    private String categoryName;

    @Size(max = 500)
    @Nationalized
    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;

    @Column(name = "STATUS")
    private Short status;

    @Column(name = "DELETED")
    private Integer deleted;

}