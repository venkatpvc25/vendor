package com.pvc.shop.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.shop.product.domain.Product;
import com.pvc.shop.product.projects.ProductPublicView;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByVendorId(UUID vendorId);

    Optional<Product> findByIdAndVendorId(UUID id, UUID vendorId);
}
