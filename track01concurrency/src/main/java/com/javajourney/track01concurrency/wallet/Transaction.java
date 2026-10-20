package com.javajourney.track01concurrency.wallet;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public record Transaction(
        Instant timestamp,
        String id,
        BigDecimal oldBalance,
        BigDecimal balance,
        BigDecimal amount,
        String operationType,
        Account account
) implements Comparable<Transaction> {

    public Transaction(BigDecimal oldBalance, BigDecimal balance, BigDecimal amount, OperationType operationType, Account account) {
        this(
                Instant.now(),
                generateId(),
                oldBalance,
                balance,
                amount,
                operationType.name(),
                account
        );
    }

    private static String generateId() {
        Random random = new Random();
        int number = random.nextInt(100, 201);

        return STR."TX-\{number}";
    }

    private static String formatTimestamp(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        return formatter.format(instant);
    }

    private static String formatValue(BigDecimal amount) {
        Locale locale = Locale.getDefault();
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);

        return numberFormat.format(amount);
    }

    @Override
    public String toString() {
        return """
        {
            timestamp: %s,
            accountId: %s,
            transactionId: %s,
            operationType: %s,
            amount: %s,
            oldBalance: %s,
            balance: %s
        }
        """.formatted(
                formatTimestamp(timestamp),
                account.getId(),
                id,
                operationType,
                formatValue(amount),
                formatValue(oldBalance),
                formatValue(balance)
        );
    }

    @Override
    public int compareTo(Transaction o) {
        int compare = this.timestamp.compareTo(o.timestamp);
        if (compare != 0) return compare;

        return this.id.compareTo(o.id);
    }
}
