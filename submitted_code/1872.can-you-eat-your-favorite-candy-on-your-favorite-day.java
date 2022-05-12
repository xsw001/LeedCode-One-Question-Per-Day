//
// @lc app=leetcode.cn id=1872 lang=java
//
// [1872] can-you-eat-your-favorite-candy-on-your-favorite-day
//
class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int l = queries.length;
        boolean[] ans = new boolean[l];
        // dp[i] 表示 第i类糖果之前的糖果数量
        long[] dp = new long[candiesCount.length + 1];
        for (int i = 0; i < candiesCount.length; i++) {
            dp[i + 1] = dp[i] + candiesCount[i];
        }
        // 一天吃一个，第 i 天吃 i + 1 个
        // 每天吃最多，第 i 天吃 i + 1 * dailyCap 个
        for (int i = 0; i < l; i++) {
            int favoriteType = queries[i][0], favoriteDay = queries[i][1], dailyCap = queries[i][2];
            ans[i] = favoriteDay + 1 > dp[favoriteType]/dailyCap  && favoriteDay < dp[favoriteType + 1];
        }
        return ans;
    }
}
// @lc code=end