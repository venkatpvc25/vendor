package com.pvc.vendor.vendor.order.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.pvc.vendor.comman.constants.OrderStatus;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "pk_order_id", nullable = false)
    private UUID id;

    @Column(name = "account_id", nullable = false)
    private UUID accountId;

    @Column(name = "vendor_id", nullable = false)
    private UUID vendorId;

    @Column(nullable = false)
    private String status;

    @Column(name = "total_amount", nullable = false)
    private BigDecimal totalAmount;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();

    public void markPlaced(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
        this.status = OrderStatus.PLACED.name();
    }

    public void markPaid() {
        if (isPaid()) {
            return;
        }
        this.status = OrderStatus.PAID.name();
    }

    public void markStockFailed() {
        this.status = OrderStatus.STOCK_FAILED.name();
    }

    public void markPaymentFailed() {
        this.status = OrderStatus.PAYMENT_FAILED.name();
    }

    public boolean isPaid() {
        return OrderStatus.PAID.name().equals(this.status);
    }
}
