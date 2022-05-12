//
// @lc app=leetcode.cn id=242 lang=java
//
// [242] valid-anagram
//
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] letter = new int[26];
        if(t.length() != s.length())
            return false;
        for (int i = 0; i < t.length(); i++) {
            ++letter[t.charAt(i)-'a'];
            --letter[s.charAt(i)-'a'];
        }
        for (int i : letter) {
            if(i!=0)
                return false;
        }
        return true;
    }
}
// @lc code=end