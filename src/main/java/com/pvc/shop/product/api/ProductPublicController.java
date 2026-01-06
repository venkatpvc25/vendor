package com.pvc.shop.product.api;

import org.springframework.web.bind.annotation.*;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.product.dto.ProductPublicResponse;
import com.pvc.shop.product.service.ProductQueryService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vendors/{vendorId}/products")
public class ProductPublicController {

    private final ProductQueryService service;

    public ProductPublicController(ProductQueryService service) {
        this.service = service;
    }

    @GetMapping
    public ApiResponse<List<ProductPublicResponse>> list(@PathVariable UUID vendorId) {

        return ApiResponse.success(service.getProductsByVendor(vendorId));
    }
}
