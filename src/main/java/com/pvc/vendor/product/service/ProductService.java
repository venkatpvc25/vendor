package com.pvc.vendor.product.service;

import com.pvc.vendor.product.domain.Product;
import com.pvc.vendor.product.dto.ProductRequest;
import com.pvc.vendor.product.dto.ProductResponse;
import com.pvc.vendor.product.port.VendorAccessPort;
import com.pvc.vendor.product.repo.ProductRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repo;
    private final VendorAccessPort vendorService;

    public ProductService(ProductRepository repo, VendorAccessPort vendorService) {
        this.repo = repo;
        this.vendorService = vendorService;
    }

    public ProductResponse create(UUID accountId, ProductRequest req) {

        UUID vendorId = vendorService.getActiveVendorIdByAccount(accountId);

        Product p = new Product();
        p.setVendorId(vendorId);
        p.setName(req.name());
        p.setDescription(req.description());
        p.setPrice(req.price());
        p.setStock(req.stock());
        p.setCreatedAt(Instant.now());

        return map(repo.save(p));
    }

    public List<ProductResponse> list(UUID accountId) {
        UUID vendorId = vendorService.getActiveVendorIdByAccount(accountId);
        return repo.findByVendorId(vendorId).stream()
                .map(this::map)
                .toList();
    }

    public ProductResponse update(UUID accountId, UUID productId, ProductRequest req) {
        UUID vendorId = vendorService.getActiveVendorIdByAccount(accountId);

        Product p = repo.findByIdAndVendorId(productId, vendorId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        p.setName(req.name());
        p.setDescription(req.description());
        p.setPrice(req.price());
        p.setStock(req.stock());
        p.setUpdatedAt(Instant.now());

        return map(repo.save(p));
    }

    public void delete(UUID accountId, UUID productId) {
        UUID vendorId = vendorService.getActiveVendorIdByAccount(accountId);

        Product p = repo.findByIdAndVendorId(productId, vendorId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        repo.delete(p);
    }

    private ProductResponse map(Product p) {
        return new ProductResponse(
                p.getId(),
                p.getName(),
                p.getDescription(),
                p.getPrice(),
                p.getStock(),
                p.isActive());
    }
}
