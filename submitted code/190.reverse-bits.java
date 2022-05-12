//
// @lc app=leetcode.cn id=190 lang=java
//
// [190] reverse-bits
//
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0;
        int i = 0;
        while(i < 32 && n != 0){
            res |= (n & 1) << (31 - i);
            n >>= 1;
            ++i;
        }
        return res;
    }
}
// @lc code=end