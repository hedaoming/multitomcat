package com.java8.charcter8.model;

public class GdOnlineBank extends OnlineBank {
    @Override
    protected void makeCustomerHappy(Customer c) {
        // 发红包
        sendRedPad();
    }

    private void sendRedPad() {

    }
}
