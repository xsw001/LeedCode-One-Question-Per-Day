package Tiger2022.offer.jinsahnyun;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

/*
小明在砍树
时间限制： 3000MS
内存限制： 589824KB
题目描述：
现在有一片树林，具有n行m列树，对于第 i 行第 j 列的树，若砍掉这棵树，小明会花费a[i][j]的力气。

小明打算在这片树林中选择一个正方形，对于处于这个正方形边界及内部的所有树木全部砍掉。但是小明的力气总和为C，他想让他选择的正方形内的树在他力气耗尽前全部砍掉，且正方形的面积尽可能大。请问他能选择的正方形最大面积为多少。



输入描述
第一行三个以空格隔开的正整数n，m 和 C，表示树林的行数、列数和小明的力气和。

接下来有n行，每行有m个数。表示砍掉第 i 行第 j 列这棵树会花费a[i][j]的力气。

数字间有空格隔开。

对于100%的数据，1≤C，a[i][j] ≤ 300000， 1≤n , m≤500

输出描述
输出可选择的正方形的最大面积


样例输入
3 3 4
3 3 3
3 1 1
3 1 1
样例输出
4

提示
样例解释

可以选择第2行第2列开始，第3行第3列结束这样一个正方形，边长为2，面积为4，花费的力气和为4，小于等于小明的力气和，所以是可以选的方案，且在所有可选的正方形中是面积最大的一种选择方案，所以输出面积4。
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int c = sc.nextInt();
        int[][] nums = new int[n][m];
        int min = 3000001;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                nums[i][j] = sc.nextInt();
                min = Math.min(min, nums[i][j]);
            }
        }
        if (min > c) {
            System.out.println(0);
            return;
        }
        if (min == c) {
            System.out.println(1);
            return;
        }
        int[][] pre = new int[n][m];
        pre[0][0] = nums[0][0];
        for (int i = 1; i < n; i++) {
            pre[i][0] = pre[i - 1][0] + nums[i][0];
        }
        for (int i = 1; i < m; i++) {
            pre[0][i] = pre[0][i - 1] + nums[0][i];
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                pre[i][j] = nums[i][j] + pre[i - 1][j] + pre[i][j - 1] - pre[i - 1][j - 1];
            }
        }
        int l = 1, r = Math.min(n, m);

        for (int i = r; i >= l; i--) {
            if (can(pre, i, c)) {
                System.out.println(i * i);
                return;
            }
        }
        System.out.println(0);
    }

    private static boolean can(int[][] pre, int l, int target) {
        int n = pre.length;
        int m = pre[0].length;
        for (int i = 0; i <= n - l; i++) {
            for (int j = 0; j <= m - l; j++) {
                int a = i + l - 1;
                int b = j + l - 1;
                int mj = pre[a][b];
                if (i > 0 && j > 0)
                    mj = mj - pre[i - 1][b] - pre[a][j - 1] + pre[i - 1][j - 1];
                else if (i > 0)
                    mj = mj - pre[i - 1][b];
                else if (j > 0)
                    mj = mj - pre[a][j - 1];
                if (mj <= target)
                    return true;
            }
        }
        return false;
    }


}
