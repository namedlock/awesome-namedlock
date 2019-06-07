package com.namedlock.concurrent.interrupt;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AWaitSignalThread extends Thread{

    static private Map<String,Integer> oneSizeMap = Maps.newHashMap();

    static private ReentrantLock reentrantLock = new ReentrantLock();

    static private Condition putFull = reentrantLock.newCondition();
    static private Condition notEmpty = reentrantLock.newCondition();

    Random random = new Random();
    static Manager manager = new Manager();

    @Override
    public void run() {
        for (int i =0 ;i < 100; i ++){
            manager.put(""+i, i );
            System.out.println("put:"+i);
        }
    }

    static class Manager{
        public void put(String k, Integer v){
            reentrantLock.lock();

            try {
                if(oneSizeMap.size()==1){
                    try {
                        putFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                oneSizeMap.put(k,v);

                notEmpty.signal();
            }finally {
                reentrantLock.unlock();
            }

        }

        public Integer get(){
            reentrantLock.lock();

            try {

                if(oneSizeMap.size()==0){
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                String [] array = oneSizeMap.keySet().toArray(new String [] {});
                Integer v =  oneSizeMap.remove(array[0]);
                putFull.signal();
                return v;
            }finally {
                reentrantLock.unlock();
            }


        }
    }



    public static void main(String[] args) {
        AWaitSignalThread w = new AWaitSignalThread();
        w.start();

        GetThread getThread = new GetThread();
        getThread.start();

        try {
            TimeUnit.SECONDS.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class GetThread extends Thread{
        @Override
        public void run() {
            for (int i =0 ;i < 1000; i ++){
                System.out.println("get: "+manager.get());
            }
        }
    }
}
