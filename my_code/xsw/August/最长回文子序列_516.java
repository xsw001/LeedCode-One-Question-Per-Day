package xsw.August;
/*
516. 最长回文子序列
给你一个字符串 s ，找出其中最长的回文子序列，并返回该序列的长度。

子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。



示例 1：

输入：s = "bbbab"
输出：4
解释：一个可能的最长回文子序列为 "bbbb" 。
示例 2：

输入：s = "cbbd"
输出：2
解释：一个可能的最长回文子序列为 "bb" 。


提示：

1 <= s.length <= 1000
s 仅由小写英文字母组成
通过次数65,643提交次数103,006
请问您在哪类招聘中遇到此题？
 */

import java.util.ArrayList;
import java.util.Arrays;

public class 最长回文子序列_516 {

    // 看错题了  是回文串
    public static int xxx(String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        int ans = 0;
        for (int count : counts) {
            ans = Math.max(count, ans);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(longestPalindromeSubseq("asdygfdsjhgweriuefhbsdasaguasuigh"));
    }

    /*
    **思路：** dp[i][j]表示s[i..j]代表的字符串的最长回文子序列
    d[i][i]=1
    dp[i][j] = dp[i+1][j-1]+2 当s[i]=s[j]
    dp[i][j]=max(dp[i+1][j],dp[i][j-1])
        * 当s[i]!=s[j] 取s[i+1..j] 和s[i..j-1]中最长的
        * 由于dp[i][j]需要dp[i+1][j]所以需要逆序枚举s的长度
        * 而又因为j是递增的，所以在求解dp[i][j]时,dp[i][j-1]肯定已经求解过了
     */
    public static int longestPalindromeSubseq(String s) {
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
        // System.out.println(Arrays.deepToString(dp));
        return dp[0][n-1];
    }
}