package com.java8.charcter8.model;

public class BjOnlineBank extends OnlineBank {
    @Override
    protected void makeCustomerHappy(Customer c) {
        // 抽奖
        draw();
    }

    private void draw() {
    }
}
