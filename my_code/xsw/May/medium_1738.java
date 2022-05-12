package xsw.May;
/*
1738. 找出第 K 大的异或坐标值
给你一个二维矩阵 matrix 和一个整数 k ，矩阵大小为 m x n 由非负整数组成。

矩阵中坐标 (a, b) 的 值 可由对所有满足 0 <= i <= a < m 且 0 <= j <= b < n 的元素 matrix[i][j]（下标从 0 开始计数）执行异或运算得到。

请你找出 matrix 的所有坐标中第 k 大的值（k 的值从 1 开始计数）。



示例 1：

输入：matrix = [[5,2],[1,6]], k = 1
输出：7
解释：坐标 (0,1) 的值是 5 XOR 2 = 7 ，为最大的值。
示例 2：

输入：matrix = [[5,2],[1,6]], k = 2
输出：5
解释：坐标 (0,0) 的值是 5 = 5 ，为第 2 大的值。
示例 3：

输入：matrix = [[5,2],[1,6]], k = 3
输出：4
解释：坐标 (1,0) 的值是 5 XOR 1 = 4 ，为第 3 大的值。
示例 4：

输入：matrix = [[5,2],[1,6]], k = 4
输出：0
解释：坐标 (1,1) 的值是 5 XOR 2 XOR 1 XOR 6 = 0 ，为第 4 大的值。


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 1000
0 <= matrix[i][j] <= 106
1 <= k <= m * n
通过次数5,266提交次数8,125
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class medium_1738 {

    public static int kthLargestValue(int[][] matrix, int k) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = matrix[0][0];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(dp[0][0]);
        for (int i = 1; i < m; i++) {
            dp[i][0] = matrix[i][0] ^ dp[i - 1][0];
            queue.add(dp[i][0]);
        }
        for (int i = 1; i < n; i++) {
            dp[0][i] = matrix[0][i] ^ dp[0][i - 1];
            queue.add(dp[0][i]);
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] ^ dp[i - 1][j - 1] ^ dp[i][j - 1] ^ dp[i - 1][j];
                queue.add(dp[i][j]);
            }
        }
        for (int i = 0; i < m*n-k; i++) {
            queue.poll();
        }
        return queue.peek();
    }

    public static void main(String[] args) {
        int[][] data = {{10, 9, 5}, {2, 0, 4}, {1, 0, 9}, {3, 4, 8}};
        System.out.println(kthLargestValue(data, 12));

    }

    public int kthLargestValue1(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        int[][] sum = new int[m + 1][n + 1];
        PriorityQueue<Integer> q = new PriorityQueue<>(k, (a, b)->a - b);
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                sum[i][j] = sum[i - 1][j] ^ sum[i][j - 1] ^ sum[i - 1][j - 1] ^ mat[i - 1][j - 1];
                if (q.size() < k) {
                    q.add(sum[i][j]);
                } else {
                    if (sum[i][j] > q.peek()) {
                        q.poll();
                        q.add(sum[i][j]);
                    }
                }
            }
        }
        return q.peek();
    }
}