//
// @lc app=leetcode.cn id=85 lang=java
//
// [85] maximal-rectangle
//
class Solution {
    public int maximalRectangle(char[][] matrix) {
if (matrix.length == 0 || (matrix.length == 1 && matrix[0].length == 1 && matrix[0][0] == '0')) return 0;

        int row = matrix.length;
        int col = matrix[0].length;
        int max = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int curr = 0;
                if (matrix[i][j] == '0') continue;
                int k = j + 1;
                while (k < col && matrix[i][k] == '1') k++;

                int l = i + 1;
                while (l < row && matrix[l][j] == '1') {
                    int m = j + 1;
                    while (m < k && matrix[l][m] == '1') m++;
                    if (m < k) {
                        curr = (k - j) * (l - i);
                        max = Math.max(max, curr);
                        k = m;
                    }
                    l++;
                }
                curr = (k - j) * (l - i);
                max = Math.max(max, curr);
            }
        }
        return max;
    }
}
// @lc code=end