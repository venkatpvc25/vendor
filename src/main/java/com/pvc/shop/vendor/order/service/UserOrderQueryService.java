package com.pvc.shop.vendor.order.service;

import org.springframework.stereotype.Service;

import com.pvc.shop.vendor.dto.UserOrderResponse;
import com.pvc.shop.vendor.order.repo.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserOrderQueryService {

    private final OrderRepository repository;

    public UserOrderQueryService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<UserOrderResponse> getMyOrders(UUID accountId) {
        return repository
                .findByAccountIdOrderByCreatedAtDesc(accountId)
                .stream()
                .map(o -> new UserOrderResponse(
                        o.getId(),
                        o.getStatus(),
                        o.getTotalAmount(),
                        o.getCreatedAt()))
                .toList();
    }
}
