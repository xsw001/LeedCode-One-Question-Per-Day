//
// @lc app=leetcode.cn id=643 lang=java
//
// [643] maximum-average-subarray-i
//
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int l = nums.length;
        double all = 0;
        for (int i = 0; i < k; ++i) {
            all += nums[i];
        }
        double result = all;
        for (int i = k; i < l; ++i) {
            all = all-nums[i-k]+nums[i];
            result = Math.max(result,all);
        }
        return result/k;
    }
}
// @lc code=end