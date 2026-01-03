package com.pvc.vendor.auth.dto;

public record LoginResponse(
        String accessToken,
        String role) {
}
