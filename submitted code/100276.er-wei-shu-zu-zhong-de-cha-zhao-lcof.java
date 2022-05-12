//
// @lc app=leetcode.cn id=100276 lang=java
//
// [100276] er-wei-shu-zu-zhong-de-cha-zhao-lcof
//
class Solution {
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0)
            return false;
        int n = matrix[0].length;
        if(n == 0 || target < matrix[0][0] || target > matrix[m-1][n-1])
            return false;
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (matrix[mid][0] > target)
                r = mid - 1;
            else
                l = mid;
        }
        if (matrix[l][0] == target)
            return true;
        int end = l;
        l = 0;
        r = m - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (matrix[mid][n - 1] < target)
                l = mid + 1;
            else
                r = mid;
        }
        if (matrix[l][n - 1] == target)
            return true;
        int start = l;
        while (start <= end) {
            l = 0;
            r = n - 1;
            while (l < r) {
                int mid = (l + r) / 2;
                if (matrix[start][mid] < target)
                    l = mid + 1;
                else
                    r = mid;
            }
            if (matrix[start++][l] == target)
                return true;
        }
        return false;
    }
}
// @lc code=end