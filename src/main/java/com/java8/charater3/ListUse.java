package com.java8.charater3;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListUse {

    public static List<PensionWeight> init(){
        List<PensionWeight> pensionWeightList = Lists.newArrayList();
        PensionWeight p1 = new PensionWeight();
        p1.setInvestAmount(new BigDecimal(23));
        p1.setWeight(new BigDecimal(34));
        PensionWeight p2 = new PensionWeight();
        p2.setInvestAmount(new BigDecimal(33));
        p2.setWeight(new BigDecimal(44));
        PensionWeight p3 = new PensionWeight();
        p3.setInvestAmount(new BigDecimal(13));
        p3.setWeight(new BigDecimal(14));
        pensionWeightList.add(p1);
        pensionWeightList.add(p2);
        pensionWeightList.add(p3);
        return pensionWeightList;
    }

    public static void main(String args[]){
//        useSort();
        iterator();
    }

    public static void map(){
        List<PensionWeight> pensionWeightList = init();
        List<Integer> cycleLength = pensionWeightList.stream()
                .map(PensionWeight::getInvestCycle)
                .map(String::length)
                .collect(Collectors.toList());

        List<String> words = Lists.newArrayList("hello","wolrd");
        List<char[]> collect = words.stream()
                .map(String::toCharArray)
                .distinct()
                .collect(Collectors.toList());
        final List<String[]> collect1 = words.stream()
                .map(s -> s.split(""))
                .distinct()
                .collect(Collectors.toList());
    }
    public static void useSort(){
        List<PensionWeight> pensionWeightList = Lists.newArrayList();
        pensionWeightList.sort(Comparator.comparing(PensionWeight::getInvestAmount));
        pensionWeightList.sort(Comparator.comparing(PensionWeight::getInvestAmount));
        pensionWeightList
                .sort(Comparator.comparing(PensionWeight::getInvestAmount).reversed());
        pensionWeightList.sort(Comparator.comparing(PensionWeight::getInvestAmount)
                .thenComparing(Comparator.comparing(PensionWeight::getInvestCycle))
                .thenComparing(Comparator.comparing(PensionWeight::getPensionProductId)));
    }

    public static void iterator(){
        List<PensionWeight> pensionWeightList = init();
        List<BigDecimal> weightList = pensionWeightList.stream()
                .filter(pensionWeight -> {
                    System.out.println("1. filter investAmount" + pensionWeight.getInvestAmount());
                    return pensionWeight.getInvestAmount().compareTo(new BigDecimal(15)) > 0;
                })
                .map(pensionWeight -> {
                    System.out.println("2. map weight" + pensionWeight.getWeight());
                    return pensionWeight.getWeight();
                })
                .limit(3)
                .collect(Collectors.toList());
    }
}
