//
// @lc app=leetcode.cn id=898 lang=java
//
// [898] transpose-matrix
//
class Solution {
    public int[][] transpose(int[][] matrix) {
        int m = matrix.length,n = matrix[0].length;
        if(m == n){
            for(int i=0;i<m;++i){
                for(int j=i+1;j<n;++j){
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
            return matrix;
        }
        int[][] res = new int[n][m];
        for(int i=0;i<m;++i){
            for(int j=0;j<n;++j){
            res[j][i] = matrix[i][j];
            }
        }
        return res;
    }
}
// @lc code=end