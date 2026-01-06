package com.pvc.shop.comman.event;

import java.util.UUID;

public record PaymentRequestedEvent(UUID orderId) {
}
