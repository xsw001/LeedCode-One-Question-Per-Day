package xsw.June;
/*
279. 完全平方数
给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。

给你一个整数 n ，返回和为 n 的完全平方数的 最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。



示例 1：

输入：n = 12
输出：3
解释：12 = 4 + 4 + 4
示例 2：

输入：n = 13
输出：2
解释：13 = 4 + 9

提示：

1 <= n <= 104
通过次数156,094提交次数252,983
 */

import java.util.ArrayList;
import java.util.Arrays;

public class 完全平方数_279 {

    public static int numSquares(int n) {
        int num = 1;
        ArrayList<Integer> list = new ArrayList<>();
        while (num * num <= n) {
            list.add(num * num);
            ++num;
        }
        System.out.println(list);
        int[] dp = new int[n + 1]; // 和为 n 的最少完全平方数的个数
        Arrays.fill(dp, n);
        for (Integer i : list) {
            dp[i] = 1;
        }
        for (int i = 1; i*i <= n; i++) {
            for (int j = i*i; j <= n; ++j) {
                dp[j] = Math.min(dp[j], dp[j - i*i] + 1);
            }
        }
        System.out.println(Arrays.toString(dp));

        return dp[n];
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(numSquares(17));
    }

}