//
// @lc app=leetcode.cn id=59 lang=java
//
// [59] spiral-matrix-ii
//
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int num = 1;
        int t = 0, l = 0, r = n - 1, b = n - 1;
        for (int i = 0; i < n*n; i++) {
            for (int j = l; j <= r; j++) {
                matrix[t][j] = num++;
            }
            ++t;
            for (int j = t; j <= b; j++) {
                matrix[j][r] = num++;
            }
            --r;
            for (int j = r; j >= l; j--) {
                matrix[b][j] = num++;
            }
            --b;
            for (int j = b; j >= t; j--) {
                matrix[j][l] = num++;
            }
            ++l;
        }
        return matrix;
    }
}
// @lc code=end