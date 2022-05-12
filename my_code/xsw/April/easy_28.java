package xsw.April;
/*
28. 实现 strStr()
实现 strStr() 函数。

给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回  -1 。



说明：

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与 C 语言的 strstr() 以及 Java 的 indexOf() 定义相符。



示例 1：

输入：haystack = "hello", needle = "ll"
输出：2
示例 2：

输入：haystack = "aaaaa", needle = "bba"
输出：-1
示例 3：

输入：haystack = "", needle = ""
输出：0


提示：

0 <= haystack.length, needle.length <= 5 * 104
haystack 和 needle 仅由小写英文字符组成
通过次数334,734提交次数840,176
 */

import java.util.ArrayList;

public class easy_28 {
    /*执行用时：2616 ms, 在所有 Java 提交中击败了5.01%的用户*/
    public static int strStr1(String haystack, String needle) {
        int hl = haystack.length();
        int nl = needle.length();
        if (nl > hl)
            return -1;
        if (hl == 0 || nl == 0)
            return 0;
        int h = 0, n = 0;
        while (h < hl) {
            if (haystack.charAt(h) == needle.charAt(n)) {
                int t = h;
                while (t < hl && n < nl && haystack.charAt(t) == needle.charAt(n)) {
                    ++t;
                    ++n;
                }
                if (n == nl)
                    return h;
                n = 0;
            }
            ++h;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};
        ArrayList<String> list = new ArrayList<>();

        System.out.println(strStr("ababcababf", "ababf"));

    }

    /*执行用时：3 ms, 在所有 Java 提交中击败了39.16%的用户*/
    //还是暴力，测试用力的问题
    public static int strStr2(String haystack, String needle) {
        int hl = haystack.length();
        int nl = needle.length();
        if (nl > hl)
            return -1;
        if (hl == 0 || nl == 0)
            return 0;
        int h = 0, n = 0;
        while (h < hl) {
            if (haystack.charAt(h) == needle.charAt(n)) {
                while (h < hl && n < nl && haystack.charAt(h) == needle.charAt(n)) {
                    ++h;
                    ++n;
                }
                if (n == nl)
                    return h - nl;
            } else {
                h = h - n + 1;
                n = 0;
            }
        }
        return -1;
    }

    /*执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
内存消耗：36.7 MB, 在所有 Java 提交中击败了97.36%的用户*/
    public static int strStr3(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    // KMP 算法
    // ss: 原串(string)  pp: 匹配串(pattern)
    public static int strStr(String ss, String pp) {
        if (pp.isEmpty()) return 0;

        // 分别读取原串和匹配串的长度
        int n = ss.length(), m = pp.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        ss = " " + ss;
        pp = " " + pp;

        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && p[i] != p[j + 1])
                j = next[j];
            // 匹配成功的话，先让 j++
            if (p[i] == p[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }

        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && s[i] != p[j + 1])
                j = next[j];//为什么要前一个字符的前缀表的数值呢，因为要找前面字符串的最长相同的前缀和后缀
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (s[i] == p[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }

        return -1;
    }

    // sunday算法
    public int strStr5(String haystack, String needle) {
        // 各种边界情况
        if (haystack != null && haystack.equals(needle)) {
            return 0;
        }
        if (haystack == null || haystack.length() == 0) {
            return -1;
        }
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        int M = haystack.length();
        int N = needle.length();
        for (int i = 0; i <= M - N; i++) {
            int j = 0;
            while (j < N && needle.charAt(j) == haystack.charAt(i + j)) {
                j++;
            }
            if (j == N) {
                return i;
            } else {
                // 前面都属于暴力匹配部分，下面代码在字符串needle中，从后往前寻找第一个与字符haystack.charAt(i + N)相等的字符，
                // 从而获得下一个合适的i，目的是尽量增加每一次的移动步数。
                int k = N - 1;
                while (k >= 0 && i + N < M && needle.charAt(k) != haystack.charAt(i + N)) {
                    k--;
                }
                if (k < 0) {
                    i = i + N;
                } else {
                    i = i + N - 1 - k;
                }
            }
        }
        return -1;
    }

    // 2021-7-26   再次学习KMP，默写
    public int strStr_kmp(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        if (n > m)
            return -1;
        if (n == 0)
            return 0;
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; ++i) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j))
                j = next[j - 1];
            if (needle.charAt(i) == needle.charAt(j))
                ++j;
            next[i] = j;
        }

        for (int i = 0, j = 0; i < m; ++i) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j))
                j = next[j - 1];
            if (haystack.charAt(i) == needle.charAt(j))
                ++j;
            if (j == n)
                return i - n + 1;
        }
        return -1;
    }
}