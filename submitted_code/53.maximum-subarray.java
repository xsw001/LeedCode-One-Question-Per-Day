//
// @lc app=leetcode.cn id=53 lang=java
//
// [53] maximum-subarray
//
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1)
            return nums[0];
        int l = nums.length, max = nums[0], pre = nums[0];
        for (int i = 1; i < l; i++) {
            if(pre > 0)
                pre += nums[i];
            else
                pre = nums[i];
            max = Math.max(max,pre);
        }
        return max;
    }
}
// @lc code=end