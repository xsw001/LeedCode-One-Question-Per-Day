//
// @lc app=leetcode.cn id=100330 lang=cpp
//
// [100330] zuo-xuan-zhuan-zi-fu-chuan-lcof
//
class Solution {
public:
    void reverse(string &s,int start,int last){
        while(start < last){
            int tmp = s[start];
            s[start++] = s[last];
            s[last--] = tmp;
        }
    }

    string reverseLeftWords(string s, int n) {
        reverse(s,0,n-1);
        reverse(s,n,s.size()-1);
        reverse(s,0,s.size()-1);
        return s;
    }
};
// @lc code=end