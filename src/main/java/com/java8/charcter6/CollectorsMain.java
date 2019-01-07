package com.java8.charcter6;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.System.out;
import static java.util.stream.Collectors.*;

public class CollectorsMain {

    public static void main(String args[]){
//        max();
//        min();
//        join();
//        group();
        partition();
    }

    /**
     * 分区
     */
    public static void partition(){
        List<Dish> dishes = Dish.init();
        Map<Boolean, List<Dish>> collect = dishes.stream()
                .collect(partitioningBy(Dish::isVegetarian));
        collect.forEach((key, dish) ->{
            out.println("key:" + key + ",dish:" + dish.toString());
        });
    }

    /**
     * 分组
     */
    public static void group(){
        List<Dish> dishes =  Dish.init();
        // 1. 有匹配类型
        Map<String, List<Dish>> collect = dishes.stream()
                .collect(groupingBy(Dish::getType));
        collect.forEach((key, dish) -> {
            out.println("key:" + key + ",dish:" + dish);
        });

        out.println("================");
        // 2. 自定义类型
        Map<Dish.CaloriesLevel, List<Dish>> group =  dishes.stream()
                .collect(groupingBy(Dish::getCalortiesLevel));
        group.forEach((key, dish) ->{
            out.println("key:" + key + ",dish:" + dish);
        });

        // 3. 多级分组
        Map<String, Map<Dish.CaloriesLevel, List<Dish>>> groups = dishes.stream()
                .collect(groupingBy(Dish::getType,
                        groupingBy(dish ->{
                            if (dish.getCalories() <= 400)
                                return Dish.CaloriesLevel.Low;
                            else if (dish.getCalories() <= 700)
                                return Dish.CaloriesLevel.Mid;
                            else
                                return Dish.CaloriesLevel.High;
                        })));


    }

    /**
     * 连接
     */
    public static void join(){
        List<Dish> dishes =  Dish.init();
        String joinCollect = dishes.stream()
                .map(Dish::getName)
                .collect(joining(","));
        out.println(joinCollect);

    }

    /**
     * 最大值
     */
    public static void max(){
        Comparator<Dish> comparingDish = Comparator.comparing(Dish::getCalories);
        List<Dish> dishes =  Dish.init();

        Optional<Dish> maxDish = dishes.stream()
                .collect(maxBy(comparingDish));
        if (maxDish.isPresent()){
            out.println(maxDish.get());
        }
    }

    /**
     * 最小值
     */
    public static void min(){
        Comparator<Dish> comparingDish = Comparator.comparing(Dish::getCalories);
        List<Dish> dishes =  Dish.init();

        Optional<Dish> minDish = dishes.stream()
                .collect(minBy(comparingDish));
        if (minDish.isPresent()){
            out.println(minDish.get());
        }
    }

}
