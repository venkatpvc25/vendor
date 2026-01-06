package com.pvc.shop.vendor.order.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.vendor.order.dto.VendorOrderList;
import com.pvc.shop.vendor.order.service.VendorOrderQueryService;

@RestController
@RequestMapping("/vendor/order")
public class VendorOrderController {

    private final VendorOrderQueryService vendorOrderQueryService;

    public VendorOrderController(VendorOrderQueryService vendorOrderQueryService) {
        this.vendorOrderQueryService = vendorOrderQueryService;
    }

    @GetMapping
    public ApiResponse<VendorOrderList> getVendorOrders() {
        return ApiResponse.success(vendorOrderQueryService.getVendorOrders());
    }
}
