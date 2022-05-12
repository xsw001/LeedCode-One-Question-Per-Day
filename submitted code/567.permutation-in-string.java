//
// @lc app=leetcode.cn id=567 lang=java
//
// [567] permutation-in-string
//
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int[] freq = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            freq[s1.charAt(i)-'a']++;
        }
        int left = 0, right = 0;
        while (right < s2.length()){
            if(freq[s2.charAt(right)-'a'] != 0){
                freq[s2.charAt(right)-'a']--;
                ++right;
            }else{
                freq[s2.charAt(left)-'a']++;
                ++left;
            }
            if(right-left == s1.length())
                return true;
        }
        return false;
    }
}
// @lc code=end