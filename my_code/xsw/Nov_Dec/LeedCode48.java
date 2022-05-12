package xsw.Nov_Dec;/*
48. 旋转图像
给定一个 n × n 的二维矩阵表示一个图像。

将图像顺时针旋转 90 度。

说明：

你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

示例 1:

给定 matrix =
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

原地旋转输入矩阵，使其变为:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
示例 2:

给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
],

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

import java.util.Arrays;

/*
面试考点：
        从简单的例子出发，系统性分析得出普适解
        代码是否简单明了
*/
public class LeedCode48 {

    //另辟数组
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        int[][] matrixTemp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrixTemp[j][n - i - 1] = matrix[i][j];
            }
        }
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrixTemp[i], 0, matrix[i], 0, n);
        }
    }

    public static void main(String[] args) {
        int[][] nums = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate2(nums);
        System.out.println(Arrays.deepToString(nums));
    }

    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        int i = 0, j = n - 1;
        //水平翻转
        while (i < j) {
            int[] temp = matrix[i];
            matrix[i] = matrix[j];
            matrix[j] = temp;
            ++i;
            --j;
        }
        //对角线翻转
        for (int a = 0; a < n; ++a) {
            for (int b = 0; b < a; ++b) {
                int temp = matrix[a][b];
                matrix[a][b] = matrix[b][a];
                matrix[b][a] = temp;
            }
        }
    }

    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
                System.out.println(Arrays.deepToString(matrix));
            }
        }
    }
}
