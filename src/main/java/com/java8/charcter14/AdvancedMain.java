package com.java8.charcter14;

import java.util.function.DoubleUnaryOperator;

public class AdvancedMain {

    /**
     * 科里化是一个将2个参数（比如， x 和 y ）的函数 f 转化为使用一个参数的函数 g ，并
     * 且这个函数的返回值也是一个函数，它会作为新函数的一个参数
     * 比如温度换算中：华氏摄氏之间的转换
     * @param f 转换因子
     * @param b 基线
     * @return
     */
    static DoubleUnaryOperator curriedConverter(double f, double b){
        return (double x) -> x * f + b;
    }

    public static void conver(){
        DoubleUnaryOperator unaryOperator1 = curriedConverter(9, 3);
        System.out.println(unaryOperator1.applyAsDouble(500));
    }

    public static void main(String args[]){
        conver();
    }
}
