//
// @lc app=leetcode.cn id=70 lang=java
//
// [70] climbing-stairs
//
class Solution {
    public int climbStairs(int n) {
        int[] step = {1,2};
        int[] dp = new int[n+1];
        dp[0] = 1;
        for(int i=1; i<=n; ++i){
            for(int j= 0; j < 2;++j){
                if(i >= step[j])
                    dp[i] += dp[i-step[j]];
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }
}
// @lc code=end