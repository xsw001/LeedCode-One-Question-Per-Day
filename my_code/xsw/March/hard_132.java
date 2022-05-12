package xsw.March;
/*
132. 分割回文串 II
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

返回符合要求的 最少分割次数 。



示例 1：

输入：s = "aabbbbbb"
输出：1
解释：只需一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
示例 2：

输入：s = "a"
输出：0
示例 3：

输入：s = "ab"
输出：1


提示：

1 <= s.length <= 2000
s 仅由小写英文字母组成
 */

import java.util.ArrayList;

public class hard_132 {
    //超出时间限制
    static int res;

    public static int minCut1(String s) {
        res = s.length() - 1;
        if (s.length() < 2)
            return 0;
        dfs(s, new ArrayList<String>());
        return res;
    }

    private static void dfs(String s, ArrayList<String> path) {
        if (s.length() == 0) {
            res = Math.min(res, path.size() - 1);
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String str = s.substring(0, i);
            if (help(str)) {
                path.add(str);
                dfs(s.substring(i), path);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(minCut("abb"));
    }

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
            }
            for (int j = 0; j < i; j++) {
                if (help(s.substring(j + 1, i + 1)))
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