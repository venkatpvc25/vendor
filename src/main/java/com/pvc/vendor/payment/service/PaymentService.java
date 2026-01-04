package com.pvc.vendor.payment.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.pvc.vendor.payment.domain.Payment;
import com.pvc.vendor.payment.gateway.RazorpayClient;
import com.pvc.vendor.payment.gateway.RazorpayClient.RazorpayOrder;
import com.pvc.vendor.payment.port.OrderPaymentPort;
import com.pvc.vendor.payment.repo.PaymentRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentService {

    private final OrderPaymentPort orderPort;
    private final PaymentRepository repo;
    private final RazorpayClient razorpay;

    public PaymentService(
            OrderPaymentPort orderPort,
            PaymentRepository repo,
            RazorpayClient razorpay) {
        this.orderPort = orderPort;
        this.repo = repo;
        this.razorpay = razorpay;
    }

    @Transactional
    public void initiatePayment(UUID orderId) {

        var order = orderPort.getOrder(orderId);

        RazorpayOrder rpOrder = razorpay.createOrder(order.amount());

        Payment payment = Payment.builder()
                .orderId(orderId)
                .razorpayOrderId(rpOrder.id())
                .amount(order.amount())
                // .currency(rpOrder.currency())
                .status("INITIATED")
                .build();

        repo.save(payment);
    }
}
