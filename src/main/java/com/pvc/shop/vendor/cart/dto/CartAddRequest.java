package com.pvc.shop.vendor.cart.dto;

import java.util.UUID;

public record CartAddRequest(UUID accountId, UUID productId, Integer quantity) {

}
