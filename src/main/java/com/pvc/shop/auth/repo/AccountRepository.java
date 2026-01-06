package com.pvc.shop.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.shop.auth.domain.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {
    Optional<Account> findByEmail(String email);
}
