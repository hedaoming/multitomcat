package com.java8.charcter8.model;

public class BankMain {

    public static void main(String args[]){
        GdOnlineBank gd = new GdOnlineBank();
        gd.process(1);

        BjOnlineBank bj = new BjOnlineBank();
        bj.process(1);


    }
}
