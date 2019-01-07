package com.java8.charcter6;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class Dish implements Comparable {
    private String name;
    private int calories;
    private BigDecimal money;
    private String type;
    private boolean isVegetarian;

    public enum CaloriesLevel{
        Low,
        Mid,
        High
    }

    public static List<Dish> init(){
        List<Dish> dishes = Lists.newArrayList(
                new Dish("apple", 200,new BigDecimal(2.9),"Fruit", true),
                new Dish("banana", 1000,new BigDecimal(1.9), "Fruit", true),
                new Dish("chicken", 400,new BigDecimal(5.8),"Meet", false),
                new Dish("milk", 600, new BigDecimal(0.9), "Liuq", true));
        return dishes;
    }


    @Override
    public int compareTo(Object o) {
        if (!(o instanceof Dish)){
            throw new IllegalArgumentException("不是Dish类实例");
        }
        Dish dish = (Dish) o;
        return this.calories - dish.calories;
    }

    public CaloriesLevel getCalortiesLevel(){
        if (this.getCalories() <= 400)
            return CaloriesLevel.Low;
        else if (this.getCalories() <= 700)
            return CaloriesLevel.Mid;
        else
            return CaloriesLevel.High;
    }
}
