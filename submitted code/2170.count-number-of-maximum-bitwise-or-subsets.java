//
// @lc app=leetcode.cn id=2170 lang=java
//
// [2170] count-number-of-maximum-bitwise-or-subsets
//
class Solution {

    public int countMaxOrSubsets(int[] nums) {
       int mask = 1 << nums.length;
        int max = 0, count = 0;
        for (int i = 0; i < mask; i++) {
            int val = 0;
            for (int j = 0; j < nums.length; j++) {
                if (((i >> j) & 1) == 1)
                    val |= nums[j];
            }
            if (val > max) {
                max = val;
                count = 1;
            } else if (val == max)
                ++count;
        }

        return count;
    }
}
// @lc code=end