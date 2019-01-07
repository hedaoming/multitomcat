package com.java8.charcter10;

import lombok.Data;

import java.util.Optional;

@Data
public class Person {
    private Car car;
    private Optional<Car> carOptional;
}
