package com.pvc.shop.payment.port;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderPaymentPort {

    OrderPaymentSnapshot getOrder(UUID orderId);

    record OrderPaymentSnapshot(
            UUID orderId,
            BigDecimal amount) {
    }
}
