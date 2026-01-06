package com.pvc.shop.comman.event;

import java.util.UUID;

public record StockReductionFailedEvent(
                UUID orderId,
                String reason) {
}
