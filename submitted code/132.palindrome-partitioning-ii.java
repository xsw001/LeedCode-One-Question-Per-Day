//
// @lc app=leetcode.cn id=132 lang=java
//
// [132] palindrome-partitioning-ii
//
class Solution {
public static int minCut(String s) {
        if (s.length() < 2)
            return 0;
        int[] dp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i] = i;
        }
        for (int i = 1; i < s.length(); i++) {
            if (help(s.substring(0, i + 1))) {
                dp[i] = 0;
                continue;
            }
            for (int j = 0; j < i; j++) {
                if (help(s.substring(j + 1,i+1)))
                    dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }
        return dp[s.length() - 1];
    }

    private static boolean help(String s) {
        int l = s.length();
        if (l < 1) {
            return true;
        }
        int left = 0, right = l - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            ++left;
            --right;
        }
        return true;
    }
}
// @lc code=end