//
// @lc app=leetcode.cn id=2227 lang=java
//
// [2227] sum-of-subarray-ranges
//
class Solution {
    public long subArrayRanges(int[] nums) {
        long res = 0;
        for (int i = 0; i < nums.length; ++i) {
            int minN = nums[i];
            int maxN = nums[i];
            for (int j = i + 1; j < nums.length; ++j) {
                maxN = Math.max(maxN, nums[j]);
                minN = Math.min(minN, nums[j]);
                res += (maxN - minN);
            }
        }
        return res;
    }
}
// @lc code=end