package com.java8.charcter8.model;

public abstract class OnlineBank {

    public void process(int id){
        // 通用处理：查找customer信息，做一些操作等
        Customer c = Customer.getById(id);

        // 灵活操作：不同支行有不同的处理方法
        makeCustomerHappy(c);
    }

    protected abstract void makeCustomerHappy(Customer c);
}
