package com.namedlock.mem.reference;

public class VeryBig {
    private static final int SIZE = 1000000;
    private long[] la = new long[SIZE];
    private String ident;
    public VeryBig(String id) { ident = id; }
    public String toString() { return ident; }
    protected void finalize() {
        System.out.println("Finalizing " + ident);
    }
}
