package com.limaila.com.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Objects;
import java.util.Set;

/**
 * Author: huangxincheng
 * <p>
 * <p>
 **/
public class TestDate {

    /**
     * 1. LocalDate、LocalTime、LocalDateTime
     **/
    @Test
    public void test1() {
        // 获取当前时间
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        // 指定时间
        LocalDateTime ldt2 = LocalDateTime.of(2016, 12, 1, 10, 0, 20);
        System.out.println(ldt2);

        // 加一天
        LocalDateTime ldt3 = ldt2.plusDays(1);
        System.out.println(ldt3);

        // 减一个月
        LocalDateTime ldt4 = ldt2.minusMonths(1);
        System.out.println(ldt4);

        // 加一天
        LocalDateTime ldt5 = ldt2.minusDays(1);
        System.out.println(ldt5);
    }

    /**
     * 2. Instant : 时间戳。
     * （使用 Unix 元年  1970年1月1日 00:00:00 所经历的毫秒值）
     * **/
    @Test
    public void test2() {
        Instant ins = Instant.now(); ////默认使用 UTC 时区
        System.out.println(ins);
        System.out.println(ins.getNano());
        System.out.println(ins.toEpochMilli());
        OffsetDateTime odt = ins.atOffset(ZoneOffset.ofHours(8)); //得到我们的时区
        System.out.println(odt);
    }


    //4. TemporalAdjuster : 时间校正器
    @Test
    public void test4(){
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println(ldt);

        LocalDateTime ldt2 = ldt.withDayOfMonth(10);
        System.out.println(ldt2);

        LocalDateTime ldt3 = ldt.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        System.out.println(ldt3);

        //自定义：下一个工作日
        LocalDateTime ldt5 = ldt.with((l) -> {
            LocalDateTime ldt4 = (LocalDateTime) l;

            DayOfWeek dow = ldt4.getDayOfWeek();

            if(dow.equals(DayOfWeek.FRIDAY)){
                return ldt4.plusDays(3);
            }else if(dow.equals(DayOfWeek.SATURDAY)){
                return ldt4.plusDays(2);
            }else{
                return ldt4.plusDays(1);
            }
        });

        System.out.println(ldt5);

    }

    /**
     * Duration : 用于计算两个“时间”间隔
     * Period : 用于计算两个“日期”间隔
     */
    @Test
    public void test3() throws InterruptedException {
        Instant ins1 = Instant.now();
        System.out.println("---------------");
        Thread.sleep(1000);

        Instant ins2 = Instant.now();
        System.out.println("所耗费时间为：" + Duration.between(ins1, ins2).toMillis());
        System.out.println("----------------------------------");

        LocalDate ld1 = LocalDate.now();
        LocalDate ld2 = LocalDate.of(2016, 1, 1);
        Period period = Period.between(ld1, ld2);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());

        LocalDateTime of = LocalDateTime.of(2018, 12, 1, 0, 0, 0);
        LocalDateTime of1 = LocalDateTime.of(2018, 11, 30, 0, 0, 0);
        long l = Duration.between(of, of1).toDays();
        System.out.println(l);
    }


    //5. DateTimeFormatter : 解析和格式化日期或时间
    @Test
    public void test5(){
//		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");

        LocalDateTime ldt = LocalDateTime.now();
        String strDate = ldt.format(dtf);

        System.out.println(strDate);

        LocalDateTime newLdt = ldt.parse(strDate, dtf);
        System.out.println(newLdt);
    }

    @Test
    public void test7(){
        LocalDateTime ldt = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(ldt);

        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("US/Pacific"));
        System.out.println(zdt);
    }

    @Test
    public void test6(){
        Set<String> set = ZoneId.getAvailableZoneIds();
        set.forEach(System.out::println);
    }
}
