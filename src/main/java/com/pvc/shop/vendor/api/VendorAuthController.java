package com.pvc.shop.vendor.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.vendor.dto.VendorRegisterRequest;
import com.pvc.shop.vendor.service.VendorRegistrationService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth/vendor")
public class VendorAuthController {

    private final VendorRegistrationService service;

    public VendorAuthController(VendorRegistrationService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody VendorRegisterRequest req) {
        service.registerVendor(req);
        return ApiResponse.ok("Vendor registered. Pending approval.");
    }
}
