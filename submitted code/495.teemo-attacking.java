//
// @lc app=leetcode.cn id=495 lang=java
//
// [495] teemo-attacking
//
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;
        int l = timeSeries.length;
        for(int i = 0; i < l-1; ++i){
            if(timeSeries[i] + duration - 1 < timeSeries[i+1])
                ans += duration;
            else{
                ans += timeSeries[i+1] - timeSeries[i];
            }
        }
        return ans + duration;
    }
}
// @lc code=end