package com.pvc.shop.vendor.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pvc.shop.auth.domain.Account;
import com.pvc.shop.auth.repo.AccountRepository;
import com.pvc.shop.comman.constants.AccountStatus;
import com.pvc.shop.comman.constants.VendorStatus;
import com.pvc.shop.vendor.domain.Vendor;
import com.pvc.shop.vendor.repo.VendorRepository;

import java.util.UUID;

@Service
public class AdminVendorService {

    private final VendorRepository vendorRepo;
    private final AccountRepository accountRepo;

    public AdminVendorService(
            VendorRepository vendorRepo,
            AccountRepository accountRepo) {
        this.vendorRepo = vendorRepo;
        this.accountRepo = accountRepo;
    }

    @Transactional
    public void approveVendor(UUID vendorId) {
        Vendor vendor = vendorRepo.findById(vendorId)
                .orElseThrow(() -> new RuntimeException("Vendor not found"));
        Account account = accountRepo.findById(vendor.getAccount().getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));
        vendor.setStatus(VendorStatus.ACTIVE);
        account.setStatus(AccountStatus.ACTIVE);
        vendorRepo.save(vendor);
        accountRepo.save(account);
    }
}
