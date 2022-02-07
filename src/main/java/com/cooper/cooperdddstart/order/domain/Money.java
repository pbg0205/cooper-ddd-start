package com.cooper.cooperdddstart.order.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Money {

    private final int value;

    public Money multiply(int multiplier) {
        return new Money(value * multiplier);
    }

    public Money add(Money money) {
        return new Money(this.value + money.getValue());
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
