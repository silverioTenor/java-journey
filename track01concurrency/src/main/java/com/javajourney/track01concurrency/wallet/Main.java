package com.javajourney.track01concurrency.wallet;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    static void main(String[] args) throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            String id1 = "A1";
            Account accountOne = new Account(id1, BigDecimal.ZERO);

            String id2 = "A2";
            Account accountTwo = new Account(id2, BigDecimal.ZERO);

            executor.submit(() -> {
                accountOne.deposit(BigDecimal.valueOf(30_000L));
                accountOne.transfer(accountTwo, BigDecimal.valueOf(10_000L));
                accountOne.deposit(BigDecimal.valueOf(20_000L));
                accountOne.withdraw(BigDecimal.valueOf(30_000L));
            });

            executor.submit(() -> {
                accountTwo.withdraw(BigDecimal.valueOf(20_000L));
                accountTwo.deposit(BigDecimal.valueOf(30_000L));
                accountTwo.transfer(accountOne, BigDecimal.valueOf(10_000L));
            });

            executor.shutdown();
            if (executor.awaitTermination(2, TimeUnit.SECONDS)) {

                ConcurrentSkipListSet<String> history = new ConcurrentSkipListSet<>();

                history.addAll(accountOne.extract());
                history.addAll(accountTwo.extract());

                history.forEach(System.out::println);
            }
        }
    }
}
