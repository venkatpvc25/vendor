package com.pvc.shop.product.service;

import org.springframework.stereotype.Service;

import com.pvc.shop.product.dto.ProductPublicResponse;
import com.pvc.shop.product.repo.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProductQueryService {

    private final ProductRepository repository;

    public ProductQueryService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<ProductPublicResponse> getProductsByVendor(UUID vendorId) {

        return repository
                .findByVendorId(vendorId)
                .stream()
                .map(p -> new ProductPublicResponse(
                        p.getId(),
                        p.getName(),
                        p.getPrice(),
                        p.getStock() > 0))
                .toList();
    }
}
