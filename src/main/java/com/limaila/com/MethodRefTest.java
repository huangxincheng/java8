package com.limaila.com;


import org.junit.Test;

import javax.management.openmbean.CompositeDataSupport;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

/**
 * 方法应用一定要和函数接口一起使用
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 *
 */
public class MethodRefTest {

    // 对象的引用 :: 实例方法名
    @Test
    public void test1() {
        Consumer<String> c1 = (o) -> System.out.println(o);
        c1.accept("哈哈哈c1");
        Consumer<String> c2 = System.out::println;
        c2.accept("哈哈哈c2");

        Emp emp = new Emp(1,"111");
        Supplier<String> sp1 = () -> emp.getName();
        System.out.println(sp1.get());
        Supplier<String> sp2 = emp::getName;
        System.out.println(sp2.get());
    }

    //类名 :: 静态方法名
    @Test
    public void test2() {
        Comparator<Integer> c1 = (x,y) -> Integer.compare(x,y);
        System.out.println(c1.compare(1, 2));

        Comparator<Integer> c2 = Integer::compare;
        System.out.println(c2.compare(1,2));
    }

    //类名 :: 实例方法名
    @Test
    public void test3() {
        BiPredicate<String,String> bp1 = (o1,o2) -> o1.equals(o2);
        System.out.println(bp1.test("1", "2"));

        BiPredicate<String,String> bp2 = String::equals;
        System.out.println(bp2.test("1", "2"));
    }

    //构造器引用
    @Test
    public void test4() {
        Supplier<Emp> sp1 = () -> new Emp();
        System.out.println(sp1.get());
        Supplier<Emp> sp2 = Emp::new;
        System.out.println(sp2.get());
    }

    //数组应用
    @Test
    public void test5() {
       Function<Integer,String[]> fb1 = (t) -> new String[t];
        String[] apply1 = fb1.apply(2);
        System.out.println(apply1);
        Function<Integer,String[]> fb2 = String[]::new;
        String[] apply2 = fb2.apply(3);
        System.out.println(apply2);
    }

    //数组应用
    @Test
    public void test6() {
       Supplier<List<Emp>> sp1 = () -> new ArrayList<>();
       sp1.get();
       Supplier<List<Emp>> sp2 = ArrayList::new;
       sp2.get();
    }



}

class Emp {
    private int age;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Emp(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Emp(){}

    @Override
    public String toString() {
        return "Emp{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
