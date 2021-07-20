//
// @lc app=leetcode.cn id=1508 lang=java
//
// [1508] longest-happy-prefix
//
class Solution {
    public String longestPrefix(String s) {
        int l = s.length();
        int[] next = new int[l];
        for (int i = 1, j = 0; i < l; i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = next[j - 1];
            if (s.charAt(i) == s.charAt(j))
                ++j;
            next[i] = j;
        }
        return s.substring(0, next[next.length - 1]);
    }
}
// @lc code=end