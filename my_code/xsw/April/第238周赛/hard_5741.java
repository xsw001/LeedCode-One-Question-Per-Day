package xsw.April.第238周赛;
/*
5741. 最高建筑高度 显示英文描述
通过的用户数32
尝试过的用户数62
用户总通过次数35
用户总提交次数87
题目难度Hard
在一座城市里，你需要建 n 栋新的建筑。这些新的建筑会从 1 到 n 编号排成一列。

这座城市对这些新建筑有一些规定：

每栋建筑的高度必须是一个非负整数。
第一栋建筑的高度 必须 是 0 。
任意两栋相邻建筑的高度差 不能超过  1 。
除此以外，某些建筑还有额外的最高高度限制。这些限制会以二维整数数组 restrictions 的形式给出，其中 restrictions[i] = [idi, maxHeighti] ，表示建筑 idi 的高度 不能超过 maxHeighti 。

题目保证每栋建筑在 restrictions 中 至多出现一次 ，同时建筑 1 不会 出现在 restrictions 中。

请你返回 最高 建筑能达到的 最高高度 。



示例 1：


输入：n = 5, restrictions = [[2,1],[4,1]]
输出：2
解释：上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,1,2] ，最高建筑的高度为 2 。
示例 2：


输入：n = 6, restrictions = []
输出：5
解释：上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,3,4,5] ，最高建筑的高度为 5 。
示例 3：


输入：n = 10, restrictions = [[5,3],[2,5],[7,4],[10,3]]
输出：5
解释：上图中的绿色区域为每栋建筑被允许的最高高度。
我们可以使建筑高度分别为 [0,1,2,3,3,4,4,5,4,3] ，最高建筑的高度为 5 。


提示：

2 <= n <= 109
0 <= restrictions.length <= min(n - 1, 105)
2 <= idi <= n
idi 是 唯一的 。
0 <= maxHeighti <= 109
 */

import java.util.*;

public class hard_5741 {

    /*超出时间限制*/
    public static int maxBuilding(int n, int[][] restrictions) {
        if (restrictions.length == 0)
            return n - 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int[] restriction : restrictions) {
            map.put(restriction[0] - 1, restriction[1]);
        }
        int[] dp = new int[n];
        int res = 0;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = dp[i - 1] + 1;
            if (map.containsKey(i)) {
                if (dp[i] > map.get(i)) {
                    dp[i] = map.get(i);
                    while (i > 0 && Math.abs(dp[i] - dp[i - 1]) > 1) {
                        dp[i - 1] = dp[i] + 1;
                        --i;
                    }
                }
            }
        }
        for (int i = 1; i < dp.length; i++)
            res = Math.max(dp[i], res);
        System.out.println(Arrays.toString(dp));
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        int[][] restrictions = {{5, 3}, {2, 5}, {7, 4}, {10, 3}};
        int[][] restrictions1 = {{2, 1}, {4, 1}};
        int[][] restrictions2 = {{8, 5}, {9, 5}, {6, 2}, {4, 0}, {3, 2}, {5, 3}, {7, 3}, {2, 4}};
        System.out.println(maxBuilding2(10, restrictions2));
    }

    /*超出空间限制 36 / 45 个通过测试用例
     */
    public static int maxBuilding1(int n, int[][] restrictions) {
        if (restrictions.length == 0)
            return n - 1;
        int[] dp = new int[n];
        boolean[] visited = new boolean[n];
        int end = 0;
        for (int[] restriction : restrictions) {
            dp[restriction[0] - 1] = restriction[1];
            visited[restriction[0] - 1] = true;
            end = Math.max(end, restriction[0] - 1);
        }
        System.out.println(Arrays.toString(dp));
        System.out.println(Arrays.toString(visited));
        int res = 0;
        for (int i = 1; i < dp.length; i++) {
            if (visited[i])
                dp[i] = Math.min(dp[i - 1] + 1, dp[i]);
            else
                dp[i] = dp[i - 1] + 1;
        }
        System.out.println(Arrays.toString(dp));
        for (int i = end; i > 0; i--) {
            if (Math.abs(dp[i] - dp[i - 1]) > 1)
                dp[i - 1] = dp[i] + 1;
        }
        for (int i = 1; i < dp.length; i++)
            res = Math.max(dp[i], res);
        System.out.println(Arrays.toString(dp));
        return res;
    }

    public static int maxBuilding2(int n, int[][] restrictions) {
        Arrays.sort(restrictions, (o1, o2) -> o1[0] - o2[0]);
        if (restrictions.length == 0)
            return n - 1;
        restrictions[0][1] = Math.min(restrictions[0][1], restrictions[0][0] - 1);
        int m = restrictions.length;
        for (int i = 1; i < m; i++) {
            restrictions[i][1] = Math.min(restrictions[i][1], restrictions[i - 1][1] + restrictions[i][0] - restrictions[i - 1][0]);
        }
        for (int i = m - 1; i > 0; i--) {
            restrictions[i - 1][1] = Math.min(restrictions[i - 1][1], restrictions[i][1] + restrictions[i][0] - restrictions[i - 1][0]);
        }
        int max = restrictions[m - 1][1] + n - restrictions[m - 1][0];//计算最高值在末尾的
        for (int i = 0; i < m - 1; i++) {
            int diff0 = restrictions[i + 1][0] - restrictions[i][0];
            int diff1 = Math.abs(restrictions[i + 1][1] - restrictions[i][1]);
            int tmp = Math.max(restrictions[i][1], restrictions[i + 1][1]) + (diff0 - diff1) / 2;
            //int tmp = (restrictions[i + 1][0] - restrictions[i][0] + restrictions[i + 1][1] + restrictions[i][1]) / 2;
            max = Math.max(max, tmp);
        }
        return max;
    }

    public int maxBuilding3(int n, int[][] R) {
        Arrays.sort(R, new Comparator<int []>() {
            public int compare(int[] a, int[] b) {
                if (a[1] > b[1]) {
                    return 1;
                }
                if (a[1] < b[1]) {
                    return -1;
                }
                if (a[0] > b[0]) {
                    return 1;
                }
                if (a[0] < b[0]) {
                    return -1;
                }
                return 0;
            }
        });
        // for (int i=0; i<R.length; i++) {
        //     System.out.println(R[i][0] + " " + R[i][1]);
        // }
        // return 1;
        int res = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(1, 0);
        for (int i=0; i<R.length; i++) {
            int k = R[i][0], v = R[i][1];
            // System.out.println(k + " " + v);
            int lk = map.lowerKey(k);
            int lv = map.get(lk);
            if (v-lv > k-lk) {
                v = k-lk+lv;
            }
            if (map.lastKey() > k) {
                int hk = map.higherKey(k);
                int hv = map.get(hk);
                if (v-hv > hk-k) {
                    v = hk-k+hv;
                }
            }
            res = Math.max(res, v);
            map.put(k, v);
        }
        Set<Integer> set = map.keySet();
        for (Integer k : set) {
            if (k != 1) {
                int lk = map.lowerKey(k);
                int v = map.get(k);
                int lv = map.get(lk);
                int dif = k - lk + 1;
                dif -= Math.abs(v-lv);
                res = Math.max(res, Math.max(v, lv) + Math.max(0, (dif-1)/2));
            }
        }
        if (!map.containsKey(n)) {
            int lk = map.lowerKey(n);
            int lv = map.get(lk);
            res = Math.max(res, n - lk + lv);
        }
        return res;
    }
}