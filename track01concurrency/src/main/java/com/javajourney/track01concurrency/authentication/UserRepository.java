package com.javajourney.track01concurrency.authentication;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserRepository {
    private final Map<String, Map<String, String>> users = new ConcurrentHashMap<>();

    public void save(String id, Map<String, String> user) {
        users.put(id, user);
    }

    public Map<String, String> findById(String id) {
        try {
            // Simulate an actual call to the database
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return users.get(id);
    }
}
