package com.pvc.vendor.product.repo;

import com.pvc.vendor.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByVendorId(UUID vendorId);

    Optional<Product> findByIdAndVendorId(UUID id, UUID vendorId);
}
