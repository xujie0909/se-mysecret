package com.xujie.mysecret.utilTest.mytest;

public class MyTest {
    public static void main(String[] args) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        System.out.println(timestamp.substring(0,10));
    }
}