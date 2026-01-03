package com.pvc.vendor.product.port;

import java.util.UUID;

public interface VendorAccessPort {

    UUID getActiveVendorIdByAccount(UUID accountId);

}
