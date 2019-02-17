package com.java8.charcter12;

import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.temporal.TemporalAdjusters;

@Slf4j
public class LocateMain {

    public static void main(String args[]) {
//        locateTest();
//        duraction();
//        LocalDate和Duration与Date api区别是，Date会被外界所修改，不是线程安全
        temporalAdjuster();
    }

    public static void temporalAdjuster() {
        // TemporalAdjusters:复杂日期的处理
        LocalDate now = LocalDate.now();
        System.out.println("这个月的第一天：" + now.with(TemporalAdjusters.firstDayOfMonth()).toString());
        System.out.println("下个月的第一天：" + now.with(TemporalAdjusters.firstDayOfNextMonth()).toString());
        System.out.println("明年的第一天：" + now.with(TemporalAdjusters.firstDayOfNextYear()).toString());
        System.out.println("上个月的最后一天：" + now.with(TemporalAdjusters.lastDayOfMonth()).toString());
    }

    public static void duraction() {
        // 1. duraction:持续时间
        LocalDateTime dateTime1 = LocalDateTime.of(2018, 5, 16, 15, 45, 12);
        LocalDateTime dateTime2 = LocalDateTime.of(2019, 1, 5, 15, 45, 12);
        Duration duration = Duration.between(dateTime1, dateTime2);
        log.info("相隔的日期：{}", duration.toDays());
        log.info("相隔的分钟：{}", duration.toMinutes());
        log.info("相隔的小时：{}", duration.toHours());

        // 2. Period:期
        Duration tenMin = Duration.ofMinutes(10);
        Period tenDay = Period.ofDays(10);
        log.info("十分钟：{}", tenMin.toMinutes());
        log.info("十天:{}", tenDay.getDays());
    }

    public static void locateTest() {

        // 1. 年月日
        LocalDate localDate = LocalDate.of(2018, 11, 5);
        log.info("年（数字）:{}", localDate.getYear());
        log.info("月（英文）:{}", localDate.getMonth());
        log.info("一个月中的第几天（数字）：{}", localDate.getDayOfMonth());
        log.info("一周中的第几天（英文）{}", localDate.getDayOfWeek());
        log.info("一年中的第几天（数字）：{}", localDate.getDayOfYear());
        log.info("月（数字）{}", localDate.getMonthValue());

        LocalDate now = LocalDate.now();
        log.info("now:{}", now.getEra().toString());

        // 2. 时分秒
        LocalTime localTime = LocalTime.of(13, 45, 15);

        // 3. 精细到年月日时分秒
        LocalDateTime.of(2018, 4, 15, 13, 45, 15);

        // 4. 修改date的内容：线程安全，创建副本
        localDate.withDayOfYear(2015);

        // 5. 相对方式修改date
        // 加上十天:localDate并不会被改变，返回值才是改变后的值
        LocalDate localDateTemp = localDate.plusDays(10);
        // 减去十天
        LocalDate localDate1 = localDate.minusDays(10);
    }
}
