package xsw.March;
/*
59. 螺旋矩阵 II
给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。



示例 1：


输入：n = 3
输出：[[1,2,3],[8,9,4],[7,6,5]]
示例 2：

输入：n = 1
输出：[[1]]


提示：

1 <= n <= 20
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class medium_59 {

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int i = 0;
        //从外部向内部遍历，逐层打印数据
        int num = 1;
        while (i < n * n) {
            for (int j = i; j < n - i; j++) {//从左向右
                matrix[i][j] = num++;
            }
            for (int j = i + 1; j < n - i; j++) {//从上往下
                matrix[j][(n - 1) - i] = num++;
            }

            for (int j = (n - 1) - (i + 1); j >= i && (n - 1 - i != i); j--) {
                matrix[(n - 1) - i][j] = num++;//从右往左，如果这一层只有1行，那么第一个循环已经将该行打印了，这里就不需要打印了
            }
            for (int j = (n - 1) - (i + 1); j >= i + 1 && (n - 1 - i) != i; j--) {
                matrix[j][i] = num++;//从下往上，如果这一层只有1列，那么第2个循环已经将该列打印了，这里不需要打印
            }
            i++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(generateMatrix1(9)));
    }

    public static int[][] generateMatrix1(int n) {
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