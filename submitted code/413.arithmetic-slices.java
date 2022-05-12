//
// @lc app=leetcode.cn id=413 lang=java
//
// [413] arithmetic-slices
//
class Solution {
    public static int numberOfArithmeticSlices(int[] nums) {
        int n = nums.length;
        if (n < 3)
            return 0;
        int ans = 0;
        int a = 0, b = 1, c = 2;
        while (c < n) {
            if (isArithmeticSlices(nums[a], nums[b], nums[c])) {
                ++ans;
                int diff = nums[c] - nums[b];
                while (c < n - 1 && nums[c + 1] - nums[c] == diff) {
                    ++c;
                    ++ans;
                }
            }
            ++a;
            ++b;
            c = b + 1;
        }
        return ans;
    }

    private static boolean isArithmeticSlices(int a, int b, int c) {
        return c - b == b - a;
    }
}
// @lc code=end