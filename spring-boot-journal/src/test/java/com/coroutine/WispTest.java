package com.coroutine;

import com.alibaba.wisp.engine.WispEngine;
import com.google.common.util.concurrent.RateLimiter;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WispTest {

    @org.junit.Test
    public void test() throws InterruptedException {
        RateLimiter rateLimiter1 = RateLimiter.create(5);
        RateLimiter rateLimiter2 = RateLimiter.create(3);

        new Thread(){
            @Override
            public void run() {
                int count = 1000;
                while (count-->0){
                    WispEngine.dispatch(() -> {
                        rateLimiter1.acquire();
                        System.out.println(new Date() + ", r1" );
                    });

                    WispEngine.dispatch(() -> {
                        rateLimiter2.acquire();
                        System.out.println(new Date() + ", r2" );
                    });
                }
            }
        }.start();

        Executors.newSingleThreadExecutor().execute(()->{

        });

        TimeUnit.SECONDS.sleep(120);
    }
}
