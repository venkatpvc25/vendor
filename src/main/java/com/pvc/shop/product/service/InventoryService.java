package com.pvc.shop.product.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pvc.shop.product.domain.Product;
import com.pvc.shop.product.port.OrderQueryPort;
import com.pvc.shop.product.repo.ProductRepository;

@Service
public class InventoryService {

    private final ProductRepository repo;
    private final OrderQueryPort orderPort;

    public InventoryService(ProductRepository repo, OrderQueryPort orderPort) {
        this.repo = repo;
        this.orderPort = orderPort;
    }

    @Transactional
    public void reduceStockForOrder(java.util.UUID orderId) {

        var items = orderPort.getOrderItems(orderId);

        for (var item : items) {
            Product product = repo.findById(item.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            if (product.getStock() < item.quantity()) {
                throw new RuntimeException("Stock inconsistency detected");
            }

            product.setStock(product.getStock() - item.quantity());
        }
    }

    @Transactional
    public void releseStockForOrder(java.util.UUID orderId) {

        var items = orderPort.getOrderItems(orderId);

        for (var item : items) {
            Product product = repo.findById(item.productId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            product.setStock(product.getStock() + item.quantity());
        }
    }
}
