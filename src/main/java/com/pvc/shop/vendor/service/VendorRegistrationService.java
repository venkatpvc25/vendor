package com.pvc.shop.vendor.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pvc.shop.auth.domain.Account;
import com.pvc.shop.auth.repo.AccountRepository;
import com.pvc.shop.comman.constants.AccountRole;
import com.pvc.shop.comman.constants.VendorStatus;
import com.pvc.shop.vendor.domain.Vendor;
import com.pvc.shop.vendor.dto.VendorRegisterRequest;
import com.pvc.shop.vendor.repo.VendorRepository;

@Service
public class VendorRegistrationService {

    private final AccountRepository accountRepo;
    private final VendorRepository vendorRepo;
    private final PasswordEncoder passwordEncoder;

    public VendorRegistrationService(
            AccountRepository accountRepo,
            VendorRepository vendorRepo,
            PasswordEncoder passwordEncoder) {
        this.accountRepo = accountRepo;
        this.vendorRepo = vendorRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void registerVendor(VendorRegisterRequest req) {

        Account account = Account.builder()
                .email(req.email())
                .passwordHash(passwordEncoder.encode(req.password()))
                .role(AccountRole.VENDOR)
                .build();

        account = accountRepo.save(account);

        Vendor vendor = Vendor.builder()
                .account(account)
                .name(req.name())
                .category(req.category())
                .address(req.address())
                .status(VendorStatus.PENDING_APPROVAL)
                .build();

        vendorRepo.save(vendor);
    }

}
