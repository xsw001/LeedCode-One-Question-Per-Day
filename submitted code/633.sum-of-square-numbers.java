//
// @lc app=leetcode.cn id=633 lang=java
//
// [633] sum-of-square-numbers
//
class Solution {
    public boolean judgeSquareSum(int c) {
        for (int base = 2; base * base <= c; base++) {
            // 如果不是因子，枚举下一个
            if (c % base != 0) {
                continue;
            }

            // 计算 base 的幂
            int exp = 0;
            while (c % base == 0) {
                c /= base;
                exp++;
            }

            // 根据 Sum of two squares theorem 验证
            if (base % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }

      	// 例如 11 这样的用例，由于上面的 for 循环里 base * base <= c ，base == 11 的时候不会进入循环体
      	// 因此在退出循环以后需要再做一次判断
        return c % 4 != 3;
    }
}
// @lc code=end