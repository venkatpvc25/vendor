package com.pvc.vendor.comman.event;

import java.util.UUID;

public record PaymentRequestedEvent(UUID orderId) {
}
