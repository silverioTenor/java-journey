package com.javajourney.track01concurrency.wallet;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) {
        ThreadFactory factory = Thread.ofPlatform()
                .name("wallet-thread-", 1)
                .factory();

        try (ExecutorService executor = Executors.newFixedThreadPool(4, factory)) {
            Account account = new Account();
            account.deposit(1_000_000L);

            for (int i = 0; i < 4; i++) {
                executor.submit(() -> {
                    synchronized (account) { // Used to ensure that the exact result is displayed in the terminal
                        long balanceAfterWithdraw = account.withdraw(120_000L);
                        System.out.println(STR."\{Thread.currentThread().getName()} | withdraw - balance: \{balanceAfterWithdraw}");

                        long balanceAfterDeposit = account.deposit(87_000L);
                        System.out.println(STR."\{Thread.currentThread().getName()} | deposit - balance: \{balanceAfterDeposit}");
                    }
                });
            }
        }
    }
}
