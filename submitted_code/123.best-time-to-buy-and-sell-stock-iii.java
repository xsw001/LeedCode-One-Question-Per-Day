//
// @lc app=leetcode.cn id=123 lang=java
//
// [123] best-time-to-buy-and-sell-stock-iii
//
/*public class Solution {
    public int maxProfit(int[] prices) {
        int buy1 = Integer.MAX_VALUE;
        int buy2 = Integer.MAX_VALUE;
        int pro1 = 0;
        int pro2 = 0;
        for(int price : prices) {
            buy1 = Math.min(buy1, price);
            pro1 = Math.max(pro1, price - buy1);
            buy2 = Math.min(buy2, price - pro1);
            pro2 = Math.max(pro2, price - buy2);
        }
        return pro2;
    }
}*/
class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int buy1 = -prices[0], sell1 = 0;
        int buy2 = -prices[0], sell2 = 0;
        for (int i = 1; i < n; ++i) {
            buy1 = Math.max(buy1, -prices[i]);
            sell1 = Math.max(sell1, buy1 + prices[i]);
            buy2 = Math.max(buy2, sell1 - prices[i]);
            sell2 = Math.max(sell2, buy2 + prices[i]);
        }
        return sell2;
    }
}
// @lc code=end