package com.limaila.com;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * 一、 Stream 的操作步骤
 *
 * 1. 创建 Stream
 *
 * 2. 中间操作
 *
 * 3. 终止操作
 */
public class StreamApiTest3 {

    List<Emp> emps = Arrays.asList(
            new Emp(111,"a111"),
            new Emp(112,"a112"),
            new Emp(113,"a113"),
            new Emp(114,"a114"),
            new Emp(114,"a114"),
            new Emp(114,"a114")
    );

    //3. 终止操作
	/*
		allMatch——检查是否匹配所有元素
		anyMatch——检查是否至少匹配一个元素
		noneMatch——检查是否没有匹配的元素
		findFirst——返回第一个元素
		findAny——返回当前流中的任意元素
		count——返回流中元素的总个数
		max——返回流中最大值
		min——返回流中最小值
	 */

	@Test
	public void test1() {
        boolean b1 = emps.stream().allMatch((t) -> t.getName().equals("a114"));
        System.out.println(b1);

        boolean b2 = emps.stream().anyMatch((t) -> t.getName().equals("a114"));
        System.out.println(b2);

        boolean b3 = emps.stream().noneMatch((t) -> t.getName().equals("a114"));
        System.out.println(b3);

        Optional<Emp> b4 =
                emps.stream().sorted(Comparator.comparingInt(Emp::getAge)).findFirst();
        System.out.println(b4.get());

        Optional<Emp> b5 = emps.stream().sorted(Comparator.comparingInt(Emp::getAge)).findAny();
        System.out.println(b5.get());

        long count = emps.stream().count();
        System.out.println(count);

        // 获取排在最后面的那一个
        Optional<Emp> max = emps.stream().max((o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()));
        System.out.println(max.get());

        // 获取排在最前面的那一个
        Optional<Emp> min = emps.stream().min((o1, o2) -> Integer.compare(o1.getAge(), o2.getAge()));
        System.out.println(min.get());
    }

}
