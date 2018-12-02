package com.limaila.com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class LambdaTest3 {

    /**
     * 断言型接口
     */
    @Test
    public void test4() {
        List<String> strings = this.filterStr(Arrays.asList("123", "789", "55555"), (s) -> s.length() > 3);
        strings.forEach(System.out::println);
    }

    public List<String> filterStr(List<String> str, Predicate<String> predicate) {
        List<String> newList = new ArrayList<>();
        for (String s : str) {
            if (predicate.test(s)) {
                newList.add(s);
            }
        }
        return newList;
    }


    /**
     * 函数式接口
     */
    @Test
    public void test3() {
        String s = this.handlerStr("\t\t\t AAA", String::trim);
        System.out.println(s);
    }

    public String handlerStr(String str, Function<String, String> function) {
        return function.apply(str);
    }

    /**
     * 供给型接口
     */
    @Test
    public void test2() {
        List<Integer> list = this.genRamdomNum(10, () -> (int) (Math.random() * 5)
        );
        list.forEach(System.out::println);
    }

    public List<Integer> genRamdomNum(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            list.add(supplier.get());
        }
        return list;
    }
    /**
     * 消费型接口
     */
    @Test
    public void test1() {
        this.consumer(1000D, (m) -> System.out.println("你消费了" + m + "元"));
    }
    public void consumer(Double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }
}
