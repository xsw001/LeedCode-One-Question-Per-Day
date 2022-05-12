//
// @lc app=leetcode.cn id=377 lang=java
//
// [377] combination-sum-iv
//
class Solution {
    public static int combinationSum4(int[] nums, int target) {
        if (target == 0) {
            return 1;
        }
        int res = 0;
        for (int num : nums) {
            if (target >= num) {
                res += combinationSum4(nums, target - num);
            }
        }
        return res;
    }
}
// @lc code=end