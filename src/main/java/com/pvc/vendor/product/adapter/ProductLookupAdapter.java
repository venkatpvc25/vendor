package com.pvc.vendor.product.adapter;

import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;

import com.pvc.vendor.product.repo.ProductRepository;
import com.pvc.vendor.vendor.cart.port.ProductLookupPort;

@Component
public class ProductLookupAdapter implements ProductLookupPort {
    private final ProductRepository repo;

    public ProductLookupAdapter(ProductRepository repo) {
        this.repo = repo;
    }

    @Override
    public ProductSnapshot getProduct(java.util.UUID productId) {
        return repo.findById(productId)
                .map(product -> new ProductSnapshot(
                        product.getId(),
                        product.getVendorId(),
                        product.getName(),
                        product.getPrice(),
                        product.getStock()))
                .orElse(null);
    }

}
