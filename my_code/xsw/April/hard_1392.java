package xsw.April;

import java.util.Arrays;

/*
* 1392. 最长快乐前缀
「快乐前缀」是在原字符串中既是 非空 前缀也是后缀（不包括原字符串自身）的字符串。

给你一个字符串 s，请你返回它的 最长快乐前缀。

如果不存在满足题意的前缀，则返回一个空字符串。



示例 1：

输入：s = "level"
输出："l"
解释：不包括 s 自己，一共有 4 个前缀（"l", "le", "lev", "leve"）和 4 个后缀（"l", "el", "vel", "evel"）。最长的既是前缀也是后缀的字符串是 "l" 。
示例 2：

输入：s = "ababab"
输出："abab"
解释："abab" 是最长的既是前缀也是后缀的字符串。题目允许前后缀在原字符串中重叠。
示例 3：

输入：s = "leetcodeleet"
输出："leet"
示例 4：

输入：s = "a"
输出：""


提示：

1 <= s.length <= 10^5
s 只含有小写英文字母
通过次数5,994提交次数15,656*/
public class hard_1392 {

    public static String longestPrefix1(String s) {
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

    public static void main(String[] args) {
        String s = "acccbaaacccbaac";
        System.out.println(longestPrefix(s));
    }

    public static String longestPrefix(String s) {
        int n = s.length();
        int[] fail = new int[n];
        Arrays.fill(fail, -1);
        for (int i = 1; i < n; ++i) {
            int j = fail[i - 1];
            while (j != -1 && s.charAt(j + 1) != s.charAt(i)) {
                j = fail[j];
            }
            if (s.charAt(j + 1) == s.charAt(i)) {
                fail[i] = j + 1;
            }
        }
        return s.substring(0, fail[n - 1] + 1);
    }
}
