//
// @lc app=leetcode.cn id=1967 lang=java
//
// [1967] longest-substring-of-all-vowels-in-order
//
class Solution {
    public int longestBeautifulSubstring(String word) {
        int inf = 500000;
        int A = -inf, B = -inf, C = -inf, D = -inf, E = -inf, ans = 0;
        char[] chars = word.toCharArray();
        for (char c : chars) {
            if (c == 'a') {
                A = Math.max(A + 1, 1);
                B = C = D = E = -inf;
            } else if (c == 'e') {
                B = Math.max(A + 1, B + 1);
                A = C = D = E = -inf;
            } else if (c == 'i') {
                C = Math.max(B + 1, C + 1);
                A = B = D = E = -inf;
            } else if (c == 'o') {
                D = Math.max(D + 1, C + 1);
                A = B = C = E = -inf;
            } else if (c == 'u') {
                E = Math.max(E + 1, D + 1);
                A = B = C = D = -inf;
            }
            ans = Math.max(ans, E);
        }
        return ans;
    }
}
// @lc code=end