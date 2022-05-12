package xsw.Nov_Dec;
/*
85. 最大矩形
给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

示例 1：

输入：matrix = [['1','0','1','0','0'],['1','0','1','1','1'],['1','1','1','1','1'],['1','0','0','1','0']]
输出：6
解释：最大矩形如上图所示。
示例 2：

输入：matrix = []
输出：0
示例 3：

输入：matrix = [['0']]
输出：0
示例 4：

输入：matrix = [['1']]
输出：1
示例 5：

输入：matrix = [['0','0']]
输出：0


提示：

rows == matrix.length
cols == matrix[0].length
0 <= row, cols <= 200
matrix[i][j] 为 '0' 或 '1'
*/

import java.util.Deque;
import java.util.LinkedList;

public class LeedCode85多维矩阵 {

    //从左上方开始，先到右上方，在往下逐层检查，从左往右检查，更新矩阵的长度
    public static int maximalRectangle1(char[][] matrix) {
        int max = 0;
        int rows = matrix.length;
        if (rows == 0)
            return 0;
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '0')
                    continue;

                int temp = 0;
                int a = i, b = j;
                while (b < cols - 1 && matrix[a][b + 1] == '1')
                    ++b;
                while (a < rows - 1 && matrix[a + 1][j] == '1') {
                    int c = j;
                    while (c < cols-1 && matrix[a+1][c+1] == '1')
                        ++c;
                    if (c <= b) {
                        temp = (a - i + 1) * (b - j + 1);
                        max = Math.max(max, temp);
                        b =c;
                    }
                    ++a;
                }
                temp = (a - i + 1) * (b - j + 1);
                max = Math.max(max, temp);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = {
                {'1', '1', '1', '1','1', '1', '1', '1'},
                {'1', '1', '1', '1','1', '1', '1', '0'},
                {'1', '1', '1', '1','1', '1', '1', '0'},
                {'1', '1', '1', '1','1', '0', '0', '0'},
                {'0', '1', '1', '1','1', '0', '0', '0'}};
/*         char[][] matrix = {
                {'1', '0'},
                {'1', '0'}};*/
        int rectangle = maximalRectangle1(matrix);

        System.out.println(rectangle);
    }

    /*方法一: 使用柱状图的优化暴力方法
1. 首先求出高度是 1 的矩形面积，也就是它自身的数，如图中橙色的 4，面积就是 4。

2. 然后向上扩展一行，高度增加一，选出当前列最小的数字，作为矩阵的宽，求出面积，对应上图的矩形框。

3. 然后继续向上扩展，重复步骤 2。
    */
    // 从右下开始，到左上。利用了一个数组记录矩阵的长
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        //保存以当前数字结尾的连续 1 的个数
        int[][] width = new int[matrix.length][matrix[0].length];
        int maxArea = 0;
        //遍历每一行
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                //更新 width
                if (matrix[row][col] == '1') {
                    width[row][col] = (col == 0 ? 0 : width[row][col - 1] + 1);
                }
                //记录所有行中最小的数
                int minWidth = width[row][col];
                //向上扩展行
                for (int up_row = row; up_row >= 0; up_row--) {
                    int height = row - up_row + 1;
                    //找最小的数作为矩阵的宽
                    minWidth = Math.min(minWidth, width[up_row][col]);
                    //更新面积
                    maxArea = Math.max(maxArea, height * minWidth);
                }
            }
        }
        return maxArea;
    }

    public int maximalRectangle2(char[][] matrix) {
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

    // 单调栈  没看  不会
    public int maximalRectangle4(char[][] matrix) {
        int m = matrix.length;
        if (m == 0) {
            return 0;
        }
        int n = matrix[0].length;
        int[][] left = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    left[i][j] = (j == 0 ? 0 : left[i][j - 1]) + 1;
                }
            }
        }

        int ret = 0;
        for (int j = 0; j < n; j++) { // 对于每一列，使用基于柱状图的方法
            int[] up = new int[m];
            int[] down = new int[m];

            Deque<Integer> stack = new LinkedList<Integer>();
            for (int i = 0; i < m; i++) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? -1 : stack.peek();
                stack.push(i);
            }
            stack.clear();
            for (int i = m - 1; i >= 0; i--) {
                while (!stack.isEmpty() && left[stack.peek()][j] >= left[i][j]) {
                    stack.pop();
                }
                down[i] = stack.isEmpty() ? m : stack.peek();
                stack.push(i);
            }

            for (int i = 0; i < m; i++) {
                int height = down[i] - up[i] - 1;
                int area = height * left[i][j];
                ret = Math.max(ret, area);
            }
        }
        return ret;
    }

}
