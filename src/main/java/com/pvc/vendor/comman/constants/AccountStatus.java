package com.pvc.vendor.comman.constants;

public enum AccountStatus {
    PENDING_APPROVAL("PENDING_APPROVAL"),
    ACTIVE("ACTIVE"),
    SUSPENDED("SUSPENDED");

    private final String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static AccountStatus fromString(String status) {
        for (AccountStatus accountStatus : AccountStatus.values()) {
            if (accountStatus.status.equalsIgnoreCase(status)) {
                return accountStatus;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + status);
    }
}
