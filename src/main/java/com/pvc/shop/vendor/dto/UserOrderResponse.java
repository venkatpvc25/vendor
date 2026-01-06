package com.pvc.shop.vendor.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record UserOrderResponse(
        UUID orderId,
        String status,
        BigDecimal totalAmount,
        Instant createdAt) {
}
