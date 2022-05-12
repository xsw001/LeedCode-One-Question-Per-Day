//
// @lc app=leetcode.cn id=188 lang=java
//
// [188] best-time-to-buy-and-sell-stock-iv
//
class Solution {
    public int maxProfit(int k, int[] prices) {
        if(k == 0)
            return 0;
        int[] buy = new int[k+1];
        Arrays.fill(buy,Integer.MAX_VALUE);
        int[] pro = new int[k+1];
        for(int price : prices) {
            for(int i = 0; i < k; ++i){
                buy[i] = Math.min(buy[i], price);
                pro[i] = Math.max(pro[i], price - buy[i]);
                buy[i+1] = Math.min(buy[i+1], price - pro[i]);
                pro[i+1] = Math.max(pro[i+1], price - buy[i+1]);
            }
        }
        return pro[k-1];
    }
}
// @lc code=end