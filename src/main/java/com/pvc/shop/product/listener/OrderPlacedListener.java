package com.pvc.shop.product.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.pvc.shop.comman.event.OrderPlacedEvent;
import com.pvc.shop.comman.event.StockReductionFailedEvent;
import com.pvc.shop.product.service.InventoryService;

import org.springframework.transaction.event.TransactionPhase;

@Component
public class OrderPlacedListener {

    private final InventoryService inventoryService;
    private final ApplicationEventPublisher events;

    public OrderPlacedListener(InventoryService inventoryService, ApplicationEventPublisher events) {
        this.inventoryService = inventoryService;
        this.events = events;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(OrderPlacedEvent event) {
        try {
            inventoryService.reduceStockForOrder(event.orderId());
        } catch (Exception e) {
            events.publishEvent(
                    new StockReductionFailedEvent(event.orderId(), e.getMessage()));
        }

    }
}
