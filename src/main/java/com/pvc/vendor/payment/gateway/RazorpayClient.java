package com.pvc.vendor.payment.gateway;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class RazorpayClient {

    public RazorpayOrder createOrder(BigDecimal amount) {
        // convert to paise
        int amt = amount.multiply(BigDecimal.valueOf(100)).intValue();

        // call Razorpay SDK / REST here
        return new RazorpayOrder("rp_order_123", amount);
    }

    public record RazorpayOrder(String id, BigDecimal amount) {
    }
}
