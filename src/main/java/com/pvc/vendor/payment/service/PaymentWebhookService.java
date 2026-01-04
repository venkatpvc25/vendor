package com.pvc.vendor.payment.service;

import org.springframework.stereotype.Service;

import com.pvc.vendor.comman.constants.OrderStatus;
import com.pvc.vendor.payment.domain.Payment;
import com.pvc.vendor.payment.dto.RazorpayWebhookPayload;
import com.pvc.vendor.payment.port.OrderStatusPort;
import com.pvc.vendor.payment.repo.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentWebhookService {

    private final PaymentRepository paymentRepo;
    private final OrderStatusPort orderPort;

    public PaymentWebhookService(
            PaymentRepository paymentRepo,
            OrderStatusPort orderPort) {
        this.paymentRepo = paymentRepo;
        this.orderPort = orderPort;
    }

    @Transactional
    public void process(RazorpayWebhookPayload payload) {

        Payment payment = paymentRepo
                .findByRazorpayOrderId(payload.orderId())
                .orElseThrow();
        try {
            payment.markPaid();
            orderPort.markPaid(payment.getOrderId());
        } catch (Exception e) {
            payment.markPaymentFailed();
            orderPort.markPaymentFailed(payment.getOrderId());
        }
    }
}
