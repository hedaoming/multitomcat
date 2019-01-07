package com.java8.charcter8.strategy;

public class Validator {

    private ValidationStrategy strategy;
    public Validator(ValidationStrategy strategy){
        this.strategy = strategy;
    }

    public boolean validator(String s){
        return strategy.execute(s);
    }

    public static void main(String args[]){
        // 1. 实现类用法
        Validator strValidator = new Validator(new IsAllLowerCase());
        System.out.println(strValidator.validator("aaa"));

        Validator numValidator = new Validator(new NumericValidation());
        System.out.println(numValidator.validator("39939"));

        // 2. lambda用法
        Validator strValidatorLam = new Validator((s) -> s.matches("[a-z]+"));
        System.out.println(strValidatorLam.validator("fjfsd"));

        Validator numValidatorLam = new Validator((s -> s.matches("[0-9]+")));
        System.out.println(numValidatorLam.validator("999838"));
    }
}
