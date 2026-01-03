package com.pvc.vendor.comman.constants;

public enum VendorStatus {
    PENDING_APPROVAL("PENDING_APPROVAL"),
    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPENDED");

    private final String status;

    VendorStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static VendorStatus fromString(String status) {
        for (VendorStatus vendorStatus : VendorStatus.values()) {
            if (vendorStatus.status.equalsIgnoreCase(status)) {
                return vendorStatus;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + status);
    }
}
