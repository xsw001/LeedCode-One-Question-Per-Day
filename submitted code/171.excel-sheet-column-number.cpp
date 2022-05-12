//
// @lc app=leetcode.cn id=171 lang=cpp
//
// [171] excel-sheet-column-number
//
class Solution {
public:
    int titleToNumber(string s) {
        int ans = 0;
        for(int i=0;i<s.size();i++) {
            int num = s[i] - 'A' + 1;
            ans = ans * 26 + num;
        }
        return ans;
    }
};
// @lc code=end