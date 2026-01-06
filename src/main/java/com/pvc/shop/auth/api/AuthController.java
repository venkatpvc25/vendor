package com.pvc.shop.auth.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.shop.auth.dto.LoginRequest;
import com.pvc.shop.auth.dto.LoginResponse;
import com.pvc.shop.auth.service.AuthService;
import com.pvc.shop.comman.api.ApiResponse;

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
