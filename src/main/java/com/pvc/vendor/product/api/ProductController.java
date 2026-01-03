package com.pvc.vendor.product.api;

import com.pvc.vendor.comman.api.ApiResponse;
import com.pvc.vendor.product.dto.ProductRequest;
import com.pvc.vendor.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/vendor/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<?> create(
            Authentication auth,
            @Valid @RequestBody ProductRequest req) {
        UUID accountId = (UUID) auth.getPrincipal();
        return ApiResponse.success(service.create(accountId, req));
    }

    @GetMapping
    public ApiResponse<?> list(Authentication auth) {
        UUID accountId = (UUID) auth.getPrincipal();
        return ApiResponse.success(service.list(accountId));
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(
            Authentication auth,
            @PathVariable UUID id,
            @Valid @RequestBody ProductRequest req) {
        UUID accountId = (UUID) auth.getPrincipal();
        return ApiResponse.success(service.update(accountId, id, req));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(
            Authentication auth,
            @PathVariable UUID id) {
        UUID accountId = (UUID) auth.getPrincipal();
        service.delete(accountId, id);
        return ApiResponse.ok("Product deleted");
    }
}
