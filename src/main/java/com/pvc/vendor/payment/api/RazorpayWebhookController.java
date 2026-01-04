package com.pvc.vendor.payment.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.vendor.payment.dto.RazorpayWebhookPayload;
import com.pvc.vendor.payment.service.PaymentWebhookService;

@RestController
@RequestMapping("/webhooks/razorpay")
public class RazorpayWebhookController {

    private final PaymentWebhookService service;

    public RazorpayWebhookController(PaymentWebhookService service) {
        this.service = service;
    }

    @PostMapping
    public void handle(@RequestBody RazorpayWebhookPayload payload) {
        service.process(payload);
    }
}
