package com.pvc.shop.auth.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pvc.shop.auth.domain.Account;
import com.pvc.shop.auth.dto.LoginRequest;
import com.pvc.shop.auth.dto.LoginResponse;
import com.pvc.shop.auth.repo.AccountRepository;
import com.pvc.shop.auth.security.JwtProvider;
import com.pvc.shop.comman.exception.AccountException;

@Service
public class AuthService {

    private final AccountRepository repo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwt;

    public AuthService(AccountRepository repo, PasswordEncoder encoder, JwtProvider jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public LoginResponse login(LoginRequest req) {

        Account acc = repo.findByEmail(req.email())
                .orElseThrow(() -> new AccountException("Invalid credentials"));

        if (!encoder.matches(req.password(), acc.getPasswordHash())) {
            throw new AccountException("Invalid credentials");
        }

        if (!"ACTIVE".equals(acc.getStatus().name())) {
            throw new AccountException("Account not active");
        }

        String token = jwt.generateToken(acc.getId(), acc.getRole().name());

        return new LoginResponse(token, acc.getRole().name());
    }
}
