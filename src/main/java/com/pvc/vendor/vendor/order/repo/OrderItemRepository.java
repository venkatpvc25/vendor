package com.pvc.vendor.vendor.order.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.vendor.vendor.order.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> {
    java.util.List<OrderItem> findByOrderId(UUID orderId);
}
