package com.example.demo.models;

public record Money(
        int amount,
        String currency
) {
    // 도메인 로직
    public Money plus(Money other) {
        if (!currency.equals(other.currency)) {
            throw new IllegalArgumentException("통화가 다릅니다.");
        }
        return new Money(amount + other.amount, currency);
    }

    public Money multiply(int quantity) {
        return new Money(amount * quantity, currency);
    }
}
