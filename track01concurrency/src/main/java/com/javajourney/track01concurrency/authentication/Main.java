package com.javajourney.track01concurrency.authentication;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Main {
    public static void main(String[] args) {
        ThreadFactory factory = Thread.ofPlatform()
                .name("auth-thread-", 1)
                .factory();

        try (ExecutorService executor = Executors.newFixedThreadPool(10, factory)) {
            UserRepository userRepo = new UserRepository();
            // Prefer ConcurrentHashMap over HashMap when you need to avoid race condition
            Map<String, Map<String, String>> cache = new ConcurrentHashMap<>();
            Map<String, String> user = new ConcurrentHashMap<>();

            String name = "John Doe";
            String email = "john.doe@test.com";
            String id = UUID.randomUUID().toString();

            user.put("name", name);
            user.put("email", email);

            userRepo.save(id, user);

            for (int i = 1; i <= 10; i++) {
                executor.submit(() -> {

                    // Code smell – multiple threads may execute this when there is no thread safety.
//                    if (!cache.containsKey(id)) {
//                        System.out.println(Thread.currentThread().getName());
//                        Map<String, String> users = userRepo.findById(id);
//
//                        cache.put(id, users);
//                    }

                    // Solution using ConcurrentHashMap (thread-safe)
                    cache.computeIfAbsent(id, key -> {
                        System.out.println(Thread.currentThread().getName());
                        return userRepo.findById(id);
                    });
                });
            }
        }
    }
}
