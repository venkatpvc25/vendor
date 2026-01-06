package com.pvc.shop.vendor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.pvc.shop.vendor.domain.Vendor;
import com.pvc.shop.vendor.dto.VendorDto;
import com.pvc.shop.vendor.repo.VendorRepository;

@Service
public class VendorService {
    private final VendorRepository vendorRepository;

    public VendorService(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    public List<VendorDto> getVendors() {
        return vendorRepository.findAll().stream().map(Vendor::toDto).toList();
    }
}
