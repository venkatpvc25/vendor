package com.pvc.shop.vendor.order.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pvc.shop.vendor.domain.Vendor;
import com.pvc.shop.vendor.order.domain.Order;
import com.pvc.shop.vendor.order.dto.VendorOrder;
import com.pvc.shop.vendor.order.dto.VendorOrderList;
import com.pvc.shop.vendor.order.repo.OrderRepository;
import com.pvc.shop.vendor.repo.VendorRepository;

@Service
public class VendorOrderQueryService {
    private final OrderRepository orderRepository;
    private final VendorRepository vendorRepository;

    public VendorOrderQueryService(OrderRepository orderRepository, VendorRepository vendorRepository) {
        this.orderRepository = orderRepository;
        this.vendorRepository = vendorRepository;
    }

    public VendorOrderList getVendorOrders() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<Vendor> vendorOptional = vendorRepository
                .findByAccountId(UUID.fromString(auth.getPrincipal().toString()));
        if (vendorOptional.isEmpty()) {
            throw new RuntimeException();
        }
        Vendor vendor = vendorOptional.get();
        List<VendorOrder> vendorOrder = orderRepository.findByVendorId(vendor.getId()).stream()
                .map(Order::toVendorOrder)
                .toList();
        return new VendorOrderList(vendorOrder);
    }
}
