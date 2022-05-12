//
// @lc app=leetcode.cn id=198 lang=java
//
// [198] house-robber
//
class Solution {
    public int rob(int[] nums) {
        if(nums.length < 2)
            return nums[0];
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
// @lc code=end