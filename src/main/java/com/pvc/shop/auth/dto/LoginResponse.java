package com.pvc.shop.auth.dto;

public record LoginResponse(
                String accessToken,
                String role) {
}
