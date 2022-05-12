//
// @lc app=leetcode.cn id=1130 lang=java
//
// [1130] last-stone-weight-ii
//
class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        int v = sum / 2;
        int[] dp = new int[v+1];
        for (int i = 0; i < stones.length; i++) {
            for (int j = v; j >= stones[i]; j--) {
                dp[j] = Math.max(dp[j],dp[j - stones[i]] + stones[i]);
            }
        }
        return  sum - dp[v] * 2;
    }
}
// @lc code=end