//
// @lc app=leetcode.cn id=345 lang=cpp
//
// [345] reverse-vowels-of-a-string
//
class Solution {
public:

    bool isVowels(char s){
        if(s=='a'||s=='e'||s=='i'||s=='o'||s=='u'||s=='A'||s=='E'||s=='I'||s=='O'||s=='U'){
            return true;
        }else{
            return false;
        }
    }

    string reverseVowels(string s) {
        int i = 0, j = s.size()-1;
        while(i<j){
            while(i<j && !isVowels(s[i])) ++i;
            while(i<j && !isVowels(s[j])) --j;
            char tmp = s[i];
            s[i] = s[j];
            s[j] = tmp;
            ++i;--j;
        }
        return s;
    }
};
// @lc code=end