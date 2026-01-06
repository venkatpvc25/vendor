package com.pvc.shop.vendor.order.api;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.vendor.order.service.OrderService;

@RestController
@RequestMapping("/place")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ApiResponse<Void> placeOrder(@RequestAttribute("accountId") UUID accountId) {
        orderService.placeOrder(accountId);
        return ApiResponse.ok("order placed");
    }
}
