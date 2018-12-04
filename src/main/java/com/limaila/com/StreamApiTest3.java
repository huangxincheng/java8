package com.limaila.com;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public class StreamApiTest3 {

    List<Emp> emps = Arrays.asList(
            new Emp(111,"a111"),
            new Emp(112,"a112"),
            new Emp(113,"a113"),
            new Emp(114,"a114"),
            new Emp(114,"a114"),
            new Emp(114,"a114")
    );

    //2. 中间操作
	/*
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
    @Test
    public void test1() {
        List<String> strList = Arrays.asList("aaa", "bbb", "ccc", "ddd", "eee");
        strList.stream().map(String::toUpperCase).forEach(System.out::println);
        System.out.println("================================");
        emps.stream().map(Emp::getAge).forEach(System.out::println);

        System.out.println("================================2");
        Stream<Stream<Character>> streamStream = strList.stream().map(this::getStreamChar);
        streamStream.forEach((s) -> {
            s.forEach(System.out::println);
        });
        System.out.println("================================3");
        Stream<Character> characterStream = strList.stream().flatMap(this::getStreamChar);
        characterStream.forEach(System.out::println);


    }

    public Stream<Character> getStreamChar(String str) {
        List<Character> characters = new ArrayList<>();
        for (char c : str.toCharArray()) {
            characters.add(c);
        }
        return characters.stream();
    }

    /*
		sorted()——自然排序
		sorted(Comparator com)——定制排序
	 */
    @Test
    public void test2() {
        emps.stream().map(Emp::getName).sorted().forEach(System.out::println);

        System.out.println("===============================================");

        emps.stream().sorted((o1,o2) -> {
            if (o1.getAge() == o2.getAge()) {
                return o1.getName().compareTo(o2.getName());
            } else {
                return Integer.compare(o1.getAge(),o2.getAge());
            }
        }).forEach(System.out::println);
    }
}
