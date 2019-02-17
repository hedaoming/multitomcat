package com.draw;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 区间实现抽奖概率
 */
public class IntervalLotteryService implements ILotteryService<Award>{

    /**
     * 通过区间来实现概率分配  
     * 奖品1 [0,10)
     * 奖品2 [10,90)
     * 奖品3 [90,100)
     * 奖品4 [100,980)
     * @param awardList
     * @return
     */
    @Override
    public Award draw(List<Award> awardList) throws DrawException {
        int listSize = awardList.size();
        if (awardList == null || listSize < 1){
            return null;
        }
        
        // 遍历list，计算区间最右值，封装到 int数组中 中
        int[] interval = new int[listSize];
        for (int i = 0; i < listSize; i++) {
            Award award = awardList.get(i);
            Integer weight = award.getWeight();
            
            if (i == 0){
                interval[0] = weight;
            }else {
                Integer lastAwardWeight = interval[i - 1];
                // 区间最右是上一个 区间最右 + 自己的权重
                interval[i] = weight + lastAwardWeight;
            }
        }
        
        // random int 获取随机到的值
        int maxInterval = interval[listSize - 1];
        Random random = new Random();
        int ranVal = random.nextInt(maxInterval);
        
        // 遍历 int数组 ，找到随机值是位于哪个区间，获取award，返回该award
        for (int i = 0; i < interval.length; i++) {
            if (ranVal >= interval[i] && ranVal < interval[i + 1]){
                return awardList.get(i + 1);
            }else if (ranVal < interval[0]){
                // 第一个区间之内
                return awardList.get(0);
            }
        }
        throw new DrawException("抽奖异常");
    }

    // 测试
    public static void main(String args[]){
        IntervalLotteryService lotteryService = new IntervalLotteryService();
        List<Award> awardList = Lists.newArrayList(
                new Award(1,"0.088 概率奖品", 88),
                new Award(2,"0.333 概率奖品", 333),
                new Award(3,"0.155 概率奖品", 155),
                new Award(4,"0.424 概率奖品", 424)
        );

        try {
            Map<String, Integer> statMap = Maps.newHashMap();
            for (int i = 0; i < 1000; i++) {
                Award drawAward = lotteryService.draw(awardList);
                String awardName = drawAward.getName();
                if (statMap.containsKey(awardName)){
                    Integer count = statMap.get(awardName);
                    statMap.put(awardName, count + 1);
                }else {
                    statMap.put(awardName, 1);
                }
            }

            statMap.forEach((k, v) -> System.out.println(k + " : " + v));

            /*
            0.088 概率奖品 : 94
            0.155 概率奖品 : 152
            0.333 概率奖品 : 320
            0.424 概率奖品 : 434
            */
        } catch (DrawException e) {
            System.out.println("抛异常");
        }
    }
}
