//
// @lc app=leetcode.cn id=58 lang=java
//
// [58] length-of-last-word
//
class Solution {
    public int lengthOfLastWord(String s) {
        int l = s.length();
        int i = l-1;
        while(s.charAt(i) == ' ')
            --i;
        int ans = 0;
        for(; i >= 0; --i){
            if(s.charAt(i) == ' ')
                break;
            ++ans;
        }
        return ans;
    }
}
// @lc code=end