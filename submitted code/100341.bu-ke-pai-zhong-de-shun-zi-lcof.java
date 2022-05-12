//
// @lc app=leetcode.cn id=100341 lang=java
//
// [100341] bu-ke-pai-zhong-de-shun-zi-lcof
//
class Solution {
    public boolean isStraight(int[] nums) {
        Arrays.sort(nums);
        int zero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0)
                ++zero;
            else if (i < nums.length - 1) {
                if (nums[i + 1] == nums[i])
                    return false;
                zero -= nums[i + 1] - nums[i] - 1;
            }
        }
        return zero >= 0;
    }
}
// @lc code=end