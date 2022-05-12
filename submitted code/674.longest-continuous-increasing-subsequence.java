//
// @lc app=leetcode.cn id=674 lang=java
//
// [674] longest-continuous-increasing-subsequence
//
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if(nums.length < 2)
            return nums.length;
        int len = 1;
        for (int i = 0; i < nums.length-1; i++) {
            int temp = 1;
            while(i < nums.length-1 && nums[i] < nums[i+1]) {
                ++temp;
                ++i;
            }
            len = Math.max(len,temp);
        }
        return len;
    }
}
// @lc code=end