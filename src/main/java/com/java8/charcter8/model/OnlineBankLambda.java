package com.java8.charcter8.model;

import java.util.function.Consumer;

public class OnlineBankLambda {
    public void process(int id, Consumer<Customer> makeCustomerHappy){
        // 通用处理：查找customer信息，做一些操作等
        Customer c = Customer.getById(id);

        // 灵活操作：不同支行有不同的处理方法
        makeCustomerHappy.accept(c);
    }

    public static void main(String args[]){
        new OnlineBankLambda().process(1, (Customer c) -> System.out.println("广东支行发红包"));
        new OnlineBankLambda().process(1, (Customer c) -> System.out.println("北京支行发现金"));
    }
}
