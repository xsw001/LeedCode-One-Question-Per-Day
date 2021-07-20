//
// @lc app=leetcode.cn id=338 lang=java
//
// [338] counting-bits
//
class Solution {
    public static int[] countBits(int num) {
        int[] res = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            if (i % 2 == 1) {
                res[i] = res[i / 2] + 1;
            } else
                res[i] = res[i / 2];
        }
        return res;
    }
}
// @lc code=end