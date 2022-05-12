package xsw.Palindrome;
/*
132. 分割回文串 II
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是回文。

返回符合要求的 最少分割次数 。



示例 1：

输入：s = "aab"
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
import java.util.Arrays;
import java.util.List;

public class LeedCode132_分割回文串II {
    //我认输了，不对
    public static int minCut(String s) {
        if (isPalindrome(s))
            return 0;
        int res = cutNum(s);
        String longest = longestPalindrome(s);
        String[] split = s.split(longest);
        ArrayList<String> list = new ArrayList<>();
        for (String i : split) {
            if (!i.equals("")) {
                list.add(i);
            }
        }
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            res += cutNum(str);
            for (String j : str.split(longestPalindrome(str))) {
                if (!j.equals("")) {
                    list.add(j);
                }
            }
        }
        return res;
    }

    private static int cutNum(String s) {
        if(s.length() < 2)
            return 0;
        int res = 0;
        String longest = longestPalindrome(s);
        String str = s.replaceAll(longest, "#");
        for (int i = 0; i < str.length(); i++) {
            if (i == 0 || i == str.length() - 1) {
                if (str.charAt(i) == '#')
                    ++res;
            } else if (str.charAt(i) == '#')
                res += 2;
        }
        return res;
    }

    private static boolean isPalindrome(String s) {
        if (s.length() < 1)
            return true;
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            ++left;
            --right;
        }
        return true;
    }

    public static String longestPalindrome(String s) {
        int start = 0, end = -1;
        StringBuilder t = new StringBuilder("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuilder ans = new StringBuilder();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public static int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }

    public static void main(String[] args) {
        System.out.println(minCut1("cabababcbc"));
    }
    //动态规划
    public static int minCut1(String s) {
        int n = s.length();
        boolean[][] g = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(g[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                g[i][j] = s.charAt(i) == s.charAt(j) && g[i + 1][j - 1];
            }
        }

        int[] f = new int[n];
        Arrays.fill(f, Integer.MAX_VALUE);
        for (int i = 0; i < n; ++i) {
            if (g[0][i]) {
                f[i] = 0;
            } else {
                for (int j = 0; j < i; ++j) {
                    if (g[j + 1][i]) {
                        f[i] = Math.min(f[i], f[j] + 1);
                    }
                }
            }
        }

        return f[n - 1];
    }
}