package com.java8.charcter12;

import org.junit.Assert;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        // 1. 实现1：借助已有的api
        /*LocalDate localDate = LocalDate.from(temporal);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        switch (dayOfWeek) {
            case SATURDAY:
            case SUNDAY:
            case FRIDAY:
                return localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
            default:
                return localDate.plusDays(1);
        }*/
        // 实现2：
        DayOfWeek dayOfWeek  =DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        int dayAdd = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY){
            dayAdd = 3;
        }else if (dayOfWeek == DayOfWeek.SATURDAY){
            dayAdd = 2;
        }
        return temporal.plus(dayAdd, ChronoUnit.DAYS);
    }

    public static void main(String args[]) {
        // 1.实现1：实现TemporalAdjuster接口，重写方法
        LocalDate now = LocalDate.parse("2019-01-07");
        LocalDate nextWorkingDay = now.with(new NextWorkingDay());
        Assert.assertTrue(nextWorkingDay.compareTo(LocalDate.parse("2019-01-08")) == 0);

        now = LocalDate.parse("2019-01-08");
        nextWorkingDay = now.with(new NextWorkingDay());
        Assert.assertTrue(nextWorkingDay.compareTo(LocalDate.parse("2019-01-09")) == 0);

        now = LocalDate.parse("2019-01-11");
        nextWorkingDay = now.with(new NextWorkingDay());
        Assert.assertTrue(nextWorkingDay.compareTo(LocalDate.parse("2019-01-14")) == 0);

        now = LocalDate.parse("2019-01-12");
        nextWorkingDay = now.with(new NextWorkingDay());
        Assert.assertTrue(nextWorkingDay.compareTo(LocalDate.parse("2019-01-14")) == 0);

        now = LocalDate.parse("2019-01-13");
        nextWorkingDay = now.with(new NextWorkingDay());
        Assert.assertTrue(nextWorkingDay.compareTo(LocalDate.parse("2019-01-14")) == 0);

        // 2.实现2：函数式
        LocalDate nextWorkingDay2 =now.with(temporal -> {
            DayOfWeek dayOfWeek  =DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayAdd = 1;
            if (dayOfWeek == DayOfWeek.FRIDAY){
                dayAdd = 3;
            }else if (dayOfWeek == DayOfWeek.SATURDAY){
                dayAdd = 2;
            }
            return temporal.plus(dayAdd, ChronoUnit.DAYS);
        });
    }
}
