package com.pvc.shop.vendor.dto;

import jakarta.validation.constraints.NotBlank;

public record VendorRegisterRequest(
                @NotBlank String email,
                @NotBlank String password,
                @NotBlank String name,
                String category,
                String address) {
}
