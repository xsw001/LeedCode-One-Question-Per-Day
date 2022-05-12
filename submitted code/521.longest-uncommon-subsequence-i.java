//
// @lc app=leetcode.cn id=521 lang=java
//
// [521] longest-uncommon-subsequence-i
//
class Solution {
    public int findLUSlength(String a, String b) {
        return !a.equals(b) ? Math.max(a.length(), b.length()) : -1;
    }
}
// @lc code=end