//
// @lc app=leetcode.cn id=58 lang=cpp
//
// [58] length-of-last-word
//
class Solution {
public:
    int lengthOfLastWord(string s) {
        int tmp=0;
        int i=s.length()-1;
        while(i > -1 && s[i] == ' ')
            --i;
        while(i > -1 && s[i] != ' '){
            ++tmp;
            --i;
        }
        return tmp;
    }
};
// @lc code=end