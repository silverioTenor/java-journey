package com.javajourney.track01concurrency.coupon;

import java.util.concurrent.atomic.AtomicInteger;

public class Coupon {
//    public int availableQty = 10;
    public static AtomicInteger availableQty = new AtomicInteger(10);

//    public int rescue() {
//        if (availableQty > 0) {
//            availableQty--;
//            return 200;
//        }
//        return 429;
//    }

    public int rescue() {
        if (availableQty.get() > 0) {
            availableQty.decrementAndGet();
            return 200;
        }
        return 429;
    }
}
