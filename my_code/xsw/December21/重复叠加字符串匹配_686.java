package xsw.December21;
/*
686. 重复叠加字符串匹配
给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。

注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。



示例 1：

输入：a = "abcd", b = "cdabcdab"
输出：3
解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
示例 2：

输入：a = "a", b = "aa"
输出：2
示例 3：

输入：a = "a", b = "a"
输出：1
示例 4：

输入：a = "abc", b = "wxyz"
输出：-1


提示：

1 <= a.length <= 104
1 <= b.length <= 104
a 和 b 由小写英文字母组成
通过次数20,517提交次数55,654
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class 重复叠加字符串匹配_686 {

    public int repeatedStringMatch1(String a, String b) {
        char c = b.charAt(0);
        ArrayList<Integer> index = new ArrayList<>();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == c)
                index.add(i);
        }
        if (index.isEmpty())
            return -1;
        for (Integer start : index) {
            int i = 0;
            int ans = 1;
            while (i < b.length() && b.charAt(i) == a.charAt(start)) {
                ++i;
                start = ++start % a.length();
                if (start == 0 && i < b.length()) {
                    ++ans;
                }
            }
            if (i == b.length())
                return ans;
        }
        return -1;
    }

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
//        System.out.println(repeatedStringMatch("abcd",
//                "cdabcdab"));
//        System.out.println(kmp("abcdabcdabcd",
//                "cdabcdoab"));
        System.out.println(new Solution().repeatedStringMatch("abcdc",
                "cabcdcab"));
    }

    public int repeatedStringMatch(String a, String b) {
        int ans = 1;
        int m = a.length(), n = b.length();
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        while (sb.length() < n) {
            sb.append(a);
            ++ans;
        }
        sb.append(a);
        int idx = kmp(sb.toString(), b);
        if (idx == -1) return -1;
        return idx + b.length() > a.length() * ans ? ans + 1 : ans;
    }

    public int kmp(String haystack, String needle) {
        int m = haystack.length(), n = needle.length();
        if (n > m)
            return -1;
        if (n == 0)
            return 0;
        int[] next = new int[n];
        for (int i = 1, j = 0; i < n; ++i) {
            while (j > 0 && needle.charAt(i) != needle.charAt(j))
                j = next[j - 1];
            if (needle.charAt(j) == needle.charAt(i))
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

    class Solution {
        static final int kMod1 = 1000000007;
        static final int kMod2 = 1337;

        public int repeatedStringMatch(String a, String b) {
            int an = a.length(), bn = b.length();
            int index = strStr(a, b);
            if (index == -1) {
                return -1;
            }
            if (an - index >= bn) {
                return 1;
            }
            return (bn + index - an - 1) / an + 2;
        }

        public int strStr(String haystack, String needle) {
            int n = haystack.length(), m = needle.length();
            if (m == 0) {
                return 0;
            }

            int k1 = 1000000009;
            int k2 = 1337;
            Random random = new Random();
            int kMod1 = random.nextInt(k1) + k1;
            int kMod2 = random.nextInt(k2) + k2;

            long hashNeedle = 0;
            for (int i = 0; i < m; i++) {
                char c = needle.charAt(i);
                hashNeedle = (hashNeedle * kMod2 + c) % kMod1;
            }
            long hashHaystack = 0, extra = 1;
            for (int i = 0; i < m - 1; i++) {
                hashHaystack = (hashHaystack * kMod2 + haystack.charAt(i % n)) % kMod1;
                extra = (extra * kMod2) % kMod1;
            }
            for (int i = m - 1; (i - m + 1) < n; i++) {
                hashHaystack = (hashHaystack * kMod2 + haystack.charAt(i % n)) % kMod1;
                if (hashHaystack == hashNeedle) {
                    return i - m + 1;
                }
                hashHaystack = (hashHaystack - extra * haystack.charAt((i - m + 1) % n)) % kMod1;
                hashHaystack = (hashHaystack + kMod1) % kMod1;
            }
            return -1;
        }
    }
}