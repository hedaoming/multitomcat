package com.java8.charcter9;

public interface AnimalDaily {
    default void run(){
        System.out.println("run");
    }

    void eat();
}
