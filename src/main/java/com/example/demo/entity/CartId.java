package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CartId implements java.io.Serializable {
    private static final long serialVersionUID = -5834176227291032759L;
    @Size(max = 36)
    @NotNull
    @Column(name = "CUSTOMER_ID", nullable = false, length = 36)
    private String customerId;

    @Size(max = 36)
    @NotNull
    @Column(name = "PRODUCT_ID", nullable = false, length = 36)
    private String productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CartId entity = (CartId) o;
        return Objects.equals(this.productId, entity.productId) &&
                Objects.equals(this.customerId, entity.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, customerId);
    }

}