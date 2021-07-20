//
// @lc app=leetcode.cn id=5 lang=java
//
// [5] longest-palindromic-substring
//
class Solution {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        String res = "";
        for (int j = 0; j < s.length(); j++) {
            for (int i = 0; i <= j; i++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    if(j-i >= res.length())
                        res = s.substring(i,j+1);
                }
            }
        }
        return res;
    }
}
// @lc code=end