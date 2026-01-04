package com.pvc.vendor.payment.repo;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pvc.vendor.payment.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
    java.util.Optional<Payment> findByRazorpayOrderId(String razorpayOrderId);

    @Query("""
                SELECT p
                FROM Payment p
                WHERE p.status = 'PENDING'
                  AND p.createdAt <= :cutoffTime
            """)
    List<Payment> findExpiredPendingPayments(@Param("cutoffTime") Instant cutoffTime);

}
