package com.namedlock.mem.reference;

import java.lang.ref.*;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class References {
    private static ReferenceQueue<VeryBig> rq =
            new ReferenceQueue<VeryBig>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null) {
            System.out.println("In queue: " + inq.get());
        }else {
            System.out.println("poll null.");
        }
    }

    public static void main(String[] args) {
        int size = 1000000;
        // Or, choose size via the command line:
        if (args.length > 0)
            size = new Integer(args[0]);
        LinkedList<SoftReference<VeryBig>> sa =
                new LinkedList<SoftReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<VeryBig>(
                    new VeryBig("Soft " + i), rq));
            System.out.println("Just created: "+i+"   " + sa.getLast());

            if(sa.get(0)==null){
                sa.remove(0);
                System.out.println("sa's 0 index is null, removing.");
            }
            checkQueue();
            try {
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


//        LinkedList<WeakReference<VeryBig>> wa =
//                new LinkedList<WeakReference<VeryBig>>();
//        for (int i = 0; i < size; i++) {
//            wa.add(new WeakReference<VeryBig>(
//                    new VeryBig("Weak " + i), rq));
//            System.out.println("Just created: " + wa.getLast());
//            checkQueue();
//        }
//
//        SoftReference<VeryBig> s =
//                new SoftReference<VeryBig>(new VeryBig("Soft"));
//        WeakReference<VeryBig> w =
//                new WeakReference<VeryBig>(new VeryBig("Weak"));

//        System.out.println("gc...");
//        System.gc();



        LinkedList<PhantomReference<VeryBig>> pa =
                new LinkedList<PhantomReference<VeryBig>>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<VeryBig>(
                    new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + pa.getLast());
            checkQueue();
        }
    }
}
