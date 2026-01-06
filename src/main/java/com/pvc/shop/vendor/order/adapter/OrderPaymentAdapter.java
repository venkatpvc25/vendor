package com.pvc.shop.vendor.order.adapter;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.pvc.shop.payment.port.OrderPaymentPort;
import com.pvc.shop.vendor.order.domain.Order;
import com.pvc.shop.vendor.order.repo.OrderRepository;

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
