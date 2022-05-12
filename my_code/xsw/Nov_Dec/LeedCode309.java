package xsw.Nov_Dec;

/*
309. 最佳买卖股票时机含冷冻期
给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
示例:

输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
*/

public class LeedCode309 {

    public static int maxProfit1(int[] prices) {
        int len = prices.length;
        if(len < 2)
            return 0;
        int[][] dp = new int[len][3];

        dp[0][2] = 0;//冷冻期
        dp[0][1] = 0;
        dp[0][0] = -prices[0];

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1] - prices[i]);
            //第i天没有股票而且不处于冷冻期，说明什么都没干。
            //要么买要么卖，如果买了，不可能没有股票；如果卖了，不可能不处于冷冻期
            //如果i-1天有股票，那么今天没有股票肯定是买了，如果卖了，不可能不处于冷冻期，所以i-1没有股票
            //没股票有两种状态，取最大值
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][2]);
            dp[i][2] = dp[i-1][0] + prices[i];
        }
        return Math.max(dp[len-1][1],dp[len-1][2]);
    }

    public static void main(String[] args) {

        int[] prices= {1,2,3,0,2};
        int profit = maxProfit(prices);
        System.out.println(profit);
    }

    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if(len < 2)
            return 0;
        int[][] dp = new int[len][2];

        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[1][0] = Math.max(dp[0][0], dp[0][1] + prices[1]);
        dp[1][1] = Math.max(dp[0][1], dp[0][0] - prices[1]);
        for (int i = 2; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 2][0] - prices[i]);
        }
        return dp[len-1][0];
    }
}
