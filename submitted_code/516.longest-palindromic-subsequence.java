//
// @lc app=leetcode.cn id=516 lang=java
//
// [516] longest-palindromic-subsequence
//
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        // dp[i][j]表示s[i..j]代表的字符串的最长回文子序列
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        for (int i = n-1; i >= 0; --i) {
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j))
                    dp[i][j] = dp[i + 1][j - 1] + 2;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
            }
        }
        return dp[0][n-1];
    }
}
// @lc code=end