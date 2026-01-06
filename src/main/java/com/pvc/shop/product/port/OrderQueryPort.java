package com.pvc.shop.product.port;

import java.util.List;
import java.util.UUID;

public interface OrderQueryPort {

    List<OrderItemSnapshot> getOrderItems(UUID orderId);

    record OrderItemSnapshot(
            UUID productId,
            int quantity) {
    }
}
