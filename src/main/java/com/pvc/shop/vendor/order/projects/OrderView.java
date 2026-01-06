package com.pvc.shop.vendor.order.projects;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public interface OrderView {

    UUID getId();

    String getStatus();

    BigDecimal getTotalAmount();

    Instant getCreatedAt();
}
