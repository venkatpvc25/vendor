package com.pvc.vendor.product.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.pvc.vendor.comman.event.PaymentFailedEvent;
import com.pvc.vendor.product.service.InventoryService;

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
