package com.pvc.vendor.vendor.service;

import com.pvc.vendor.auth.domain.Account;
import com.pvc.vendor.auth.repo.AccountRepository;
import com.pvc.vendor.comman.constants.AccountStatus;
import com.pvc.vendor.comman.constants.VendorStatus;
import com.pvc.vendor.vendor.domain.Vendor;
import com.pvc.vendor.vendor.repo.VendorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
