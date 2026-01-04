package com.pvc.vendor.payment.schedule;

import java.time.Instant;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;

import com.pvc.vendor.comman.event.PaymentFailedEvent;
import com.pvc.vendor.payment.repo.PaymentRepository;

import jakarta.transaction.Transactional;

public class PaymentSchedule {

    private final PaymentRepository paymentRepo;
    private final ApplicationEventPublisher eventPublisher;

    public PaymentSchedule(PaymentRepository paymentRepo, ApplicationEventPublisher eventPublisher) {
        this.paymentRepo = paymentRepo;
        this.eventPublisher = eventPublisher;
    }

    @Scheduled(fixedDelay = 60000)
    @Transactional
    public void processExpiredPayments() {
        paymentRepo.findExpiredPendingPayments(Instant.now().minusMillis(60000)).forEach(payment -> {
            payment.markFailed();
            eventPublisher.publishEvent(new PaymentFailedEvent(payment.getOrderId()));
        });
    }
}
