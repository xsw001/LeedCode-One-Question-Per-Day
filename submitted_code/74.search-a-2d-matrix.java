//
// @lc app=leetcode.cn id=74 lang=java
//
// [74] search-a-2d-matrix
//
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m-1;
        while(l < r){
            int mid = l + r+1 >> 1;
            if(matrix[mid][0] <= target)
                l = mid;
            else
                r = mid - 1;
        }
        if(matrix[l][0] == target)
            return true;
        int t = l;
        l = 0; r = n-1;
        while(l < r){
            int mid = l + r >> 1;
            if(matrix[t][mid] >= target)
                r = mid;
            else
                l = mid + 1;
        }
        return matrix[t][l] == target;
    }
}
// @lc code=end