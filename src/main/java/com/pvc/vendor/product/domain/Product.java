package com.pvc.vendor.product.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "pk_product_id")
    private UUID id;

    @Column(name = "fk_vendor_id", nullable = false)
    private UUID vendorId;

    private String name;
    private String description;
    private BigDecimal price;
    private int stock;

    @Column(name = "is_active")
    private boolean active = true;

    @Column(name = "created_at")
    private Instant createdAt;

    @Column(name = "updated_at")
    private Instant updatedAt;

}
