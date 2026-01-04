package com.pvc.vendor.comman.constants;

public enum OrderStatus {
    CREATED("CREATED"),
    PLACED("PLACED"),
    PAYMENT_PENDING("PAYMENT_PENDING"),
    PAID("PAID"),
    PAYMENT_FAILED("PAYMENT_FAILED"),
    STOCK_FAILED("STOCK_FAILED"),
    CANCELLED("CANCELLED"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus os : OrderStatus.values()) {
            if (os.status.equalsIgnoreCase(status)) {
                return os;
            }
        }
        throw new IllegalArgumentException("No enum constant for status: " + status);
    }
}
