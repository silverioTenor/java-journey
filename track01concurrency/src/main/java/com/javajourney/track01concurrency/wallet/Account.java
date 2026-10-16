package com.javajourney.track01concurrency.wallet;

import java.util.concurrent.atomic.AtomicLong;

public class Account {

    private final AtomicLong balance = new AtomicLong(0L);

    public Long getBalance() {
        return balance.get();
    }

    public Long deposit(Long amount) {
        if (amount >= 0) {
            return balance.addAndGet(amount);
        }
        return balance.get();
    }

    public Long withdraw(Long amount) {
        if (amount <= 0) {
            return balance.get();
        }

        // Prevents the balance from being altered during the operation
        return balance.accumulateAndGet(amount, (currentAmount, amountToBeDeducted) -> {
            if (currentAmount >= amountToBeDeducted) {
                return currentAmount - amountToBeDeducted;
            }
            return currentAmount;
        });
    }
}
