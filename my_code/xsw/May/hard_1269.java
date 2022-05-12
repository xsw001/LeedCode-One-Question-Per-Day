package xsw.May;
/*
1269. 停在原地的方案数
有一个长度为 arrLen 的数组，开始有一个指针在索引 0 处。

每一步操作中，你可以将指针向左或向右移动 1 步，或者停在原地（指针不能被移动到数组范围外）。

给你两个整数 steps 和 arrLen ，请你计算并返回：在恰好执行 steps 次操作以后，指针仍然指向索引 0 处的方案数。

由于答案可能会很大，请返回方案数 模 10^9 + 7 后的结果。

示例 1：

输入：steps = 3, arrLen = 2
输出：4
解释：3 步后，总共有 4 种不同的方法可以停在索引 0 处。
向右，向左，不动
不动，向右，向左
向右，不动，向左
不动，不动，不动
示例  2：

输入：steps = 2, arrLen = 4
输出：2
解释：2 步后，总共有 2 种不同的方法可以停在索引 0 处。
向右，向左
不动，不动
示例 3：

输入：steps = 4, arrLen = 2
输出：8


提示：

1 <= steps <= 500
1 <= arrLen <= 10^6
通过次数4,924提交次数10,869
 */

import java.util.*;

///对于计算方案数的题目，常用的方法是动态规划。
public class hard_1269 {

    // 不会
    static int ans;

    public static int numWays1(int steps, int arrLen) {
        ans = 0;
        dfs(steps, arrLen, 0, 0);
        return ans;
    }

    private static void dfs(int steps, int arrLen, int startIndex, int num) {
        if (num >= steps) {
            if (startIndex == 0)
                ++ans;
            return;
        }
        for (int i = startIndex; i < arrLen; i++) {
            ++num;
            dfs(steps, arrLen, i, num);
            --num;
            if (i < arrLen - 1) {
                ++num;
                dfs(steps, arrLen, i + 1, num);
                --num;
            }
            if (i > 0) {
                ++num;
                dfs(steps, arrLen, i - 1, num);
                --num;
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(numWays(2, 2));
    }

    private static final int MOD = 1000000007;

    private static int arr;
    private static long[][] memo;

    public static int numWays2(int steps, int arrLen) {
        // 数组长度最多就是一般的步数+1, 否则走太远就回不来了。
        arr = Math.min(arrLen, steps / 2 + 1);

        memo = new long[arr][steps + 1];
        for (int i = 0; i < arr; i++) {
            for (int j = 0; j < steps + 1; j++) {
                memo[i][j] = -1;
            }
        }
        return (int) backTrack(0, steps);
    }

    private static long backTrack(int from, int steps) {
        if (from == 0 && steps == 0) {
            return 1;
        }

        if (from > steps) {
            return 0;
        }

        if (memo[from][steps] != -1) {
            return memo[from][steps];
        }

        // 不动
        long nonMoveCount = backTrack(from, steps - 1);

        // 向左
        long leftMoveCount = 0;
        if (from > 0) {
            leftMoveCount = backTrack(from - 1, steps - 1);
        }

        // 向右
        long rightMoveCount = 0;
        if (from < arr - 1) {
            rightMoveCount = backTrack(from + 1, steps - 1);
        }

        memo[from][steps] = (nonMoveCount + leftMoveCount + rightMoveCount) % MOD;

        return memo[from][steps];
    }

    public static int numWays(int steps, int arrLen) {
        // 数组长度最多就是一般的步数+1, 否则走太远就回不来了。
        int arr = Math.min(arrLen, steps / 2 + 1);
        // 表示在 i 步操作之后，指针位于下标 j 的方案数
        int[][] dp = new int[steps + 1][arr];
        dp[0][0] = 1;
        for (int i = 1; i < steps + 1; i++) {
            for (int j = 0; j < arr; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % 1000000007;
                }
                if (j < arr - 1) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % 1000000007;
                }
            }
        }
        return dp[steps][0];
    }
}