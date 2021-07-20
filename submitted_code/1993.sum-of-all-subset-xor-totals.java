//
// @lc app=leetcode.cn id=1993 lang=java
//
// [1993] sum-of-all-subset-xor-totals
//
class Solution {
    public static int subsetXORSum(int[] nums) {
        int n = nums.length;
        int res = 0;
        for (int mask = 0; mask < (1 << n); ++mask) {
            int temp = 0;
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    temp ^= nums[i];
                }
            }
            res += temp;
        }
        return res;
    }
}
// @lc code=end