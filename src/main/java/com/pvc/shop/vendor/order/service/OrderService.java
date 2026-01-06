package com.pvc.shop.vendor.order.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.pvc.shop.comman.event.OrderPlacedEvent;
import com.pvc.shop.comman.event.PaymentRequestedEvent;
import com.pvc.shop.product.port.VendorAccessPort;
import com.pvc.shop.vendor.cart.port.ProductLookupPort;
import com.pvc.shop.vendor.cart.repo.CartItemRepository;
import com.pvc.shop.vendor.order.domain.Order;
import com.pvc.shop.vendor.order.domain.OrderItem;
import com.pvc.shop.vendor.order.repo.OrderItemRepository;
import com.pvc.shop.vendor.order.repo.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final CartItemRepository cartRepo;
    private final ProductLookupPort productPort;
    private final VendorAccessPort vendorPort;
    private final OrderRepository orderRepo;
    private final OrderItemRepository itemRepo;
    private final ApplicationEventPublisher events;

    public OrderService(
            CartItemRepository cartRepo,
            ProductLookupPort productPort,
            VendorAccessPort vendorPort,
            OrderRepository orderRepo,
            OrderItemRepository itemRepo,
            ApplicationEventPublisher events) {
        this.cartRepo = cartRepo;
        this.productPort = productPort;
        this.vendorPort = vendorPort;
        this.orderRepo = orderRepo;
        this.itemRepo = itemRepo;
        this.events = events;
    }

    @Transactional
    public UUID placeOrder(UUID accountId) {

        var cartItems = cartRepo.findByAccountId(accountId);
        if (cartItems.isEmpty())
            throw new RuntimeException("Cart empty");

        // Resolve vendor from first product (single vendor order)
        var firstProduct = productPort.getProduct(cartItems.get(0).getProductId());
        UUID vendorId = firstProduct.vendorId();

        BigDecimal total = BigDecimal.ZERO;

        Order order = Order.builder()
                .accountId(accountId)
                .vendorId(vendorId)
                .status("PENDING")
                .totalAmount(BigDecimal.ZERO)
                .build();

        order = orderRepo.save(order);

        for (var item : cartItems) {
            var p = productPort.getProduct(item.getProductId());

            if (item.getQuantity() > p.availableStock()) {
                throw new RuntimeException("Stock changed");
            }

            total = total.add(p.price().multiply(BigDecimal.valueOf(item.getQuantity())));
            OrderItem orderItem = OrderItem.builder()
                    .orderId(order.getId())
                    .productId(p.productId())
                    .productName(p.name())
                    .price(p.price())
                    .quantity(item.getQuantity())
                    .build();
            itemRepo.save(orderItem);

        }

        order.setTotalAmount(total);
        order.setStatus("PLACED");
        orderRepo.save(order);
        cartRepo.deleteByAccountId(accountId);

        // 🔥 EVENT (important)
        events.publishEvent(new OrderPlacedEvent(order.getId()));
        events.publishEvent(new PaymentRequestedEvent(order.getId()));

        return order.getId();
    }
}
