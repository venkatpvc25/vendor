package com.pvc.vendor.vendor.order.adapter;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.pvc.vendor.payment.port.OrderPaymentPort;
import com.pvc.vendor.vendor.order.domain.Order;
import com.pvc.vendor.vendor.order.repo.OrderRepository;

@Component
public class OrderPaymentAdapter implements OrderPaymentPort {

    private final OrderRepository repo;

    public OrderPaymentAdapter(OrderRepository repo) {
        this.repo = repo;
    }

    @Override
    public OrderPaymentSnapshot getOrder(UUID orderId) {
        Order o = repo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return new OrderPaymentSnapshot(o.getId(), o.getTotalAmount());
    }
}
