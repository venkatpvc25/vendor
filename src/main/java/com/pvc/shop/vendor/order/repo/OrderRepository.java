package com.pvc.shop.vendor.order.repo;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.shop.vendor.order.domain.Order;
import com.pvc.shop.vendor.order.projects.OrderView;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    List<OrderView> findByAccountIdOrderByCreatedAtDesc(UUID accountId);

    List<OrderView> findByVendorId(UUID vendorId);

}
