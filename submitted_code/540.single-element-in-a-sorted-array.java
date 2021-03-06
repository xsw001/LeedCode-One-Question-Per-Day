//
// @lc app=leetcode.cn id=540 lang=java
//
// [540] single-element-in-a-sorted-array
//
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (m % 2 == 0) {
                if (nums[m] == nums[m + 1]) // 正常的
                    l = m + 1;
                else
                    r = m;
            } else {
                if (nums[m] == nums[m - 1]) // 正常的
                    l = m + 1;
                else
                    r = m;
            }
        }
        return nums[l];
    }
}
// @lc code=end