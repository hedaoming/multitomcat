package com.java8.charater3;

import java.util.function.Function;

public class FunctionAnd {

    public static void main(String args[]){
        Function<String, String> addHeader = Letter::addHead;
        addHeader.andThen(Letter::addContent)
                .andThen(Letter::addBottom);
        String content = addHeader.apply(" 写信  ");
        System.out.println(content);
    }

}
class Letter{

    public static String addHead(String msg){
        return "header -*****-";
    }

    public static String addContent(String msg){
        return msg + " Content ";
    }

    public static String addBottom(String msg){
        return msg + " Bottom";
    }
}
