package xsw.April;
/*
1143. 最长公共子序列
给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：
它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。



示例 1：

输入：text1 = "abcde", text2 = "ace"
输出：3
解释：最长公共子序列是 "ace" ，它的长度为 3 。
示例 2：

输入：text1 = "abc", text2 = "abc"
输出：3
解释：最长公共子序列是 "abc" ，它的长度为 3 。
示例 3：

输入：text1 = "abc", text2 = "def"
输出：0
解释：两个字符串没有公共子序列，返回 0 。


提示：

1 <= text1.length, text2.length <= 1000
text1 和 text2 仅由小写英文字符组成。
通过次数87,621提交次数141,929
 */

public class medium_1143 {

    //思路错误
    public static int longestCommonSubsequence1(String text1, String text2) {
        int res = 0;
        int l1 = text1.length();
        int l2 = text2.length();

        int begin = 0;
        for (int i = 0; i < l2; ) {
            int flag = 0;
            for (int j = begin; j < l1; j++) {
                if (text1.charAt(j) == text2.charAt(i)) {
                    ++i;
                    ++flag;
                    begin = j;
                    ++res;
                }
                if (i > l2)
                    return res;
            }
            if (flag == 0)
                ++i;
        }

        return res;
    }

    public static void main(String[] args) {
        String text1 = "aaaaaaaasssssssss", text2 = "aaaasssss";
        System.out.println(longestCommonSubsequence(text2, text1));

    }

    /*    假设字符串text1和text2的长度分别为 m 和 n，创建 m+1 行 n+1 列的二维数组dp
            其中 dp[i][j] 表示 text1[0:i]和 text[0:j] 的最长公共子序列的长度。
            text1[0:i] 表示 text1的长度为 i 的前缀
    */
    public static int longestCommonSubsequence(String text1, String text2) {
        int l1 = text1.length();
        int l2 = text2.length();
        int[][] dp = new int[l1 + 1][l2 + 1];

        for (int i = 1; i <= l1; ++i) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= l2; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[l1][l2];
    }
}