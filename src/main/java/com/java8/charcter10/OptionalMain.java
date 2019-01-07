package com.java8.charcter10;

import java.util.Optional;

public class OptionalMain {

    public static  String getColorName(Person person){
        if (person == null)
            return null;
        if (person.getCar() == null)
            return null;
        if (person.getCar().getColor() == null)
            return null;
        return person.getCar().getColor().getColorName();
    }

    public static String getColorNameOptional(Optional<Person> person){
        return person.flatMap(Person::getCarOptional)
                .flatMap(Car::getColorOptional)
                .map(Color::getColorName)
                .orElse("Unknow");
    }

    public static void main(String args[]){
        Person p1 = new Person();
        Car c1 = new Car();
        Color color1 = new Color();
        color1.setColorName("color name");
        c1.setColor(color1);
        p1.setCar(c1);

        Optional<Person> personOptional = Optional.of(p1);
        Optional.of(c1);
    }
}
