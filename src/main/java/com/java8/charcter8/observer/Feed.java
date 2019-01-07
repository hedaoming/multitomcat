package com.java8.charcter8.observer;

import java.util.ArrayList;
import java.util.List;

public class Feed implements Subject {

    List<Observer> observers = new ArrayList<Observer>();

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyAll(String tweet) {
        observers.forEach(o -> o.notify(tweet));
    }

    public static void main(String args[]){
        // 1.实现类
        Feed f = new Feed();
        f.register(new NYTimeObserver());
        f.register(new LeMonde());
        f.notifyAll("The queen said her favourite book is Java 8 in Action!");

        // 2.lambda
        f.register((String sweet) -> {
            if (sweet != null && sweet.contains("queen")){
                System.out.println("Breaking news in NY! " + sweet);
            }
        });
    }
}
