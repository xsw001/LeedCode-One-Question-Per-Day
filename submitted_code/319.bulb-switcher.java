//
// @lc app=leetcode.cn id=319 lang=java
//
// [319] bulb-switcher
//
class Solution {
    public int bulbSwitch(int n) {
        int l = 0, r = 31622;
        while (l < r) {
            int m = (l + r) / 2;
            if (m * (m + 2) < n)
                l = m + 1;
            else
            r = m;
        }
        return l;
    }
}
// @lc code=end