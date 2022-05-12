package xsw.August;
/*
552. 学生出勤记录 II
可以用字符串表示一个学生的出勤记录，其中的每个字符用来标记当天的出勤情况（缺勤、迟到、到场）。记录中只含下面三种字符：
'A'：Absent，缺勤
'L'：Late，迟到
'P'：Present，到场
如果学生能够 同时 满足下面两个条件，则可以获得出勤奖励：

按 总出勤 计，学生缺勤（'A'）严格 少于两天。
学生 不会 存在 连续 3 天或 连续 3 天以上的迟到（'L'）记录。
给你一个整数 n ，表示出勤记录的长度（次数）。请你返回记录长度为 n 时，可能获得出勤奖励的记录情况 数量 。答案可能很大，所以返回对 109 + 7 取余 的结果。



示例 1：

输入：n = 2
输出：8
解释：
有 8 种长度为 2 的记录将被视为可奖励：
"PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
只有"AA"不会被视为可奖励，因为缺勤次数为 2 次（需要少于 2 次）。
示例 2：

输入：n = 1
输出：3
示例 3：

输入：n = 10101
输出：183236316


提示：

1 <= n <= 105
通过次数7,519提交次数15,426
 */

import java.util.ArrayList;

public class 学生出勤记录II_552 {

    public int checkRecord(int n) {
        final int MOD = 1000000007;
        int[][][] dp = new int[n + 1][2][3]; // 长度，A 的数量，结尾连续 L 的数量
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // 以 P 结尾的数量,则前 i 天和前 i-1 天的出勤记录相比，‘A’ 的数量不变，结尾连续 ‘L’ 的数量清零
            for (int j = 0; j <= 1; j++) {
                for (int k = 0; k <= 2; k++) {// 结尾连续 ‘L’ 的数量为 0 | 1 | 2
                    dp[i][j][0] = (dp[i][j][0] + dp[i - 1][j][k]) % MOD;
                }
            }
            // 以 A 结尾的数量，结尾连续 ‘L’ 的数量清零
            for (int k = 0; k <= 2; k++) {
                dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][k]) % MOD;
            }
            // 以 L 结尾的数量
            for (int j = 0; j <= 1; j++) {
                for (int k = 1; k <= 2; k++) { // 至少为1个 ‘L’， 所以 k 从 1 开始
                    dp[i][j][k] = (dp[i][j][k] + dp[i - 1][j][k - 1]) % MOD;
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= 1; j++) {
            for (int k = 0; k <= 2; k++) {
                sum = (sum + dp[n][j][k]) % MOD;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();

    }

    class Solution {

        int MOD = 1000000007;

        public int checkRecord(int n) {
            long[][] dp = new long[2][3]; // A L 的数量
            // 初始值
            dp[0][0] = 1;
            dp[1][0] = 1;
            dp[0][1] = 1;

            for (int i = 1; i < n; i++) {
                long[][] newDp = new long[2][3];
                // 本次填入P，前一天累计了 0 个 A
                newDp[0][0] = (dp[0][0] + dp[0][1] + dp[0][2]) % MOD;
                // 本次填入P，前一天累计了 1 个 A
                // 本次填入A，前一天没有累计A都能转移过来
                newDp[1][0] = (dp[1][0] + dp[1][1] + dp[1][2] + dp[0][0] + dp[0][1] + dp[0][2]) % MOD;
                // 本次填入L，前一天最多只有一个连续的L，分成四种情况
                newDp[0][1] = dp[0][0];
                newDp[0][2] = dp[0][1];
                newDp[1][1] = dp[1][0];
                newDp[1][2] = dp[1][1];

                dp = newDp;
            }
            // 计算结果，即最后一天的所有状态相加
            long ans = 0;
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 3; j++) {
                    ans = (ans + dp[i][j]) % MOD;
                }
            }
            return (int) ans;
        }
    }

/*    作者：tong-zhu
    链接：https://leetcode-cn.com/problems/student-attendance-record-ii/solution/tong-ge-lai-shua-ti-la-yi-ti-liu-jie-dfs-s5fa/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}