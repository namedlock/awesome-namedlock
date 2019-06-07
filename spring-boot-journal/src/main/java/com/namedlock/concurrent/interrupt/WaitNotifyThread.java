package com.namedlock.concurrent.interrupt;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class WaitNotifyThread extends Thread {

    static private Integer MAX=10;
    static private ArrayList<Integer> list = Lists.newArrayList();
    static private Object notEmpty = new Object();
    static private Object storeAvailable = new Object();
    static private StoreManager manager = new StoreManager();

    static private Random random = new Random();

    @Override
    public void run() {
        for (int i =0; i < 1000; i++){
            manager.add(i);
            System.out.println("put: "+i);
        }
    }

    static class StoreManager{
        public void add(Integer a){
            synchronized (storeAvailable){
                if(list.size()==MAX){
                    try {
                        System.out.println("wait on storeAvailable");
                        storeAvailable.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            list.add(a);

            synchronized (notEmpty){
                notEmpty.notifyAll();
            }

        }


        public Integer get(){
            Integer i=0;
            synchronized (notEmpty){
                if(list.size()==0){
                    try {
                        System.out.println(Thread.currentThread()+"wait on not empty.");
                        notEmpty.wait();
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread()+" interrupted.");
                    }
                }

                if(list.size()>0){
                    i = list.remove(0);
                }

            }

            synchronized (storeAvailable){
                storeAvailable.notifyAll();
            }
            return i;
        }
    }

    static class GetThread extends Thread{
        @Override
        public void run() {
            for (int i =0 ;i < 200; i ++){
                System.out.println(Thread.currentThread()+" get: "+manager.get());
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(Thread.currentThread()+" was interrupted, quit.");
                    break;
                }

            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyThread w = new WaitNotifyThread();
        w.start();

        GetThread getThread = new GetThread();
        getThread.start();

        GetThread getThread2 = new GetThread();
        getThread2.start();

        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(100)+1);
            getThread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Interrupted:"+getThread2.isInterrupted());

        try {
            TimeUnit.SECONDS.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
