package com.pvc.shop.comman.exception;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pvc.shop.comman.api.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResponse<?> handleIllegalArgument(IllegalArgumentException ex) {
        return ApiResponse.error(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleValidation(MethodArgumentNotValidException ex) {
        return ApiResponse.error(
                ex.getBindingResult().getFieldError().getDefaultMessage());
    }

    @ExceptionHandler(AccountException.class)
    public ApiResponse<?> handleAccount(AccountException ex) {
        return ApiResponse.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneric(Exception ex) {
        return ApiResponse.error("Internal server error");
    }
}
