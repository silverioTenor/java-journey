package com.javajourney.track01concurrency.messagebroker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    volatile static boolean running = true;

    static void main(String[] args) throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(3)) {
            Worker worker = new Worker();

            for (int i = 1; i <= 2; i++) {
                executor.submit(() -> {
                    try {
                        while (running) {
                            worker.consume();
                            Thread.sleep(400);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            }

            executor.submit(() -> {
                try {
                    while (running) {
                        worker.produce("Message broker: Hello World!");
                        Thread.sleep(150);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });

            shutdown();
        }
    }

    static void shutdown() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("[INFO] Shutting down message broker...");
        running = false;
    }
}
