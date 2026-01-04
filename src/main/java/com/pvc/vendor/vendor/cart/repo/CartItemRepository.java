package com.pvc.vendor.vendor.cart.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.vendor.vendor.cart.domain.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, UUID> {
    List<CartItem> findByAccountId(UUID accountId);

    void deleteByAccountId(UUID accountId);
}
