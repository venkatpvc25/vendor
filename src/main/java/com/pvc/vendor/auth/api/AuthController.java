package com.pvc.vendor.auth.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.vendor.auth.dto.LoginRequest;
import com.pvc.vendor.auth.dto.LoginResponse;
import com.pvc.vendor.auth.service.AuthService;
import com.pvc.vendor.comman.api.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(
            @Valid @RequestBody LoginRequest req) {
        return ApiResponse.success(service.login(req));
    }
}
