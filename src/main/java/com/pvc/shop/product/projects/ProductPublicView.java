package com.pvc.shop.product.projects;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductPublicView {

    UUID getId();

    String getName();

    BigDecimal getPrice();

    Integer getStock();
}
