package Tiger2022.offer;
/*
剑指 Offer 04. 二维数组中的查找
在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。



示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。

给定 target = 20，返回 false。



限制：

0 <= n <= 1000

0 <= m <= 1000



注意：本题与主站 240 题相同：https://leetcode-cn.com/problems/search-a-2d-matrix-ii/

通过次数318,581提交次数790,354
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class 二维数组中的查找_4 {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0 || target < matrix[0][0] || target > matrix[m - 1][matrix[0].length - 1])
            return false;
        int l = 0, r = m - 1, n = matrix[0].length;
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

    @Test
    public void test() throws Exception {

        int[][] data = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}};
        System.out.println(findNumberIn2DArray(data, 8));
        System.out.println(findNumberIn2DArray(data, 3));
        System.out.println(findNumberIn2DArray(data, 19));
        System.out.println(findNumberIn2DArray(data, 13));
        System.out.println(findNumberIn2DArray(data, 1));

        List<Integer> list = new ArrayList<>();

    }


}
