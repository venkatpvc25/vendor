package com.pvc.shop.vendor.cart.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pvc.shop.comman.api.ApiResponse;
import com.pvc.shop.vendor.cart.dto.CartAddRequest;
import com.pvc.shop.vendor.cart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ApiResponse<Void> createCartItem(@RequestBody CartAddRequest cartAddRequest) {
        cartService.add(cartAddRequest.accountId(), cartAddRequest.productId(), cartAddRequest.quantity());
        return ApiResponse.ok("cart item added successfuly");
    }
}
