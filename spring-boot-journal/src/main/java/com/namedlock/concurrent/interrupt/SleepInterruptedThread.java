package com.namedlock.concurrent.interrupt;

import java.util.concurrent.TimeUnit;

public class SleepInterruptedThread extends Thread{
    @Override
    public void run() {
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        SleepInterruptedThread thread = new SleepInterruptedThread();
        thread.start();
        thread.interrupt();
    }
}
