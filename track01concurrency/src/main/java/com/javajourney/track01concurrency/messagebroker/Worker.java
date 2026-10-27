package com.javajourney.track01concurrency.messagebroker;

import java.util.LinkedList;
import java.util.Queue;

public class Worker {

    private Queue<String> list = new LinkedList<>();

    public synchronized void produce(String msg) throws InterruptedException {
        if (list.size() == 10) wait();

        list.add(msg);
        notifyAll();
    }

    public synchronized void consume() throws InterruptedException {
        if (list.isEmpty()) wait();

        String msg = list.poll();

        if (msg != null) {
            System.out.println(STR."[INFO] \{msg} | Qty: \{list.size()}");
        }

        notifyAll();
    }
}
