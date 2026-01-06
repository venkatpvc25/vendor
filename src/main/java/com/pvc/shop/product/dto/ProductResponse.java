package com.pvc.shop.product.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
                UUID id,
                String name,
                String description,
                BigDecimal price,
                int stock,
                boolean active) {
}
