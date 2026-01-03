package com.pvc.vendor.vendor.api;

import com.pvc.vendor.comman.api.ApiResponse;
import com.pvc.vendor.vendor.service.AdminVendorService;
import org.springframework.web.bind.annotation.*;

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
