//
// @lc app=leetcode.cn id=1031 lang=cpp
//
// [1031] add-to-array-form-of-integer
//
class Solution {
public:
    vector<int> addToArrayForm(vector<int>& A, int K) {
        int flag = 0;
        vector<int> res;
        for(int a=A.size()-1; a>=0;--a){
            int temp = A[a] + K%10 + flag;
            K /= 10;
            if(temp > 9){
                temp %= 10;
                flag = 1; 
            }else
                flag = 0;
            res.push_back(temp);
        }
        while(flag == 1){
            int temp = 1+K%10;
            if(temp > 9){
                temp = 0;
                flag = 1;
            }else
                flag = 0;
            res.push_back(temp);
            K/=10;
        }
            
        if(K != 0){
            while(K != 0){
                res.push_back(K%10);
                K/=10;
            }
        }
        reverse(res.begin(),res.end());
        return res;
    }
};
// @lc code=end