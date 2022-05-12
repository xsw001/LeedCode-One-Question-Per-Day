//
// @lc app=leetcode.cn id=100280 lang=cpp
//
// [100280] ti-huan-kong-ge-lcof
//
class Solution {
public:
    string replaceSpace(string s) {
        string res;
        for(int i=0;i<s.size(); ++i){
            if(s[i] == ' ')
                res += "%20";
            else
                res += s[i];
        }
        return res;
    }
};
// @lc code=end