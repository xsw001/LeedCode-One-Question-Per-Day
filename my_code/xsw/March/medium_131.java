package xsw.March;
/*
给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。

返回 s 所有可能的分割方案。

示例:

输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
"aabb"
[
    ["a","a","b","b"],
    ["a","a","bb"],
    ["aa","b","b"],
    ["aa","bb"]
]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class medium_131 {

    static boolean[][] f;
    static List<List<String>> ret = new ArrayList<List<String>>();
    static List<String> ans = new ArrayList<String>();
    static int n;

    public static List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public static void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().partition("aab"));
    }

}

class Solution {
    List<List<String>> res = new ArrayList<List<String>>();
    int n;

    public List<List<String>> partition(String s) {
        n = s.length();
        if (n == 0)
            return res;
        dfs(s, new ArrayList<String>());
        return res;
    }

    public void dfs(String s, List<String> path) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = 1; i <= s.length(); ++i) {
            String str = s.substring(0, i);
            if (isString(str)) {
                path.add(str);
                dfs(s.substring(i), path);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean isString(String s) {
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