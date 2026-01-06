package com.pvc.shop.vendor.api;

import org.springframework.web.bind.annotation.*;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.vendor.dto.UserOrderResponse;
import com.pvc.shop.vendor.order.service.UserOrderQueryService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@Slf4j
public class UserOrderController {

    private final UserOrderQueryService service;

    public UserOrderController(UserOrderQueryService service) {
        this.service = service;
    }

    @GetMapping("/me")
    public ApiResponse<UserOrderListResponse> myOrders(
            @RequestAttribute("accountId") UUID accountId) {
        log.info("✅ accountId from request = {}", accountId);
        return ApiResponse.success(new UserOrderListResponse(
                service.getMyOrders(accountId)));
    }
}

record UserOrderListResponse(List<UserOrderResponse> items) {
}
