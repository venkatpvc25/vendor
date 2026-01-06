package com.pvc.shop.product.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pvc.shop.comman.event.PaymentFailedEvent;
import com.pvc.shop.product.service.InventoryService;

import jakarta.transaction.Transactional;

@Component
public class PaymentFailedListener {
    private final InventoryService inventoryService;

    public PaymentFailedListener(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @EventListener
    public void onPaymentFailed(PaymentFailedEvent event) {
        inventoryService.releseStockForOrder(event.orderId());
    }
}
