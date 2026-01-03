package com.pvc.vendor.comman.api;

import java.time.Instant;

public class ApiResponse<T> {

    private boolean success;
    private T data;
    private String message;
    private Instant timestamp;

    private ApiResponse(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public static ApiResponse<Void> ok(String message) {
        return new ApiResponse<>(true, null, message);
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null);
    }

    public static ApiResponse<Void> successMessage(String message) {
        return new ApiResponse<>(true, null, message);
    }

    public static ApiResponse<Void> error(String message) {
        return new ApiResponse<>(false, null, message);
    }

    // getters
    public boolean isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public Instant getTimestamp() {
        return timestamp;
    }
}
