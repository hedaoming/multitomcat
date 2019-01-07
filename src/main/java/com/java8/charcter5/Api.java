package com.java8.charcter5;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Api {

    public static void main(String arg[]){
//        testFlatMap();
        testReduce();
    }

    public static void testFlatMap(){
        String[][] arr = {{"abc","jfd","lie"}, {"hello", "byte"}};
        Stream<String[]> stream = Arrays.stream(arr);
//        stream.forEach(System.out::println);

        Stream<String> stringStream = stream.flatMap(strings -> Arrays.stream(strings));
        stringStream.forEach(System.out::println);
    }
    public static void testList(){
        List<List<Integer>> lists = Lists.newArrayList();
        lists.add(Arrays.asList(1,3,4));
        lists.add(Arrays.asList(5,6,3));

        Stream<List<Integer>> stream = lists.stream();

        Stream<Integer> integerStream = lists.stream()
                .flatMap(list -> list.stream());
    }

    public static void testReduce(){
        List<Integer> nums = Arrays.asList(2,3,4,5,6);
        Integer sum = nums.stream()
                .reduce(0, Integer::sum);
        Optional<Integer> max = nums.stream()
                .reduce(Integer::max);
        Optional<Integer> min = nums.stream()
                .reduce(Integer::min);
        System.out.println(sum);
    }

}
