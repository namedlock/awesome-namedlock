package com.namedlock;

import java.io.Serializable;
import java.util.List;

public class A implements Serializable {
    private static final long serialVersionUID = 7408579460619842073L;
    private int a;
    private long b;
    private List<String> list;
    private double c;
    private String d;
    private String e;
    private String f;
    private B bObject;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public long getB() {
        return b;
    }

    public void setB(long b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public B getbObject() {
        return bObject;
    }

    public void setbObject(B bObject) {
        this.bObject = bObject;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                ", b=" + b +
                ", list=" + list +
                ", c=" + c +
                ", d='" + d + '\'' +
                ", e='" + e + '\'' +
                ", f='" + f + '\'' +
                ", bObject=" + bObject +
                '}';
    }
}
