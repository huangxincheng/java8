package com.limaila.com.method;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public interface Def {

    default void println(Object o) {
        System.out.println(o);
    }

    public static void show(Object o) {
        System.out.println("111");
    }
}
