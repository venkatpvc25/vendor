package com.pvc.shop.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductPublicResponse(
                UUID productId,
                String name,
                BigDecimal price,
                boolean inStock) {
}
