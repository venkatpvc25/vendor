package com.pvc.vendor.vendor.order.repo;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.vendor.vendor.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
