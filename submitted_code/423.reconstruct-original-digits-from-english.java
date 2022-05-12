//
// @lc app=leetcode.cn id=423 lang=java
//
// [423] reconstruct-original-digits-from-english
//
class Solution {
    public String originalDigits(String s) {
        int[] words = new int[26];
        int[] ans = new int[10];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == 'z') {
                ans[0]++;
                for (int j = 0; j < 4; j++) {
                    words["zero".charAt(j) - 'a']--;
                }
            } else if (c == 'w') {
                ans[2]++;
                for (int j = 0; j < 3; j++) {
                    words["two".charAt(j) - 'a']--;
                }
            } else if (c == 'u') {
                ans[4]++;
                for (int j = 0; j < 4; j++) {
                    words["four".charAt(j) - 'a']--;
                }
            } else if (c == 'x') {
                ans[6]++;
                for (int j = 0; j < 3; j++) {
                    words["six".charAt(j) - 'a']--;
                }
            } else if (c == 'g') {
                ans[8]++;
                for (int j = 0; j < 5; j++) {
                    words["eight".charAt(j) - 'a']--;
                }
            }
            words[c - 'a']++;
        }
        ans[1] += words['o' - 'a'];
        ans[3] += words['t' - 'a'];
        ans[5] += words['f' - 'a'];
        words['i' - 'a'] -= words['f' - 'a'];
        ans[7] += words['s' - 'a'];
        ans[9] += words['i' - 'a'];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            if (ans[i] > 0) {
                sb.append(("" + i).repeat(ans[i]));
            }
        }
        return sb.toString();
    }
}
// @lc code=end