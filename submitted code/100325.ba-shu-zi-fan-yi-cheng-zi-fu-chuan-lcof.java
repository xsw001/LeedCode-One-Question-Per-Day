//
// @lc app=leetcode.cn id=100325 lang=java
//
// [100325] ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof
//
class Solution {
    public int translateNum(int num) {
        if (num < 10)
            return 1;
        if (num < 26)
            return 2;
        String s = String.valueOf(num);
        int[] dp = new int[s.length()];
        dp[0] = 1;
        dp[1] = Integer.parseInt(s.substring(0, 2)) < 26 ? 2 : 1;
        for (int i = 2; i < s.length(); i++) {
            int n = Integer.parseInt(s.substring(i - 1, i + 1));
            if (n > 9 && n < 26)
                dp[i] = dp[i - 1] + dp[i - 2];
            else
                dp[i] = dp[i - 1];
        }
        return dp[s.length() - 1];
    }
}
// @lc code=end