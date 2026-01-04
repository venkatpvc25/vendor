package com.pvc.vendor.vendor.order.adapter;

import com.pvc.vendor.product.port.OrderQueryPort;
import com.pvc.vendor.product.port.OrderQueryPort.OrderItemSnapshot;
import com.pvc.vendor.vendor.order.repo.OrderItemRepository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderQueryAdapter implements OrderQueryPort {

    private final OrderItemRepository repo;

    public OrderQueryAdapter(OrderItemRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<OrderItemSnapshot> getOrderItems(UUID orderId) {
        return repo.findByOrderId(orderId)
                .stream()
                .map(i -> new OrderItemSnapshot(i.getProductId(), i.getQuantity()))
                .toList();
    }
}
