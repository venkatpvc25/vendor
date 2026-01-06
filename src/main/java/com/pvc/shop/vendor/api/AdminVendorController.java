package com.pvc.shop.vendor.api;

import org.springframework.web.bind.annotation.*;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.vendor.service.AdminVendorService;

import java.util.UUID;

@RestController
@RequestMapping("/admin/vendors")
public class AdminVendorController {

    private final AdminVendorService service;

    public AdminVendorController(AdminVendorService service) {
        this.service = service;
    }

    @PatchMapping("/{vendorId}/approve")
    public ApiResponse<?> approve(@PathVariable UUID vendorId) {
        service.approveVendor(vendorId);
        return ApiResponse.ok("Vendor approved and activated");
    }
}
