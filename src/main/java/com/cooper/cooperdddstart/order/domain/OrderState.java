package com.cooper.cooperdddstart.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderState {
    PAYMENT_WAITING(true),
    PREPARING(true),
    SHIPPED(false),
    DELIVERING(false),
    DELIVERY_COMPLETED(false),
    CANCELED(false);

    private final boolean isShippingChangeable;

    public boolean isShipped() {
        return !((this == PAYMENT_WAITING) || (this == PREPARING));
    }
}
