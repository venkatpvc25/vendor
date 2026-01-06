package com.pvc.shop.vendor.service;

import org.springframework.stereotype.Service;

import com.pvc.shop.product.port.VendorAccessPort;
import com.pvc.shop.vendor.domain.Vendor;
import com.pvc.shop.vendor.repo.VendorRepository;

import java.util.UUID;

@Service
public class VendorAccessAdapter implements VendorAccessPort {

    private final VendorRepository repo;

    public VendorAccessAdapter(VendorRepository repo) {
        this.repo = repo;
    }

    @Override
    public UUID getActiveVendorIdByAccount(UUID accountId) {

        Vendor vendor = repo.findByAccountId(accountId)
                .filter(v -> "ACTIVE".equals(v.getStatus().name()))
                .orElseThrow(() -> new RuntimeException("Vendor not active"));

        return vendor.getId();
    }
}
