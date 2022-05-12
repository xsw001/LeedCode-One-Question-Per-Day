//
// @lc app=leetcode.cn id=322 lang=java
//
// [322] coin-change
//
class Solution {
    public int coinChange(int[] coins, int amount) {
        int l = coins.length;
        int[] dp = new int[amount+1];
        Arrays.fill(dp,amount+1);
        dp[0] = 0;
        for(int i=0;i<l;++i){
            for(int j=coins[i];j<=amount;++j){
                dp[j] = Math.min(dp[j] , dp[j - coins[i]] + 1);
            }
        }
        return dp[amount]>amount?-1:dp[amount];
    }
}
// @lc code=end