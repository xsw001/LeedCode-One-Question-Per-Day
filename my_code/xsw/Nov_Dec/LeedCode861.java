package xsw.Nov_Dec;/*
861. 翻转矩阵后的得分
有一个二维矩阵 A 其中每个元素的值为 0 或 1 。

移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。

在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。

返回尽可能高的分数。



示例：

输入：[[0,0,1,1],
      [1,0,1,0],
      [1,1,0,0]]
输出：39
解释：
转换为 [[1,1,1,1],
       [1,0,0,1],
       [1,1,1,1]]
0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39


提示：

1 <= A.length <= 20
1 <= A[0].length <= 20
A[i][j] 是 0 或 1
*/

import java.util.Arrays;

public class LeedCode861 {

    //AC
    public static int matrixScore(int[][] A) {
        int columns = A.length;
        int rows = A[0].length;
        for (int i = 0; i < columns; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < rows; j++) {
                    if (A[i][j] == 0)
                        A[i][j] = 1;
                    else
                        A[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(A));
        for (int j = 1; j < rows; j++) {
            int count = 0;
            for (int[] ints : A) {
                if (ints[j] == 1)
                    ++count;
            }
            if (count < columns / 2+1) {
                for (int i = 0; i < columns; i++) {
                    if (A[i][j] == 0)
                        A[i][j] = 1;
                    else
                        A[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(A));
        int score = 0;
        for (int[] a : A) {
            int mi = rows-1;
            for (int i : a) {
                score += i*(1 << mi);
                --mi;
            }
        }
        return score;
    }

    public static void main(String[] args) {
        int[][] nums = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        int score = matrixScore1(nums);
        System.out.println(score);
    }

    //无需修改原矩阵，而是可以计算每一列对总分数的「贡献」，从而直接计算出最高的分数。
    public static int matrixScore1(int[][] A) {
        int m = A.length, n = A[0].length;
        //对于最左边的列而言，由于最优情况下，它们的取值都为 1，因此每个元素对分数的贡献都为 2^n-1
        int ret = m * (1 << (n - 1));

        for (int j = 1; j < n; j++) {
            int nOnes = 0;
            for (int i = 0; i < m; i++) {
                if (A[i][0] == 1) {
                    nOnes += A[i][j];
                } else {
                    nOnes += (1 - A[i][j]); // 如果这一行进行了行反转，则该元素的实际取值为 1 - A[i][j]
                }
            }
            //我们统计这一列 0,1 的数量，令其中的最大值为 k，则 k 是列翻转后的 1 的数量，该列的总贡献为 k * 2^{n-j-1}
            int k = Math.max(nOnes, m - nOnes);
            ret += k * (1 << (n - j - 1));
        }
        return ret;
    }
}
