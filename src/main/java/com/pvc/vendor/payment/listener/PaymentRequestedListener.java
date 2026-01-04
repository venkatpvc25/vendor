package com.pvc.vendor.payment.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.pvc.vendor.comman.event.PaymentRequestedEvent;
import com.pvc.vendor.payment.service.PaymentService;

@Component
public class PaymentRequestedListener {

    private final PaymentService service;

    public PaymentRequestedListener(PaymentService service) {
        this.service = service;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(PaymentRequestedEvent e) {
        service.initiatePayment(e.orderId());
    }
}
