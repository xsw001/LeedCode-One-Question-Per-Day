//
// @lc app=leetcode.cn id=258 lang=cpp
//
// [258] add-digits
//
class Solution {
public:
    int addDigits(int num) {
        return (num - 2) % 9 + 2;
    }
};
// @lc code=end