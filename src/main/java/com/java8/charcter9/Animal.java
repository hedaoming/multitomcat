package com.java8.charcter9;

public class Animal implements AnimalDaily {
    @Override
    public void eat() {
        System.out.println("eat");
    }

    public static void main(String args[]){
        Animal animal = new Animal();
        animal.eat();
        animal.run();
    }
}
