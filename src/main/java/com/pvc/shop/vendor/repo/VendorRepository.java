package com.pvc.shop.vendor.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pvc.shop.vendor.domain.Vendor;

import java.util.Optional;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    Optional<Vendor> findByAccountId(UUID accountId);

}
