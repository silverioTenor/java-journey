package com.javajourney.track01concurrency.wallet;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentSkipListSet;

public final class History {
    private final ConcurrentSkipListSet<String> history = new ConcurrentSkipListSet<>();

    public History() {}

    public ConcurrentSkipListSet<String> getHistory() {
        return history;
    }

    public void addTransaction(
            BigDecimal oldBalance,
            BigDecimal balance,
            BigDecimal amount,
            OperationType operation,
            Account account
    ) {
        Transaction transaction = new Transaction(oldBalance, balance, amount, operation, account);
        history.add(transaction.toString());
    }
}
