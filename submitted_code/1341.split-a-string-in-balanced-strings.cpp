//
// @lc app=leetcode.cn id=1341 lang=cpp
//
// [1341] split-a-string-in-balanced-strings
//
class Solution {
public:
    int balancedStringSplit(string s) {
        int MaxNum = 0;
        int LNum = 0,RNum=0;
        for(int i=0;i<s.size();++i){
            if(s[i]=='R'){
                ++RNum;
            }else{
                ++LNum;
            }
            if(RNum == LNum){
                ++MaxNum;
                LNum = 0;
                RNum=0;
            }
        }
        return MaxNum;
    }
};
// @lc code=end