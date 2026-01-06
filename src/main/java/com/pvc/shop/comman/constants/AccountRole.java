package com.pvc.shop.comman.constants;

public enum AccountRole {
    ADMIN("ADMIN"),
    VENDOR("VENDOR"),
    CUSTOMER("CUSTOMER");

    private final String role;

    AccountRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public static AccountRole fromString(String role) {
        for (AccountRole accountRole : AccountRole.values()) {
            if (accountRole.role.equalsIgnoreCase(role)) {
                return accountRole;
            }
        }
        throw new IllegalArgumentException("No enum constant for role: " + role);
    }
}
