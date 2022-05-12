//
// @lc app=leetcode.cn id=191 lang=java
//
// [191] number-of-1-bits
//
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            ++res;
            n &= n - 1;
        }
        return res;
    }
}
// @lc code=end