package com.limaila.com.stream;

import com.limaila.com.stream.StreamApiTest3.Emp;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、 Stream 的操作步骤
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class StreamApiTest2 {
    //中间操作
    /*
	  筛选与切片
		filter——接收 Lambda ， 从流中排除某些元素。
		limit——截断流，使其元素不超过给定数量。
		skip(n) —— 跳过元素，返回一个扔掉了前 n 个元素的流。若流中元素不足 n 个，则返回一个空流。与 limit(n) 互补
		distinct——筛选，通过流所生成元素的 hashCode() 和 equals() 去除重复元素
	 */
    //内部迭代：迭代操作 Stream API 内部完成
    List<Emp> emps = Arrays.asList(
            new Emp(111,"a111"),
            new Emp(112,"a112"),
            new Emp(113,"a113"),
            new Emp(114,"a114"),
            new Emp(114,"a114"),
            new Emp(114,"a114")
            );

    @Test
    public void test1() {
        Stream<Emp> stream = emps.stream().filter((e) -> {
            System.out.println("测试中间操作");
            return e.getAge() > 112;
        });
        stream.forEach(System.out::println);
    }

    @Test
    public void test2() {
        emps.stream().filter((e) -> {
            System.out.println("测试中间操作");
            return e.getAge() > 112;
        }).limit(2)
          .forEach(System.out::println);
    }

    @Test
    public void test3() {
        emps.stream()
        .filter((e) -> {
            System.out.println("测试中间操作");
            return e.getAge() > 112;
        })
        .skip(2)
        .forEach(System.out::println);
    }

    @Test
    public void test4() {
        emps.stream().distinct().forEach(System.out::println);
    }


    //2. 中间操作
	/*
		映射
		map——接收 Lambda ， 将元素转换成其他形式或提取信息。接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
		flatMap——接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
	 */
    @Test
    public void test5() {
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
    public void test6() {
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
