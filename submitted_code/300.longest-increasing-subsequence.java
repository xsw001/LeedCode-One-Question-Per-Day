//
// @lc app=leetcode.cn id=300 lang=java
//
// [300] longest-increasing-subsequence
//
class Solution {
    public int lengthOfLIS(int[] nums) {
        int l = nums.length;
        if(l < 2)
            return 1;
        int[] dp = new int[l];
        Arrays.fill(dp,1);
        for(int i=1;i<l;++i){
            for(int j=0;j<i;++j){
                if(nums[i] > nums[j])
                    dp[i] = Math.max(dp[i],dp[j]+1);
            }
        }
        //System.out.println(Arrays.toString(dp));
        int res = 0;
        for(int i : dp)
            res = Math.max(res, i);
        return res;
    }
}
// @lc code=end