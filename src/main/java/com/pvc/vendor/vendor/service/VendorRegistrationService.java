package com.pvc.vendor.vendor.service;

import com.pvc.vendor.auth.domain.Account;
import com.pvc.vendor.auth.repo.AccountRepository;
import com.pvc.vendor.comman.constants.AccountRole;
import com.pvc.vendor.comman.constants.VendorStatus;
import com.pvc.vendor.vendor.domain.Vendor;
import com.pvc.vendor.vendor.dto.VendorRegisterRequest;
import com.pvc.vendor.vendor.repo.VendorRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
