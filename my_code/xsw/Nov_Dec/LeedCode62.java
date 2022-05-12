package xsw.Nov_Dec;/*
62. 不同路径
一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。

问总共有多少条不同的路径？



例如，上图是一个7 x 3 的网格。有多少可能的路径？



示例 1:

输入: m = 3, n = 2
输出: 3
解释:
从左上角开始，总共有 3 条路径可以到达右下角。
1. 向右 -> 向右 -> 向下
2. 向右 -> 向下 -> 向右
3. 向下 -> 向右 -> 向右
示例 2:

输入: m = 7, n = 3
输出: 28


提示：

1 <= m, n <= 100
题目数据保证答案小于等于 2 * 10 ^ 9
*/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeedCode62 {

    //没问题，就是超时了
    public static int uniquePaths(int m, int n) {
        return path(m - 1, n - 1);
    }

    public static int path(int column, int row) {
        if (column == 0 || row == 0)
            return 1;
        return path(column - 1, row) + path(column, row - 1);
    }

    //优化一下,AC   但依旧是最慢的
    public static int uniquePaths2(int m, int n) {
        return path1(m - 1, n - 1, new HashMap<>());
    }

    public static int path1(int column, int row, Map<String, Integer> map) {
        if (column == 0 || row == 0)
            return 1;
        String key = column + "*" + row;
        if (map.containsKey(key))
            return map.get(key);
        int c = path1(column - 1, row,map);
        int r = path1(column, row - 1,map);
        int totla = c + r;
        map.put(key, totla);
        return totla;
    }

    public static void main(String[] args) {
        //int paths = uniquePaths(51, 9);
        int paths = uniquePaths2(3, 3);
        System.out.println(paths);
    }

    //组合数学
    /*从左上角到右下角的过程中，我们需要移动 m+n-2 次，其中有 m-1 次向下移动，n-1 次向右移动
    因此路径的总数，就等于从 m+n-2 次移动中选择 m-1 次向下移动的方案数，即组合数*/
    public static int uniquePaths1(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }

    //动态规划
    public static int uniquePaths_dp1(int m, int n) {//优化后的版本，空间复杂度为 O(n)
        /*
        cur[j] += cur[j-1] 即 cur[j] = cur[j-1] + cur[j]
        未赋值之前右边的cur[j] 始终表示当前行第i行的上一行第j列的值，
        赋值之后左边的cur[j]表示当前行第i行第j列的值，
        cur[j-1] 表示当前行第i行第j-1列的值
        (cur[j-1] 在计算cur[j]之前就已经计算了，所以表示的是当前行而不是上一行 )
        */
        int[] cur = new int[n];
        Arrays.fill(cur, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                cur[j] += cur[j - 1];
            }
        }
        return cur[n - 1];
    }

    public static int uniquePaths_dp2(int m, int n) {
        int[][] dp = new int[m][n];
        // 因为从（0,0）出发，边界上的点只能一直向右走或者向下走，所以到边界上的点只有一种走法
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }
}
