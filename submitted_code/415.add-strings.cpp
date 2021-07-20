//
// @lc app=leetcode.cn id=415 lang=cpp
//
// [415] add-strings
//
class Solution {
public:
    string addStrings(string num1, string num2) {
        if(num1.size() < num2.size()){
            while(num1.size()!=num2.size())
                num1 = '0' + num1;
        }
        if(num1.size() > num2.size()){
            while(num1.size()!=num2.size())
                num2 = '0' + num2;
        }
        int i = num1.size()-1;
        int j = num2.size()-1;
        int flag = 0;
        string result(num2.size(),0);
        while(i >= 0){
            int sum = num1[i] + num2[i] - 96 + flag;
            flag = sum/10;
            result[i]=sum%10+'0';
            --i;
        }
        if(flag == 1)
            result = '1' + result;
        return result;
    }
};
// @lc code=end