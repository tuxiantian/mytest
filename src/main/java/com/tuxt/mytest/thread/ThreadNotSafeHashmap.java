package com.tuxt.mytest.thread;

import java.util.HashMap;
import java.util.Map;

public class ThreadNotSafeHashmap {
    public static void main(String args[]) throws InterruptedException {
    	final Map<String, String> firstHashMap =new HashMap<String, String>();
//        final Map<String, String> firstHashMap =Collections.synchronizedMap(new HashMap<String, String>()) ;
        Thread t1 = new Thread() {
            public void run() {
                for (int i = 0; i < 2500; i++) {
                    firstHashMap.put(String.valueOf(i), String.valueOf(i));
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                for (int j = 2500; j < 5000; j++) {
                    firstHashMap.put(String.valueOf(j), String.valueOf(j));
                }
            }
        };
        t1.start();
        t2.start();

        Thread.sleep(1000);
        for (int k = 0; k < 5000; k++) {
            if (!String.valueOf(k).equals(firstHashMap.get(String.valueOf(k)))) {
                System.err.println(String.valueOf(k) + ":" + firstHashMap.get(String.valueOf(k)));
            }
        }
    }
}