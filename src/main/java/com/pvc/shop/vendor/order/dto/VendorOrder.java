package com.pvc.shop.vendor.order.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record VendorOrder(UUID id, String status, BigDecimal totalAmount) {

}
