//
// @lc app=leetcode.cn id=941 lang=cpp
//
// [941] sort-array-by-parity
//
class Solution {
public:
    vector<int> sortArrayByParity(vector<int>& A) {
        int i=0, j=A.size()-1, temp;
        while(i < j){
            while(i < j && A[i]%2==0) ++i;
            while(i < j && A[j]%2==1) --j;
                temp = A[j];
                A[j] = A[i];
                A[i] = temp;
                ++i;--j;
        }
        return A;
    }
};
// @lc code=end