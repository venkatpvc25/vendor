package com.pvc.shop.payment.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import com.pvc.shop.comman.constants.OrderStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "payment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Payment {

    @Id
    @GeneratedValue
    @Column(name = "pk_payment_id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "fk_order_id", nullable = false)
    private UUID orderId;

    @Column(name = "razorpay_order_id", nullable = false, unique = true)
    private String razorpayOrderId;

    private BigDecimal amount;
    private String currency;
    private String status;

    @Column(name = "expires_at", nullable = false)
    @Builder.Default
    private Instant expiresAt = Instant.now().plusMillis(60000);

    @Column(name = "created_at", updatable = false)
    @Builder.Default
    private Instant createdAt = Instant.now();

    public void markPaid() {
        this.status = "PAID";
    }

    public void markFailed() {
        this.status = "FAILED";
    }

    public void markPaymentFailed() {
        this.status = OrderStatus.PAYMENT_FAILED.name();
    }
}
