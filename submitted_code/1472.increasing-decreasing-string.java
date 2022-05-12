//
// @lc app=leetcode.cn id=1472 lang=java
//
// [1472] increasing-decreasing-string
//
class Solution {
    public String sortString(String s) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++arr[s.charAt(i) - 'a'];
        }
        StringBuilder result = new StringBuilder();
        while (result.length() != s.length()) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] != 0) {
                    result.append((char) ('a' + i));
                    --arr[i];
                }
            }
            if (result.length() == s.length())
                return result.toString();
            for (int j = arr.length - 1; j >= 0; j--) {
                if (arr[j] != 0) {
                    result.append((char) ('a' + j));
                    --arr[j];
                }
            }
        }
        return result.toString();
    }
}
// @lc code=end