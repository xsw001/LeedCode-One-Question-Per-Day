//
// @lc app=leetcode.cn id=1583 lang=java
//
// [1583] paint-house-iii
//
class Solution {
    // 极大值
    // 选择 Integer.MAX_VALUE / 2 的原因是防止整数相加溢出
    static final int INFTY = Integer.MAX_VALUE / 2;

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // 将颜色调整为从 0 开始编号，没有被涂色标记为 -1
        for (int i = 0; i < m; ++i) {
            --houses[i];
        }

        // dp 所有元素初始化为极大值
        int[][][] dp = new int[m][n][target];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Arrays.fill(dp[i][j], INFTY);
            }
        }
        int[][][] best = new int[m][target][3];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < target; ++j) {
                best[i][j][0] = best[i][j][2] = INFTY;
                best[i][j][1] = -1;
            }
        }

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (houses[i] != -1 && houses[i] != j) {
                    continue;
                }
                
                for (int k = 0; k < target; ++k) {
                    if (i == 0) {
                        if (k == 0) {
                            dp[i][j][k] = 0;
                        }
                    } else {
                        dp[i][j][k] = dp[i - 1][j][k];
                        if (k > 0) {
                            // 使用 best(i-1,k-1) 直接得到 dp(i,j,k) 的值
                            int first = best[i - 1][k - 1][0];
                            int firstIdx = best[i - 1][k - 1][1];
                            int second = best[i - 1][k - 1][2];
                            dp[i][j][k] = Math.min(dp[i][j][k], (j == firstIdx ? second : first));
                        }
                    }

                    if (dp[i][j][k] != INFTY && houses[i] == -1) {
                        dp[i][j][k] += cost[i][j];
                    }

                    // 用 dp(i,j,k) 更新 best(i,k)
                    int first = best[i][k][0];
                    int firstIdx = best[i][k][1];
                    int second = best[i][k][2];
                    if (dp[i][j][k] < first) {
                        best[i][k][2] = first;
                        best[i][k][0] = dp[i][j][k];
                        best[i][k][1] = j;
                    } else if (dp[i][j][k] < second) {
                        best[i][k][2] = dp[i][j][k];
                    }
                }
            }
        }

        int ans = INFTY;
        for (int j = 0; j < n; ++j) {
            ans = Math.min(ans, dp[m - 1][j][target - 1]);
        }
        return ans == INFTY ? -1 : ans;
    }
}
// @lc code=end