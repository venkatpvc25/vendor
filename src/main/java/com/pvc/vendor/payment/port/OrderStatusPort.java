package com.pvc.vendor.payment.port;

import java.util.UUID;

public interface OrderStatusPort {
    void markPaid(UUID orderId);

    void markPaymentFailed(UUID orderId);
}
