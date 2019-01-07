package com.java8.charcter10;

import lombok.Data;

import java.util.Optional;

@Data
public class Car {
    private Color color;
    private Optional<Color> colorOptional;
}
