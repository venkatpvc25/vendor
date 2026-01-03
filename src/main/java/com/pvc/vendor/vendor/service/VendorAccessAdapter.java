package com.pvc.vendor.vendor.service;

import com.pvc.vendor.product.port.VendorAccessPort;
import com.pvc.vendor.vendor.domain.Vendor;
import com.pvc.vendor.vendor.repo.VendorRepository;

import org.springframework.stereotype.Service;

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
