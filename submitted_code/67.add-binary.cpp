//
// @lc app=leetcode.cn id=67 lang=cpp
//
// [67] add-binary
//
class Solution {
public:
    string addBinary(string a, string b) {
        int lenA = a.size();
        int lenB = b.size();
        int LEN = lenA;
        if(lenA > lenB){
            string zero(lenA-lenB,'0');
            b = zero+b;
        }
        else if(lenA < lenB){
            string zero(lenB-lenA,'0');
            a = zero+a;
            LEN = lenB;
        }
        string s;
        int flag = 0;
        for(int i=LEN-1, j=LEN-1;i>=0;--i,--j){
            if(a[i] == '0' && b[j] == '0'){
                if(flag == 0){
                    s = '0'+s;
                    flag = 0;    
                }
                else if(flag == 1){
                    s = '1'+s;
                    flag = 0;    
                }
            }else if((a[i] == '1' && b[j] == '0') || (a[i] == '0' && b[j] == '1')){
                if(flag == 0){
                    s = '1'+s;
                    flag = 0;    
                }
                else if(flag == 1){
                    s = '0'+s;
                    flag = 1;    
                }
            }else if(a[i] == '1' && b[j] == '1'){
                if(flag == 0){
                    s = '0'+s;
                    flag = 1;    
                }
                else if(flag == 1){
                    s = '1'+s;
                    flag = 1;    
                }
            }
        }
        if(flag == 1)
            s = '1'+s;
        return s;
    }
};
// @lc code=end