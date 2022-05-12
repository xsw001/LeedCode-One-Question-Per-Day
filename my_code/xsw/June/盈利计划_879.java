package xsw.June;
/*
879. 盈利计划
集团里有 n 名员工，他们可以完成各种各样的工作创造利润。

第 i 种工作会产生 profit[i] 的利润，它要求 group[i] 名成员共同参与。如果成员参与了其中一项工作，就不能参与另一项工作。

工作的任何至少产生 minProfit 利润的子集称为 盈利计划 。并且工作的成员总数最多为 n 。

有多少种计划可以选择？因为答案很大，所以 返回结果模 10^9 + 7 的值。



示例 1：

输入：n = 5, minProfit = 3, group = [2,2], profit = [2,3]
输出：2
解释：至少产生 3 的利润，该集团可以完成工作 0 和工作 1 ，或仅完成工作 1 。
总的来说，有两种计划。
示例 2：

输入：n = 10, minProfit = 5, group = [2,3,5], profit = [6,7,8]
输出：7
解释：至少产生 5 的利润，只要完成其中一种工作就行，所以该集团可以完成任何工作。
有 7 种可能的计划：(0)，(1)，(2)，(0,1)，(0,2)，(1,2)，以及 (0,1,2) 。


提示：

1 <= n <= 100
0 <= minProfit <= 100
1 <= group.length <= 100
1 <= group[i] <= 100
profit.length == group.length
0 <= profit[i] <= 100
通过次数4,328提交次数9,805
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 盈利计划_879 {

    public static int profitableSchemes1(int V, int minProfit, int[] group, int[] profit) {
        // 物品的数量为N
        int N = group.length;
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
            v[i] = group[i - 1];
            w[i] = profit[i - 1];
        }

        int[] f = new int[V + 1];
        int[] g = new int[V + 1];
        g[0] = 1;
        for (int i = 1; i <= N; i++) {
            f[i] = Integer.MIN_VALUE;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; --j) {
                int t = Math.max(f[j], f[j - v[i]] + w[i]);
                int s = 0;
                if (t == f[j])
                    s += g[j];
                if (t == f[j - v[i]] + w[i])
                    s += g[j - v[i]];
                f[j] = t;
                g[j] = s % 1000000007;
            }
        }
        System.out.println(Arrays.toString(f));
        System.out.println(Arrays.toString(g));
        int ans = 0;
        for (int i = 0; i < f.length; i++) {
            if (f[i] >= minProfit) {
                ans += g[i];
                ans %= 1000000007;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int V = 10;
        int minProfit = 5;
        int[] group = {2, 3, 5};
        int[] profit = {6, 7, 8};
        System.out.println(profitableSchemes(V, minProfit, group, profit));


        // 物品的数量为N
        int N = group.length;
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
            v[i] = group[i - 1];
            w[i] = profit[i - 1];
        }
        int[] dp = new int[V + 1];
        dp[0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        System.out.println(Arrays.toString(dp));
    }

    //三个维度分别为：当前可选择的工作，已选择的小组员工人数，以及目前状态的工作获利下限。
    //dp[i][j][k] 表示在前 i 个工作中选择了 j 个员工，并且满足工作利润至少为 k 的情况下的盈利计划的总数目
    public static int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int len = group.length, MOD = (int)1e9 + 7;
        int[][][] dp = new int[len + 1][n + 1][minProfit + 1];
        dp[0][0][0] = 1;
        for (int i = 1; i <= len; i++) { // 枚举工作
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = 0; j <= n; j++) {  // 枚举员工
                for (int k = 0; k <= minProfit; k++) { //  枚举利润
                    if (j < members) { // 员工小于所需人数，没法开工
                        dp[i][j][k] = dp[i - 1][j][k];
                    } else {
                        dp[i][j][k] = (dp[i - 1][j][k] + dp[i - 1][j - members][Math.max(0, k - earn)]) % MOD;
                    }
                }
            }
        }
        int sum = 0;
        for (int j = 0; j <= n; j++) {
            sum = (sum + dp[len][j][minProfit]) % MOD;
        }
        return sum;
    }
}