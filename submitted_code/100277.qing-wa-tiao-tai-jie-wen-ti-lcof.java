//
// @lc app=leetcode.cn id=100277 lang=java
//
// [100277] qing-wa-tiao-tai-jie-wen-ti-lcof
//
class Solution {
    public int numWays(int n) {
        int[] ans = new int[101];
        ans[1] = 1;
        ans[0] = 1;
        for(int i=2;i<=n;++i){
            ans[i] = (ans[i-1] + ans[i-2]) % 1000000007;
        }
        return ans[n];
    }
}
// @lc code=end