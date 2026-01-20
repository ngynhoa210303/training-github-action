package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Setter
@Entity
@Table(name = "IMAGES")
public class Image {
    @Id
    @Size(max = 36)
    @ColumnDefault("SYS_GUID()")
    @Column(name = "IMAGE_ID", nullable = false, length = 36)
    private String imageId;

    @NotNull
    @Column(name = "\"DATA\"", nullable = false)
    private byte[] data;

    @Column(name = "STATUS")
    private Short status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;
}