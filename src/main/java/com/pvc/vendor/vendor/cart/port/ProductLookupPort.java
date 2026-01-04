package com.pvc.vendor.vendor.cart.port;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductLookupPort {

    ProductSnapshot getProduct(UUID productId);

    record ProductSnapshot(
            UUID productId,
            UUID vendorId,
            String name,
            BigDecimal price,
            int availableStock) {
    }
}
