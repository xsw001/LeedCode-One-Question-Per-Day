//
// @lc app=leetcode.cn id=231 lang=java
//
// [231] power-of-two
//
class Solution {
    public boolean isPowerOfTwo(int n) {
        if(n>=0)
        return Integer.bitCount(n)==1;
        else
        return false;
    }
}
// @lc code=end