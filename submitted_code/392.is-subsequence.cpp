//
// @lc app=leetcode.cn id=392 lang=cpp
//
// [392] is-subsequence
//
class Solution {
public:
    bool isSubsequence(string s, string t) {
        int lenS = s.size(), lenT = t.size();
        int i=0,j=0;
        while(i < lenS && j < lenT){
            if(s[i] == t[j])
                ++i;
            ++j;
        }
        if(i == lenS)
            return true;
        return false;
    }
};
// @lc code=end