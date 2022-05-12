//
// @lc app=leetcode.cn id=100275 lang=java
//
// [100275] shu-zu-zhong-zhong-fu-de-shu-zi-lcof
//
class Solution {
    public int findRepeatNumber(int[] nums) {
        for (int i = 0; i < nums.length; ) {
            if (nums[i] != i) {
                if (nums[i] == nums[nums[i]])
                    return nums[i];
                int t = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i] = t;
            } else ++i;
        }
        return -1;
    }
}
// @lc code=end