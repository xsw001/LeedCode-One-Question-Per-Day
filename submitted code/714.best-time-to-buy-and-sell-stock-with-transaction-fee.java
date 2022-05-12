//
// @lc app=leetcode.cn id=714 lang=java
//
// [714] best-time-to-buy-and-sell-stock-with-transaction-fee
//
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int len=prices.length;
        if (len<2){
            return 0;
        }
        int res=0,min=prices[0];
        for (int i = 1; i <len ; i++) {
            if (prices[i]<min){
                //未发生买卖时找到第一个最小数，如果发生过买卖则比较当前价格和上次卖出时的价格减去手续费
                min=prices[i];
            }else {
                if (prices[i]-fee>min){
                    res+=prices[i]-min-fee;
                    //当前的价格已经减过手续费，所以min的值应为当前价格减去手续费。
                    min=prices[i]-fee;
                }
            }
        }
        return res;
    }
}
// @lc code=end