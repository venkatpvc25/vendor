package com.pvc.shop.product.port;

import java.util.UUID;

public interface VendorAccessPort {

    UUID getActiveVendorIdByAccount(UUID accountId);

}
