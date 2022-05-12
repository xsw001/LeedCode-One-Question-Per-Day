//
// @lc app=leetcode.cn id=171 lang=java
//
// [171] excel-sheet-column-number
//
class Solution {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for(int i : columnTitle.toCharArray()){
            ans = ans * 26 + (i-'A'+1);
        }
        return ans;
    }
}
// @lc code=end