//
// @lc app=leetcode.cn id=268 lang=java
//
// [268] missing-number
//
class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        int l =0 , r = n-1;
        for(; l<r;++l,--r)
            sum += nums[l] + nums[r];
        return (1+n)*n/2 - (l == r ? sum + nums[l] : sum);
    }
}
// @lc code=end