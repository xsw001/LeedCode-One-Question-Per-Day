//
// @lc app=leetcode.cn id=136 lang=java
//
// [136] single-number
//
class Solution {
    public int singleNumber(int[] nums) {
        int res = nums[0];
        for(int i=1;i<nums.length;++i)
            res ^= nums[i];
        return res;
    }
}
// @lc code=end