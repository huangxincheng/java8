package com.limaila.com;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

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
	/**
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

    //3. 终止操作
	/**
		归约
		reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
	 */

    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer n1 = list.stream().reduce(0, (o1, o2) -> {
            return o1 + o2;
        });
        System.out.println(n1);

        System.out.println("===============");

        Integer n2 = emps.stream().map(Emp::getAge).reduce(0, Integer::sum);
        System.out.println(n2);

        Optional<Integer> reduce = emps.stream().map(Emp::getAge).reduce(Integer::sum);
        System.out.println(reduce.orElse(null));

        System.out.println("===============");


    }


    /**
     * collect——将流转换为其他形式。接收一个 Collector接口的实现，
     * 用于给Stream中元素做汇总的方法
     */
    @Test
    public void test3() {
        List<String> list = emps.stream().map(Emp::getName).collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("=================");

        Set<String> set = emps.stream().map(Emp::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("=================");

        ArrayList<String> arrayList = emps.stream().map(Emp::getName)
                .collect(Collectors.toCollection(ArrayList::new));

        arrayList.forEach(System.out::println);

        System.out.println("=================");

        HashSet<String> hashSet = emps.stream().map(Emp::getName)
                .collect(Collectors.toCollection(HashSet::new));

        hashSet.forEach(System.out::println);
    }

    @Test
    public void test4() {
        //获取最大年龄数
        Optional<Integer> max1 = emps.stream().map(Emp::getAge)
                .collect(Collectors.maxBy(Integer::compare));
        System.out.println(max1.get());

        // 获取最小年龄数
        Optional<Integer> min = emps.stream().map(Emp::getAge)
                .min(Integer::compare);
        System.out.println(min.get());

        Integer sum = emps.stream()
                .collect(Collectors.summingInt(Emp::getAge));
                //.mapToInt(Emp::getAge).sum();
        System.out.println(sum);

        Double averaging = emps.stream()
                .collect(Collectors.averagingDouble(Emp::getAge));
        System.out.println(averaging);

        Long count = emps.stream()
//                .collect(Collectors.counting());
                .count();
        System.out.println(count);

        //总数据可以在这里拿
        IntSummaryStatistics iss = emps.stream().collect(Collectors.summarizingInt(Emp::getAge));
        System.out.println(iss.getSum() + "," + iss.getMax() + "," + iss.getMin() + "," + iss.getCount() + "," + iss.getAverage());
    }

    /**
     *  分组
     */
    @Test
    public void test5() {
        Map<Integer, List<Emp>> collect = emps.stream().collect(Collectors.groupingBy(Emp::getAge));
        System.out.println(collect);
        System.out.println("============");

        ConcurrentMap<Integer, List<Emp>> collect1 = emps.stream().collect(Collectors.groupingByConcurrent(o -> o.getAge()));
        System.out.println(collect1);
    }

    /**
     * 多级分组
     */
    @Test
    public void test6() {
        Map<Integer, Map<String, List<Emp>>> collect = emps.stream()
                .collect(Collectors.groupingBy(Emp::getAge, Collectors.groupingBy(Emp::getName)));
        System.out.println(collect);

        System.out.println("========================");

        Map<Integer, Map<String, List<Emp>>> collect1 = emps.stream().collect(Collectors.groupingBy(Emp::getAge, Collectors.groupingBy((o1) -> {
            if (o1.getAge() > 60) {
                return "老年";
            } else {
                return "新年";
            }
        })));
        System.out.println(collect1);
    }


    /** 分区**/
    @Test
    public void test7() {
        Map<Boolean, List<Emp>> collect = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getAge() > 112));
        System.out.println(collect);
    }

    /**
     * 连接
     */
    @Test
    public void test8() {
        String collect = emps.stream().map(Emp::getName)
                .collect(Collectors.joining(",", "----", "----"));
        System.out.println(collect);
    }


    @Test
    public void test9() {
        Optional<Integer> collect = emps.stream().map(Emp::getAge)
                .collect(Collectors.reducing(Integer::sum));
        System.out.println(collect.get());
    }

}
