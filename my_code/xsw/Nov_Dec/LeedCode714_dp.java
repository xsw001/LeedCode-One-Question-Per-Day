package xsw.Nov_Dec;/*
714. 买卖股票的最佳时机含手续费
给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。

你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。

返回获得利润的最大值。

注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。

示例 1:

输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
输出: 8
解释: 能够达到的最大利润:
在此处买入 prices[0] = 1
在此处卖出 prices[3] = 8
在此处买入 prices[4] = 4
在此处卖出 prices[5] = 9
总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
注意:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.
*/


import java.util.Arrays;

public class LeedCode714_dp {
    //傻傻想了一个多小时也没出结果，不会动态规划
    public static int maxProfit1(int[] prices, int fee) {
        int totalProfit = 0;
        int tempProfit = 0;
        int len = prices.length;
        if (len == 1)
            return 0;
        int low = 0, high = 0;
        while (high < len) {
            while (high < len - 1 && prices[high] > prices[high + 1]) {
                ++high;
                prices[high] -= 2;
            }
            if (high == len - 1)
                break;
            while (high < len - 1 && prices[high] < prices[high + 1]) {
                ++high;
                prices[high] -= 2;
            }
            ++high;
        }
        System.out.println(Arrays.toString(prices));
        high = 0;

        return totalProfit;
    }

    public static void main(String[] args) {
        int[] price = {9, 8, 7, 6, 5, 1, 2, 3, 2, 3, 4, 5, 8, 4, 9, 8, 7, 6};
        int fee = 2;
        System.out.println(maxProfit2(price, fee));
    }

    //动态规划
    public static int maxProfit(int[] prices, int fee) {
        int sell = 0;
        int buy = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            sell = Math.max(sell, buy - fee + prices[i]);
            buy = Math.max(buy, sell - prices[i]);
        }
        return sell;
    }

    //贪心
    /*
            min首先记录第一个最小值，
            在满足prices[i]-fee>min时进行买卖计算，
            当天的价格由于已经减过一次手续费，
            所以令min=prices[i]-fee而不是prices[i]。
    */
    public static int maxProfit2(int[] prices, int fee) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int res = 0;
        int min = prices[0];
        for (int i = 1; i < len; i++) {
            if (prices[i] < min) {
                //未发生买卖时找到第一个最小数，如果发生过买卖则比较当前价格和上次卖出时的价格减去手续费
                min = prices[i];
            } else {
                if (prices[i] - fee > min) {
                    res += prices[i] - min - fee;// prices[i]-(prices[上次卖出时的i]-fee)-fee 也就等于prices[i]-prices[上次卖出时的i]
                    //当前的价格已经减过手续费，所以min的值应为当前价格减去手续费。
                    min = prices[i] - fee;
                }
            }
        }
        return res;
    }
}
