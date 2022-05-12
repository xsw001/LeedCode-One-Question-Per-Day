package xsw.August;
/*
576. 出界的路径数
给你一个大小为 m x n 的网格和一个球。球的起始坐标为 [startRow, startColumn] 。
你可以将球移到在四个方向上相邻的单元格内（可以穿过网格边界到达网格之外）。你 最多 可以移动 maxMove 次球。

给你五个整数 m、n、maxMove、startRow 以及 startColumn ，找出并返回可以将球移出边界的路径数量。
因为答案可能非常大，返回对 109 + 7 取余 后的结果。



示例 1：


输入：m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
输出：6
示例 2：


输入：m = 1, n = 3, maxMove = 3, startRow = 0, startColumn = 1
输出：12


提示：

1 <= m, n <= 50
0 <= maxMove <= 50
0 <= startRow < m
0 <= startColumn < n
通过次数14,110提交次数32,552
 */

import java.util.ArrayList;
import java.util.HashMap;

public class 出界的路径数_576 {

    // 超时
    static int ans;

    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        ans = 0;
        dfs(m, n, maxMove, startRow, startColumn);
        return ans % 100000007;
    }

    private static void dfs(int m, int n, int maxMove, int r, int c) {
        if (maxMove < 0)
            return;
        if (r < 0 || r > m - 1) {
            ans++;
            return;
        }
        if (c < 0 || c > n - 1) {
            ans++;
            return;
        }
        dfs(m, n, maxMove - 1, r + 1, c);
        dfs(m, n, maxMove - 1, r, c + 1);
        dfs(m, n, maxMove - 1, r, c - 1);
        dfs(m, n, maxMove - 1, r - 1, c);
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(findPaths(6, 6, 10, 2, 3));
        System.out.println(findPaths1(6, 6, 10, 2, 3));
    }

    // dp[i][j][k] 表示球移动 i 次之后位于坐标 (j, k) 的路径 数量
    public static int findPaths1(int m, int n, int maxMove, int startRow, int startColumn) {
        final int MOD = 1000000007;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int ans = 0;
        int[][][] dp = new int[maxMove+1][m][n];
        dp[0][startRow][startColumn] = 1;
        for (int i = 0; i < maxMove; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < n; k++) {
                    for (int[] direction : directions) {
                        int jj = j + direction[0], kk = k + direction[1];
                        if (jj >= 0 && jj < m && kk >= 0 && kk < n)
                            dp[i+1][jj][kk] = (dp[i+1][jj][kk] + dp[i][j][k]) % MOD;
                        else {
                            ans += dp[i][j][k];
                            ans %= MOD;
                        }
                    }
                }
            }
        }
        return ans;
    }
}