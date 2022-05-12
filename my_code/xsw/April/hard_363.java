package xsw.April;
/*
363. 矩形区域不超过 K 的最大数值和
给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。

题目数据保证总会存在一个数值和不超过 k 的矩形区域。



示例 1：


输入：matrix = [[1,0,1],[0,-2,3]], k = 2
输出：2
解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
示例 2：

输入：matrix = [[2,2,-1]], k = 3
输出：3


提示：

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-100 <= matrix[i][j] <= 100
-105 <= k <= 105


进阶：如果行数远大于列数，该如何设计解决方案？

通过次数9,996提交次数23,889
 */

import java.util.ArrayList;
import java.util.TreeSet;

public class hard_363 {

    public static int maxSumSubmatrix1(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int[][] s = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                s[i][j] = s[i - 1][j] + s[i][j - 1] + matrix[i - 1][j - 1] - s[i - 1][j - 1];
            }
        }
        long res = (long) -1e18;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int a = i + 1; a <= m; a++) {
                    for (int b = j + 1; b <= n; b++) {
                        long sum = s[a][b] + s[i][j] - s[a][j] - s[i][b];
                        if (sum <= k) res = Math.max(res, sum);
                    }
                }
            }
        }
        return (int) res;
    }

    public static void main(String[] args) {
        int[][] data = {
                {3, 2, -3, 4},
                {-1, 2, 3, 0}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(maxSumSubmatrix2(data, 5));
    }

    // 附上完整代码
    public static int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, max = Integer.MIN_VALUE;
        // O(n ^ 2 * m)
        for (int l = 0; l < n; l++) { // 枚举左边界
            int[] rowSum = new int[m]; // 左边界改变才算区域的重新开始
            for (int r = l; r < n; r++) { // 枚举右边界
                for (int i = 0; i < m; i++) { // 按每一行累计到 dp
                    rowSum[i] += matrix[i][r];
                }
                max = Math.max(max, dpmax(rowSum, k));
                if (max == k) return k; // 尽量提前
            }
        }
        return max;
    }

    // 在数组 arr 中，求不超过 k 的最大值
    private static int dpmax(int[] arr, int k) {
        int rollSum = arr[0], rollMax = rollSum;
        // O(rows)
        for (int i = 1; i < arr.length; i++) {
            if (rollSum > 0)
                rollSum += arr[i];
            else
                rollSum = arr[i];
            if (rollSum > rollMax)
                rollMax = rollSum;
        }
        if (rollMax <= k) return rollMax;
        // O(rows ^ 2)
        int max = Integer.MIN_VALUE;
        for (int l = 0; l < arr.length; l++) {
            int sum = 0;
            for (int r = l; r < arr.length; r++) {
                sum += arr[r];
                if (sum > max && sum <= k) max = sum;
                if (max == k) return k; // 尽量提前
            }
        }
        return max;
    }

    public static int maxSumSubmatrix2(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length, ans = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {//上边界
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {//下边界
                for (int c = 0; c < n; c++) {
                    sum[c] += matrix[j][c];//geng新每列的元素和
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int right = 0;
                for (int v : sum) {
                    right += v;
                    //返回此 set 中大于等于给定元素的最小元素；如果不存在这样的元素，则返回 null。
                    /*要使 right - left 尽可能大，则需要寻找最小的满足 left ≥ right −k 的 left*/
                    Integer left = set.ceiling(right - k);
                    if (left != null) {
                        int NotGreaterThanTheMaximumValueOfK = right - left;//当前矩阵和的不大于K的最大值
                        ans = Math.max(ans, NotGreaterThanTheMaximumValueOfK);
                    }
                    // 将遍历过的 right 加到有序集合
                    set.add(right);
                }
            }
        }
        return ans;
    }
}