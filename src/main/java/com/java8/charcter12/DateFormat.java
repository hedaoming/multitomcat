package com.java8.charcter12;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormat {


    public static void main(String args[]) {
//        format();
        formatter();
    }

    public static void format() {
        // 1.DateTimeFormatter是线程安全的
        LocalDate now = LocalDate.now();
        System.out.println("yyyyMMdd:" + now.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("yyyy-MM-dd:" + now.format(DateTimeFormatter.ISO_LOCAL_DATE));
    }

    public static void formatter() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDate now = LocalDate.now();
        System.out.println("模式创建输出模式：" + now.format(formatter));
    }

}
