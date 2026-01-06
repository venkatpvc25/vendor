package com.pvc.shop.payment.listener;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.pvc.shop.comman.event.PaymentRequestedEvent;
import com.pvc.shop.payment.service.PaymentService;

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
