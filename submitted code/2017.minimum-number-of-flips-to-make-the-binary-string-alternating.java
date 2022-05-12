//
// @lc app=leetcode.cn id=2017 lang=java
//
// [2017] minimum-number-of-flips-to-make-the-binary-string-alternating
//
class Solution {
    public int minFlips(String s) {
        String target = "01";
        int count = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != target.charAt(i % 2)) {
                count++;
            }
        }
        int ans = Math.min(count, len - count);
        s += s;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) != target.charAt(i % 2)) {
                count--;
            }
            if (s.charAt(i + len) != target.charAt((i + len) % 2)) {
                count++;
            }
            ans = Math.min(ans, Math.min(count, len - count));
        }
        return ans;
    }
}
// @lc code=end