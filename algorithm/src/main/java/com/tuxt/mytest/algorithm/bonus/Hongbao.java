package com.tuxt.mytest.algorithm.bonus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Hongbao {
    /**
     * 实现一个红包算法，支持输入红包金额（元为单位）和红包个数，输出各个红包金额。
     * 要求：<br>
     * a. 每个红包的最大金额不能超过红包金额的90%，最低不能低于1%<br>
     * b. 输入的红包金额只支持整数，输出的每个红包的结果需要支持到分（小数点后两位）
     * @param totalAmount
     * @param totalNumber
     * @return
     */
    public List<BigDecimal> split(BigDecimal totalAmount, int totalNumber) {
        List<BigDecimal> list = new ArrayList<>();
        if (totalAmount.compareTo(BigDecimal.ZERO) <= 0 || totalNumber<= 0) {
            return list;
        }
        BigDecimal highAmount=totalAmount.multiply(BigDecimal.valueOf(0.9));
        BigDecimal lowAmount=totalAmount.multiply(BigDecimal.valueOf(0.01));
        for (int i = totalNumber; i >= 2; i--) {
            BigDecimal random = getRandom(totalAmount.divide(BigDecimal.valueOf(totalNumber)).multiply(BigDecimal.valueOf(2)),highAmount,lowAmount);
            list.add(random);
            totalAmount =totalAmount.subtract(random);
        }
        list.add(totalAmount);
        return list;
    }

    private BigDecimal getRandom(BigDecimal x,BigDecimal highAmount,BigDecimal lowAmount){
        BigDecimal random = BigDecimal.valueOf(ThreadLocalRandom.current().nextDouble(lowAmount.doubleValue(), x.doubleValue())).setScale(2,BigDecimal.ROUND_HALF_UP);
        if (random.doubleValue()>highAmount.doubleValue()){
            return getRandom(x, highAmount, lowAmount);
        }else {
            return random;
        }
    }

    public static void main(String[] args) {
        Hongbao hongbao = new Hongbao();
        List<BigDecimal> list = hongbao.split(BigDecimal.valueOf(100), 20);

        for (BigDecimal integer : list) {
            System.out.print(integer);
            System.out.println(" ");
        }

    }
}
