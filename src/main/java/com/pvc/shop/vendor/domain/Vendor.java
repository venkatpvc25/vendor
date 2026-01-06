package com.pvc.shop.vendor.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

import com.pvc.shop.auth.domain.Account;
import com.pvc.shop.comman.constants.VendorStatus;
import com.pvc.shop.vendor.dto.VendorDto;

@Entity
@Table(name = "vendor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Vendor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "pk_vendor_id")
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_account_id", referencedColumnName = "pk_account_id", nullable = false, unique = true)
    private Account account;

    private String name;
    private String category;
    private String address;

    @Enumerated(EnumType.STRING)
    private VendorStatus status;

    public static VendorDto toDto(Vendor vendor) {
        if (vendor == null)
            return null;
        String status = vendor.getStatus() == null ? null : vendor.getStatus().name();
        return new VendorDto(vendor.getId(), vendor.getName(), vendor.getCategory(), vendor.getAddress(), status);
    }
}
