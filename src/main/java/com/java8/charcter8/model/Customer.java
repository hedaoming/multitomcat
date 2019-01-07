package com.java8.charcter8.model;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;

    public static Customer getById(int id) {
        return new Customer();
    }
}
