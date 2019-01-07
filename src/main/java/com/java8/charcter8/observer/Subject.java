package com.java8.charcter8.observer;

public interface Subject {
    void register(Observer observer);
    void notifyAll(String tweet);
}
