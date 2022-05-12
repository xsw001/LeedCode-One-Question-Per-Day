package xsw.Palindrome;
/*
给你一个字符串 s，找到 s 中最长的回文子串。

示例 1：

输入：s = "babad"
输出："bab"
解释："aba" 同样是符合题意的答案。
示例 2：

输入：s = "cbbd"
输出："bb"
示例 3：

输入：s = "a"
输出："a"
示例 4：

输入：s = "ac"
输出："a"
 

提示：

1 <= s.length <= 1000
s 仅由数字和英文字母（大写和/或小写）组成
 */

public class LeedCode5_最长回文子串 {

    public static String longestPalindrome(String s) {
        String res = "";
        int len = 0;
        for (int i = 0; i < 2 * s.length() - 1; i++) {
            int left = i / 2;
            int right = left + i % 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left >= res.length()) {
                    res = s.substring(left, right+1);
                }
                --left;
                ++right;
            }
            if(left < 0 && right == s.length())
                return s;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome1("babad"));
    }

    public static String longestPalindrome1(String s) {
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