package com.java8.charcter7;

import com.google.common.base.Stopwatch;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.LongStream;

public class ParalleMain {

    public static long rangFor(long n){
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }

    public static long rangParalle(long n){
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + sum);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }

    public void test(){
        List<Long> forTimes = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            rangFor(100000);
            forTimes.add(stopwatch.elapsed(TimeUnit.NANOSECONDS));
        }

        List<Long> paraTimes = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            Stopwatch stopwatch = Stopwatch.createStarted();
            rangParalle(100000);
            paraTimes.add(stopwatch.elapsed(TimeUnit.NANOSECONDS));
        }

        System.out.println("==============");
        forTimes.forEach(System.out::println);
        System.out.println("==============");
        paraTimes.forEach(System.out::println);
        System.out.println("==============");
        System.out.println(forTimes.stream().min(Long::compareTo).get().toString());
        System.out.println("==============");
        System.out.println(paraTimes.stream().min(Long::compareTo).get().toString());
    }

    public static void main(String args[]){
        System.out.println("for range sum done in:" +
                measureSumPerf(ParalleMain::rangFor, 10_000_000) +
                " msecs");
        System.out.println("Parallel range sum done in:" +
                measureSumPerf(ParalleMain::rangParalle, 10_000_000) +
                " msecs");
    }
}
