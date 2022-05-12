//
// @lc app=leetcode.cn id=91 lang=java
//
// [91] decode-ways
//
class Solution {
    public int numDecodings(String s) {
        int n = s.length();
        int a=0,b=1,c=0;
        for (int i = 1; i <= n; ++i) {
            a = 0;
            if (s.charAt(i - 1) != '0') {
                a += b;
            }
            if (i > 1 && s.charAt(i - 2) != '0' && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                a += c;
            }
            c = b;
            b = a;
        }
        return a;
    }
}
// @lc code=end