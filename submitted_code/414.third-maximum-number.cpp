//
// @lc app=leetcode.cn id=414 lang=cpp
//
// [414] third-maximum-number
//
class Solution {
public:
    int thirdMax(vector<int>& nums) 
    {
        set<int> s(nums.begin(), nums.end());
        auto it = s.end();
        it--;
        if(s.size() >= 3){
            it--;
            it--;
        }
        return *it;
    }
};
// @lc code=end