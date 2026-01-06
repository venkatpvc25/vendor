package com.pvc.shop.vendor.cart.service;

import java.util.List;
import java.util.UUID;

import com.pvc.shop.vendor.cart.domain.CartItem;
import com.pvc.shop.vendor.cart.port.ProductLookupPort;
import com.pvc.shop.vendor.cart.repo.CartItemRepository;

@org.springframework.stereotype.Service
public class CartService {

    private final CartItemRepository repo;
    private final ProductLookupPort productPort;

    public CartService(CartItemRepository repo, ProductLookupPort productPort) {
        this.repo = repo;
        this.productPort = productPort;
    }

    public void add(UUID accountId, UUID productId, int qty) {

        var product = productPort.getProduct(productId);

        if (qty > product.availableStock()) {
            throw new RuntimeException("Insufficient stock");
        }

        CartItem item = CartItem.builder()
                .accountId(accountId)
                .productId(productId)
                .quantity(qty)
                .build();

        repo.save(item);
    }

    public List<CartItem> getCart(UUID accountId) {
        return repo.findByAccountId(accountId);
    }
}
