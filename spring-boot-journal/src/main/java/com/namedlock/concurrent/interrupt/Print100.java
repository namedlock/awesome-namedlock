package com.namedlock.concurrent.interrupt;

import java.util.concurrent.CountDownLatch;

public class Print100 {

    private static CountDownLatch latch = new CountDownLatch(2);

    private  static Object oddLock =new Object();
    private  static Object evenLock =new Object();

    static class PrintOddThread extends Thread{
        @Override
        public void run() {
            for (int i=1; i <= 100; i ++){
                if(i%2 != 0){
                    synchronized (oddLock){
                        System.out.println(i);
                        oddLock.notify();
                    }

                    synchronized (evenLock){
                        try {
                            evenLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            latch.countDown();

        }
    }

    static class PrintEvenThread extends Thread{
        @Override
        public void run() {
            for (int i=1; i <= 100; i ++){
                if(i%2 == 0){
                    synchronized (oddLock){
                        try {
                            oddLock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    synchronized (evenLock){
                        System.out.println(i);
                        evenLock.notify();
                    }
                }
            }

            latch.countDown();
        }
    }

    public static void main(String[] args) {

        PrintEvenThread evenThread = new PrintEvenThread();
        evenThread.start();

        PrintOddThread oddThread = new PrintOddThread();
        oddThread.start();


        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
