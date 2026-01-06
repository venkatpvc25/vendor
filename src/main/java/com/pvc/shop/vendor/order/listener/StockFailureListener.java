package com.pvc.shop.vendor.order.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pvc.shop.comman.event.StockReductionFailedEvent;
import com.pvc.shop.vendor.order.domain.Order;
import com.pvc.shop.vendor.order.repo.OrderRepository;

import jakarta.transaction.Transactional;

@Component
public class StockFailureListener {

    private final OrderRepository repo;

    public StockFailureListener(OrderRepository repo) {
        this.repo = repo;
    }

    @Transactional
    @EventListener
    public void handle(StockReductionFailedEvent e) {
        Order order = repo.findById(e.orderId()).orElseThrow();
        order.markStockFailed();
    }
}
