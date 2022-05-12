//
// @lc app=leetcode.cn id=557 lang=cpp
//
// [557] reverse-words-in-a-string-iii
//
class Solution {
public:
    string reverseWords(string s){
        int j=0,i=0;
        while(i<s.size()){
            if(s[i]==' '){
                help(s,j,i-1);
                j=i+1;
            }
            ++i;
        }
        help(s,j,i-1);
        return s;
    }
    void help(string &s,int m,int n){
        while(m < n){
            char temp = s[m];
            s[m] = s[n];
            s[n] = temp;
            ++m;
            --n;
        }
    }
};
// @lc code=end