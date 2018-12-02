package com.limaila.com;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public class LambdaTest2 {

    @Test
    public void t1() {
        List<Emp> emps = Arrays.asList(
                new Emp(1, "a1"),
                new Emp(2, "a1"),
                new Emp(2, "a2"),
                new Emp(3, "a1")
        );
        Collections.sort(emps, (o1,o2) -> {
            if (o1.age == o2.age) {
                return -o1.name.compareTo(o2.name);
            } else {
                return -Integer.compare(o1.age,o2.age);
            }
        });
        emps.forEach(System.out::println);
    }

    @Test
    public void t2() {
        System.out.println(MyFunc1.handMyFund1("AaA", String::toUpperCase));
        System.out.println(MyFunc1.handMyFund1("AaA", (str) -> str.toLowerCase()));
    }


    @Test
    public void t3() {
        Long hand = MyFunc2.hand(1L, 2L, (o1, o2) -> {
            return o1 + o2;
        });
        System.out.println(hand);


        Integer hand1 = MyFunc2.hand(3, 4, (Integer o1, Integer o2) -> {
            return o1 * o2;
        });
        System.out.println(hand1);
    }


    interface MyFunc2<T,R> {
        R invoke(T t1, T t2);

        static <T,R> R hand(T t1, T t2, MyFunc2<T,R>  myFunc2) {
            return myFunc2.invoke(t1,t2);
        }
    }

    interface MyFunc1 {
        String getValue(String str);

        static String handMyFund1(String str, MyFunc1 myFunc1) {
            return myFunc1.getValue(str);
        }
    }

    static class Emp {
        private int age;

        private String name;


        public Emp(int age, String name) {
            this.age = age;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Emp{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
