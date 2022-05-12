//
// @lc app=leetcode.cn id=100330 lang=java
//
// [100330] zuo-xuan-zhuan-zi-fu-chuan-lcof
//
class Solution {
    public String reverseLeftWords(String s, int n) {
        return s.substring(n,s.length()) + s.substring(0,n);
    }
}
// @lc code=end