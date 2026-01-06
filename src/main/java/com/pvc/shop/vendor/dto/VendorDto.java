package com.pvc.shop.vendor.dto;

import java.util.UUID;

public record VendorDto(UUID id, String name, String category, String address, String status) {

}
