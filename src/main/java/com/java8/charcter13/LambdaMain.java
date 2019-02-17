package com.java8.charcter13;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

public class LambdaMain {

    static HashMap hashMap = new HashMap();
    public static void threadNotSafe(){
        for (int i = 0; i < 10; i++) {
            Stream<Double> integerStream = Stream.generate(Math::random).limit(50000);
            integerStream.parallel().forEach(d -> hashMap.put(d, d));

            System.out.println("hashMap size:"+hashMap.size());
            hashMap.clear();
        }
    }

    static ConcurrentHashMap safeHashMap = new ConcurrentHashMap();
    public static void threadSafe(){
        for (int i = 0; i < 10; i++) {
            Stream<Double> integerStream = Stream.generate(Math::random).limit(50000);
            integerStream.parallel().forEach(d -> safeHashMap.put(d, d));

            System.out.println("safe HashMap size:"+safeHashMap.size());
            safeHashMap.clear();
        }
    }

    public static void main(String args[]){
        threadNotSafe();
        threadSafe();
    }
}
