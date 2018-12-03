package com.limaila.com;

import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 一、Stream API 的操作步骤：
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作(终端操作)
 */
public class StreamApiTest1 {


    // 创建Stream
    @Test
    public void test1() {
        // 1. Collection 提供了两个方法  stream() 与 parallelStream()
        List<String> list = Arrays.asList("123", "456", "789");
        Stream<String> stream1 = list.stream();

        //2. 通过 Arrays 中的 stream() 获取一个数组流
        Integer[] nums = new Integer[10];
        Stream<Integer> stream2 = Arrays.stream(nums);

        //3. 通过 Stream 类中静态方法 of()
        Stream<Integer> stream3 = Stream.of(123, 456, 789);

        //4. 创建无限流
        //迭代
        Stream<Integer> stream4 = Stream.iterate(0, (o1) -> o1 + 2).limit(10);
        stream4.forEach(System.out::println);

        //生成
        Stream<Double> stream5 = Stream.generate(Math::random).limit(10);
        stream5.forEach(System.out::println);
    }
}
