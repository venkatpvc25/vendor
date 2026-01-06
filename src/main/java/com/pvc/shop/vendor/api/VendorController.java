package com.pvc.shop.vendor.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.vendor.dto.VendorDto;
import com.pvc.shop.vendor.service.VendorService;

@RestController
@RequestMapping("/vendors")
public class VendorController {
    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping
    public ApiResponse<List<VendorDto>> getVendors() {
        return ApiResponse.success(vendorService.getVendors());
    }
}
