//
// @lc app=leetcode.cn id=1138 lang=java
//
// [1138] grumpy-bookstore-owner
//
class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int length = grumpy.length;
        int total = 0;
        for (int i = 0; i < length; i++) {
            if (i < X) {
                total += customers[i];
            } else if (grumpy[i] == 0)
                total += customers[i];
        }
        int res = Math.max(total, customers[0]);
        int left = 0, right = X;
        while (right < length) {
            if (grumpy[right] == 1) {
                total += customers[right];
            }
            if (right - left > X - 1) {
                if (grumpy[left] == 1) {
                    total -= customers[left];
                }
                ++left;
            }
            ++right;
            res = Math.max(total, res);
        }
        return Math.max(total, res);
    }
}
// @lc code=end