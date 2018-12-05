package com.limaila.com.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public class Test {

    public static void main(String[] args) {
        int a = 10000;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            list.add(i);
        }
        list.parallelStream().forEach(integer -> {

            System.out.println("Thread" + Thread.currentThread().getName() + " 值" + integer);
        });
    }
}
