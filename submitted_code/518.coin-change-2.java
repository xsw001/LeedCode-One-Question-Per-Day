//
// @lc app=leetcode.cn id=518 lang=java
//
// [518] coin-change-2
//
class Solution {
    int ans;
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0] = 1;
        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] = dp[j] + dp[j-coins[i]];
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[amount];
    }
}
// @lc code=end