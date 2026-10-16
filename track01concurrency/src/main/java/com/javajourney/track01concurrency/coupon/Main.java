package com.javajourney.track01concurrency.coupon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) {
        ThreadFactory threadFactory = Thread.ofPlatform()
                .name("thread-", 1)
                .factory();

        try (ExecutorService executor = Executors.newFixedThreadPool(20, threadFactory)) {
            for (int i = 0; i < 20; i++) {
                executor.submit(() -> {
                    Coupon coupon = new Coupon();
                    System.out.println(STR."\{Thread.currentThread().getName()} --> status \{coupon.rescue()}");
                });
            }
        }
    }
}
