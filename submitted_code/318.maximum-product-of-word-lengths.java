//
// @lc app=leetcode.cn id=318 lang=java
//
// [318] maximum-product-of-word-lengths
//
class Solution {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] bits = new int[n];
        for (int i = 0; i < n; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                bits[i] |= 1 << (word.charAt(j) - 'a');
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((bits[i] & bits[j]) == 0)
                    ans = Math.max(ans, words[i].length() * words[j].length());
            }
        }
        return ans;
    }
}
// @lc code=end