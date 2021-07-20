//
// @lc app=leetcode.cn id=205 lang=java
//
// [205] isomorphic-strings
//
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length())
            return false;

        for (int index = 0; index <= s.length() - 1; index++)
        {
            if (s.indexOf(s.charAt(index)) != t.indexOf(t.charAt(index)))
            {
                return false;
            }
        }
        return true;
    }
}
// @lc code=end