//
// @lc app=leetcode.cn id=1603 lang=java
//
// [1603] running-sum-of-1d-array
//
class Solution {
    public int[] runningSum(int[] nums) {
        int start = nums[0];
        for(int i = 1; i< nums.length;++i){
            nums[i] = nums[i]+start;
            start = nums[i];
        }
        return nums;
    }
}
// @lc code=end