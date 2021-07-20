//
// @lc app=leetcode.cn id=777 lang=java
//
// [777] toeplitz-matrix
//
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int i = 0;
        while(i < matrix.length){
            int ii = 0,iii = i;;
            int num = matrix[i][ii];
            while(i < matrix.length && ii < matrix[0].length){
                if(num != matrix[i][ii])
                    return false;
                ++i;
                ++ii;
            }
            i = iii+1;
        }
        int j = 1;
        while(j < matrix[0].length){
            int jj = 0,jjj = j;
            int num = matrix[jj][j];
            while(jj < matrix.length && j < matrix[0].length){
                if(num != matrix[jj][j])
                    return false;
                ++j;
                ++jj;
            }
            j = jjj+1;
        }
        return true;
    }
}
// @lc code=end