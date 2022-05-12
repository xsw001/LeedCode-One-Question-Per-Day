package xsw.April.团队赛;
/*
5. 最小矩形面积
通过的用户数0
尝试过的用户数0
用户总通过次数0
用户总提交次数0
题目难度Hard
二维平面上有 NN 条直线，形式为 y = kx + b，其中 k、b为整数 且 k > 0。所有直线以 [k,b] 的形式存于二维数组 lines 中，不存在重合的两条直线。两两直线之间可能存在一个交点，最多会有 C_N^2C
N
2
​
  个交点。我们用一个平行于坐标轴的矩形覆盖所有的交点，请问这个矩形最小面积是多少。若直线之间无交点、仅有一个交点或所有交点均在同一条平行坐标轴的直线上，则返回0。

注意：返回结果是浮点数，与标准答案 绝对误差或相对误差 在 10^-4 以内的结果都被视为正确结果

示例 1：

输入：lines = [[2,3],[3,0],[4,1]]

输出：48.00000

解释：三条直线的三个交点为 (3, 9) (1, 5) 和 (-1, -3)。最小覆盖矩形左下角为 (-1, -3) 右上角为 (3,9)，面积为 48

示例 2：

输入：lines = [[1,1],[2,3]]

输出：0.00000

解释：仅有一个交点 (-2，-1）

限制：

1 <= lines.length <= 10^5 且 lines[i].length == 2
1 <= lines[0] <= 10000
-10000 <= lines[1] <= 10000
与标准答案绝对误差或相对误差在 10^-4 以内的结果都被视为正确结果*/

import java.util.*;

public class hard_5 {

    public static double minRecSize1(int[][] lines) {
        int len = lines.length;
        if (len < 3)
            return 0;
        ArrayList<Double> X = new ArrayList<>();
        ArrayList<Double> Y = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int a = lines[i][0];
                int b = lines[i][1];
                int c = lines[j][0];
                int d = lines[j][1];
                if (a - c != 0) {
                    double x = (double) (d - b) / (double) (a - c);
                    X.add(x);
                    double y = a * x + b;
                    Y.add(y);
                    System.out.println(a + "," + b);
                    System.out.println(c + "," + d);
                    System.out.println(x + "," + y);
                    System.out.println();
                }
            }
        }
        System.out.println(X);
        System.out.println(Y);
        Collections.sort(X);
        Collections.sort(Y);
        return Math.abs(X.get(0) - X.get(X.size() - 1)) * Math.abs(Y.get(0) - Y.get(Y.size() - 1));
    }

    public static void main(String[] args) {
        int[][] lines = {{2, 3}, {3, 0}, {4, 1}};
        System.out.println(minRecSize(lines));
    }

    public static double minRecSize(int[][] lines) {
        int n = lines.length;
        double[][] lines2 = new double[n][2];
        for (int i = 0; i < n; i++) lines2[i] = new double[]{1.0 * lines[i][0], 1.0 * lines[i][1]};
        double[] resx = maxminSlope(lines2);
        for (int i = 0; i < n; i++) lines2[i] = new double[]{1.0 / lines[i][0], -1.0 * lines[i][1] / lines[i][0]};
        double[] resy = maxminSlope(lines2);
        if (Math.abs(resx[0]) > 1e9 || Math.abs(resx[1]) > 1e9) return 0;
        return (resx[1] - resx[0]) * (resy[1] - resy[0]);
    }

    static double[] maxminSlope(double[][] lines2) {
        int n = lines2.length;
        double minx = 1e15, maxx = -1e15;
        Arrays.sort(lines2, (l1, l2) -> Math.abs(l1[0] - l2[0]) > 1e-9 ? Double.compare(l1[0], l2[0]) : Double.compare(l1[1], l2[1]));
        for (int i = 1; i < n; i++) {
            if (Math.abs(lines2[i][0] - lines2[i - 1][0]) > 1e-9) {
                minx = Math.min(minx, 1.0 * (lines2[i][1] - lines2[i - 1][1]) / (lines2[i][0] - lines2[i - 1][0]));
                maxx = Math.max(maxx, 1.0 * (lines2[i][1] - lines2[i - 1][1]) / (lines2[i][0] - lines2[i - 1][0]));
            }
        }
        Arrays.sort(lines2, (l1, l2) -> Math.abs(l1[0] - l2[0]) > 1e-9 ? Double.compare(l1[0], l2[0]) : Double.compare(l2[1], l1[1]));
        for (int i = 1; i < n; i++) {
            if (Math.abs(lines2[i][0] - lines2[i - 1][0]) > 1e-9) {
                minx = Math.min(minx, 1.0 * (lines2[i][1] - lines2[i - 1][1]) / (lines2[i][0] - lines2[i - 1][0]));
                maxx = Math.max(maxx, 1.0 * (lines2[i][1] - lines2[i - 1][1]) / (lines2[i][0] - lines2[i - 1][0]));
            }
        }
        return new double[]{minx, maxx};
    }
}