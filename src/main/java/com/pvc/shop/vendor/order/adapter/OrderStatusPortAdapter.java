package com.pvc.shop.vendor.order.adapter;

import org.springframework.stereotype.Component;

import com.pvc.shop.vendor.order.repo.OrderRepository;

@Component
public class OrderStatusPortAdapter implements com.pvc.shop.payment.port.OrderStatusPort {
    private final OrderRepository repo;

    public OrderStatusPortAdapter(OrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public void markPaid(java.util.UUID orderId) {
        repo.findById(orderId).ifPresent(order -> {
            order.markPaid();
            repo.save(order);
        });
    }

    @Override
    public void markPaymentFailed(java.util.UUID orderId) {
        repo.findById(orderId).ifPresent(order -> {
            order.markPaymentFailed();
            repo.save(order);
        });
    }

}
