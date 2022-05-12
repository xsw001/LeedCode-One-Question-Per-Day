//
// @lc app=leetcode.cn id=747 lang=java
//
// [747] min-cost-climbing-stairs
//
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];//其中 dp[i] 表示达到下标 i 的最小花费。
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            //dp[i]=min(dp[i−1]+cost[i−1],dp[i−2]+cost[i−2])
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);//应取上述两项的最小值
        }
        return dp[n];
    }
}
// @lc code=end