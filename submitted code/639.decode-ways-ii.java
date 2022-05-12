//
// @lc app=leetcode.cn id=639 lang=java
//
// [639] decode-ways-ii
//
class Solution {
    static final int MOD = 1000000007;

    public static int numDecodings(String s) {
        int l = s.length();
        long[] dp = new long[l + 1];
        dp[0] = 1;
        for (int i = 1; i < l + 1; i++) {
            dp[i] = (dp[i - 1] * oneDigit(s.charAt(i - 1))) % MOD;
            if (i > 1) {
                dp[i] = (dp[i] + dp[i - 2] * twoDigit(s.charAt(i - 1), s.charAt(i - 2))) % MOD;
            }
        }
        return (int)dp[l];
    }

    private static int twoDigit(char c1, char c2) {
        if (c1 == '*' && c2 == '*')
            return 15;
        else if (c1 == '*') {
            if (c2 == '1')
                return 9;
            return c2 == '2' ? 6 : 0;
        }else if (c2 == '*') {
            if(c1 < '7' && c1 >= '0')
                return 2;
            return 1;
        }else{
            if (c2 != '0' && (c2 - '0') * 10 + (c1 - '0') < 27)
                return 1;
        }
        return 0;
    }

    private static int oneDigit(char c) {
        if (c == '0')
            return 0;
        return c == '*' ? 9 : 1;
    }
}

// @lc code=end