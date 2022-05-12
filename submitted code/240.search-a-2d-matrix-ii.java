//
// @lc app=leetcode.cn id=240 lang=java
//
// [240] search-a-2d-matrix-ii
//
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int l = 0, r = m - 1;
        while (l < r) {
            int mid = (l + r + 1) / 2;
            if (matrix[mid][0] == target)
                return true;
            if (matrix[mid][0] > target)
                r = mid - 1;
            else
                l = mid;
        }
        int end = l;
       // System.out.println(end);
        l = 0;
        r = m - 1;
        while (l < r) {
            int mid = (l + r) / 2;
            if (matrix[mid][n - 1] == target)
                return true;
            if (matrix[mid][n - 1] < target)
                l = mid + 1;
            else
                r = mid;
        }
        //System.out.println(l);
        for (int i = l; i <= end; i++) {
            int a = 0, b = n - 1;
            while (a <= b) {
                int mid = (a + b) / 2;
                if (matrix[i][mid] == target)
                    return true;
                if (matrix[i][mid] < target)
                    a = mid + 1;
                else
                    b = mid - 1;
            }
        }
        //System.out.println(l);
        return false;
    }
}
// @lc code=end