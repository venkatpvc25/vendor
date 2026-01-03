package com.pvc.vendor.vendor.repo;

import com.pvc.vendor.vendor.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    Optional<Vendor> findByAccountId(UUID accountId);

}
