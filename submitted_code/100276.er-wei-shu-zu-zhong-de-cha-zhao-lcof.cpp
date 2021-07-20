//
// @lc app=leetcode.cn id=100276 lang=cpp
//
// [100276] er-wei-shu-zu-zhong-de-cha-zhao-lcof
//
class Solution {
public:
    bool findNumberIn2DArray(vector<vector<int>>& matrix, int target) {
        if(matrix.size()==0)
            return false;
        int n = matrix.size(),m = matrix[0].size();
        int row = n-1,line = 0;
        while(row >= 0 && line < m){
            if(matrix[row][line] == target){
                return true;
            }
            else if(matrix[row][line] < target)
                ++line;
            else
                --row;
        }
        return false;
    }
};
// @lc code=end