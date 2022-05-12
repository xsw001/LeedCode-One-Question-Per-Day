//
// @lc app=leetcode.cn id=9 lang=java
//
// [9] palindrome-number
//
class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        int y = 0,t = x;
        while (x != 0) {
            if (y > 214748364 || y < -214748364)
                return false;
            y = y * 10 + x % 10;
            x = x / 10;
        }
        return t==y;
    }
}
// @lc code=end