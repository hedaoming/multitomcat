package com.java8.charcter8.observer;

public class NYTimeObserver implements Observer {
    @Override
    public void notify(String tweet) {
        if (tweet != null && tweet.contains("queen")){
            System.out.println("Breaking news in NY! " + tweet);
        }
    }
}
