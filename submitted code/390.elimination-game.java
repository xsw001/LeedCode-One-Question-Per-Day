//
// @lc app=leetcode.cn id=390 lang=java
//
// [390] elimination-game
//
class Solution {
    public int lastRemaining(int n) {
        int a = 1; // 首项
        int d = 1; // 公差
        boolean l2r = true; // 从左向右删除
        while (n > 1) { // 长度
            if (l2r || n % 2 != 0)
                a += d;
            l2r = !l2r;
            n /= 2;
            d *= 2;
        }
        return a;
    }
}
// @lc code=end