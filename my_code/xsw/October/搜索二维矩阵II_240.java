package xsw.October;
/*
240. 搜索二维矩阵 II
编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。


示例 1：


输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 5
输出：true
示例 2：


输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21,23,26,30]], target = 20
输出：false


提示：

m == matrix.length
n == matrix[i].length
1 <= n, m <= 300
-109 <= matrix[i][j] <= 109
每行的所有元素从左到右升序排列
每列的所有元素从上到下升序排列
-109 <= target <= 109
通过次数176,844提交次数367,259
 */

import java.util.ArrayList;

public class 搜索二维矩阵II_240 {

    public static boolean searchMatrix(int[][] matrix, int target) {
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
        System.out.println(end);
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
        System.out.println(l);
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
        System.out.println(l);
        return false;
    }

    public static void main(String[] args) {
        int[][] data = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(searchMatrix(data, 23));
        System.out.println(searchMatrix(data, 13));
        System.out.println(searchMatrix(data, 30));
        int[][] a = {{5}};
        System.out.println(searchMatrix(a, 5));

    }

    class Solution {
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            int x = 0, y = n - 1;
            while (x < m && y >= 0) {
                if (matrix[x][y] == target) {
                    return true;
                }
                if (matrix[x][y] > target) {
                    --y;
                } else {
                    ++x;
                }
            }
            return false;
        }
    }

/*    作者：LeetCode-Solution
    链接：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/solution/sou-suo-er-wei-ju-zhen-ii-by-leetcode-so-9hcx/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}