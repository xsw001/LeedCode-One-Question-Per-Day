//
// @lc app=leetcode.cn id=576 lang=java
//
// [576] out-of-boundary-paths
//
class Solution {
    public int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
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
// @lc code=end