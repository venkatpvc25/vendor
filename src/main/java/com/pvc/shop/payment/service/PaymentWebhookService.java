package com.pvc.shop.payment.service;

import org.springframework.stereotype.Service;

import com.pvc.shop.comman.constants.OrderStatus;
import com.pvc.shop.payment.domain.Payment;
import com.pvc.shop.payment.dto.RazorpayWebhookPayload;
import com.pvc.shop.payment.port.OrderStatusPort;
import com.pvc.shop.payment.repo.PaymentRepository;

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
