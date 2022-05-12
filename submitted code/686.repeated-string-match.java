//
// @lc app=leetcode.cn id=686 lang=java
//
// [686] repeated-string-match
//
class Solution {
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
        int idx = sb.indexOf(b);
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
// @lc code=end