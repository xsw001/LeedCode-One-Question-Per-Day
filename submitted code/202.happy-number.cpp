//
// @lc app=leetcode.cn id=202 lang=cpp
//
// [202] happy-number
//
class Solution {
public:
    bool isHappy(int n) {
        if(n == 4)
        {
            return false;
        }
        int res = 0;
        while(n > 0)
        {
            res += (n % 10) * (n % 10);
            n /= 10;
        }
        return res == 1 ? true:isHappy(res);
    }
};
// @lc code=end