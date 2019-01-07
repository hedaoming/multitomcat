package com.java8.charcter5;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Practive {

    public static void getStream(){
        // 1. 生成流
        Stream<String> stringStream =  Stream.of("hello", "my","3","4");

        // 2. 文件生成流
        long count = 0;
        try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\zhaop\\Desktop\\data.txt"), Charset.defaultCharset())){
            count = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("count : " + count);

        // 3.1 无限流 iterate，会保存值的状态，不会改变每次使用的值
        Stream.iterate(1, n -> n * 2)
                .limit(10)
                .forEach(System.out::println);
        // 3.1.2 斐波那契数列
        Stream.iterate(new int[]{0, 1},t -> new int[]{t[1], t[1] + t[0]})
                .limit(20)
                .forEach(t -> System.out.println("t[0]:" + t[0] + ",t[1]:" + t[1]));

        // 3.2 无限流 generate:不会保存值的状态，会改变变量
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    public static List<Transaction> init(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brian = new Trader("Brian","Cambridge");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        return transactions;
    }

    public void map(){
        List<Transaction> transactions = init();
        // 1.mapToInt:避免暗含的装箱成本，int和Integer的效率差异
        int sum = transactions.stream()
                .mapToInt(Transaction::getValue)
                .sum();

        // 2. boxed装箱
        IntStream intStream = transactions.stream()
                .mapToInt(Transaction::getValue);
        Stream<Integer> boxed = intStream.boxed();

        // 3.OptionalInt等,orElse:若值不存在则返回0
        OptionalInt maxOptional = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max();
        int max = maxOptional.orElse(0);
    }

    public void intStream(){
        // rangeClosed: 包含指定的结束范围值
        IntStream intStream = IntStream.rangeClosed(0, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(intStream.count());
    }

    public static void sqrt(){
        Stream<int[]> stream = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        stream.limit(5)
                .forEach(arr -> System.out.println(arr[0] + "," + arr[1] + "," + arr[2]));
    }

    public static void main(String args[]){
//        sqrt();
        getStream();
    }

    public void par(){
        List<Transaction> transactions = init();
        // (1) 找出2011年发生的所有交易，并按交易额排序（从低到高）。
        List<Transaction> answer1 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
//        answer1.stream().forEach(System.out::println);
        // (2) 交易员都在哪些不同的城市工作过？
        List<String> diffCitys = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
//        diffCitys.forEach(System.out::println);
        // (3) 查找所有来自于剑桥的交易员，并按姓名排序。
        List<Trader> cambridgeTraders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
//        cambridgeTraders.forEach(System.out::println);
        // (4) 返回所有交易员的姓名字符串，按字母顺序排序
        List<String> names = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.toList());
//        names.forEach(System.out::println);
        // (5) 有没有交易员是在米兰工作的？
        Optional<Trader> hadMilan = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Milan"))
                .findAny();
        if (hadMilan.isPresent()){
//            System.out.println(hadMilan);
        }
        //(6) 打印生活在剑桥的交易员的所有交易额。
        Integer sum = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(0, Integer::sum);
//        System.out.println(sum);
        //(7) 所有交易中，最高的交易额是多少？
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        if (max.isPresent()){
            System.out.println(max.get());
        }
        //(8) 找到交易额最小的交易
        Optional<Integer> min = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::min);
        if (min.isPresent()){
            System.out.println(min.get());
        }
    }
}


