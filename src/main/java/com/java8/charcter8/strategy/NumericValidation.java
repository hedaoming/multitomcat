package com.java8.charcter8.strategy;

public class NumericValidation implements ValidationStrategy {
    @Override
    public boolean execute(String s) {
        return s.matches("\\d+");
    }
}
