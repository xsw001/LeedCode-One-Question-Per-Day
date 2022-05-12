//
// @lc app=leetcode.cn id=100159 lang=cpp
//
// [100159] check-permutation-lcci
//
class Solution {
public:
    bool CheckPermutation(string s1, string s2) {
        if(s1.size()!=s2.size())
            return false;
        vector<int> map(27,0);
        for(int i=0;i<s1.size();++i){
            ++map[s1[i]-'a'];
            --map[s2[i]-'a'];
        }
        for(int i=0;i<map.size();++i){
            if(map[i]!=0)
                return false;
        }
        return true;
    }
};
// @lc code=end