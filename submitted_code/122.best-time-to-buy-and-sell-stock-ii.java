//
// @lc app=leetcode.cn id=122 lang=java
//
// [122] best-time-to-buy-and-sell-stock-ii
//
class Solution {
    public int maxProfit(int[] prices) {
        int[] copy = Arrays.copyOf(prices, prices.length + 1);
        int max = 0;
        int temp = 0;
        for (int i = 0; i < copy.length - 1; i++) {
            if (copy[i + 1] - copy[i] < 0) {
                max += temp;
                temp = 0;
            } else {
                temp += (copy[i + 1] - copy[i]);
            }
        }
        return Math.max(max,temp);
    }
}
// @lc code=end