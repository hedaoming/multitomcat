package com.java8.charater3;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PensionWeight {

    private Integer pensionWeightId;

    private String pensionProductId;

    private BigDecimal investAmount;

    private String investCycle;

    private BigDecimal weight;

    private InvestType type;

    public enum InvestType{
        MONTH,
        WEEK
    }

    public PensionWeight(){}

    public PensionWeight(BigDecimal investAmount, BigDecimal weight){
        this.investAmount = investAmount;
        this.weight = weight;
    }

    public static PensionWeight zeroPoint = new PensionWeight(new BigDecimal(0), new BigDecimal(0));
}