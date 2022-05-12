package xsw.July;
/*
LCP 07. 传递信息
小朋友 A 在和 ta 的小伙伴们玩传信息游戏，游戏规则如下：

有 n 名玩家，所有玩家编号分别为 0 ～ n-1，其中小朋友 A 的编号为 0
每个玩家都有固定的若干个可传信息的其他玩家（也可能没有）。传信息的关系是单向的（比如 A 可以向 B 传信息，但 B 不能向 A 传信息）。
每轮信息必须需要传递给另一个人，且信息可重复经过同一个人
给定总玩家数 n，以及按 [玩家编号,对应可传递玩家编号] 关系组成的二维数组 relation。返回信息从小 A (编号 0 ) 经过 k 轮传递到编号为 n-1 的小伙伴处的方案数；若不能到达，返回 0。

示例 1：

输入：n = 5, relation = [[0,2],[2,1],[3,4],[2,3],[1,4],[2,0],[0,4]], k = 3

输出：3

解释：信息从小 A 编号 0 处开始，经 3 轮传递，到达编号 4。共有 3 种方案
        分别是 0->2->0->4， 0->2->1->4， 0->2->3->4。

示例 2：

输入：n = 3, relation = [[0,2],[2,1]], k = 2

输出：0

解释：信息不能从小 A 处经过 2 轮传递到编号 2

限制：

2 <= n <= 10
1 <= k <= 5
1 <= relation.length <= 90, 且 relation[i].length == 2
0 <= relation[i][0],relation[i][1] < n 且 relation[i][0] != relation[i][1]
通过次数23,894提交次数31,795
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class 传递信息LCP07 {
    // 广度优先搜索
    public static int numWays(int n, int[][] relation, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] ints : relation) {
            ArrayList<Integer> list = map.getOrDefault(ints[0], new ArrayList<Integer>());
            list.add(ints[1]);
            map.put(ints[0], list);
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(0);
        int step = 0;
        int ans = 0;
        while (!queue.isEmpty()) {
            ++step;
            if (step > k)
                return ans;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int poll = queue.poll();
                for (int num : map.getOrDefault(poll, new ArrayList<Integer>())) {
                    if (num == n - 1 && k == step)
                        ++ans;
                    else
                        queue.add(num);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] data = {{0, 2}, {2, 1}, {3, 4}, {2, 3}, {1, 4}, {2, 0}, {0, 4}};
        System.out.println(numWayss(5, data, 3));
    }

    /*定义动态规划的状态 dp[i][j] 为经过 i 轮传递到编号 j 的玩家的方案数，其中 0≤i≤k，0≤j<n。
    对于传信息的关系 [src,dst]，如果第 i 轮传递到编号 src 的玩家
    则第 i+1 轮可以从编号 src 的玩家传递到编号 dst 的玩家
    因此在计算 dp[i+1][dst] 时，需要考虑可以传递到编号 dst 的所有玩家*/
    public int numWay(int n, int[][] relation, int k) {
        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 0; i < k; i++) {
            for (int[] edge : relation) {
                int src = edge[0], dst = edge[1];
                dp[i + 1][dst] += dp[i][src];
            }
        }
        return dp[k][n - 1];
    }

    // 深度优先搜索

    public static int numWayss(int n, int[][] relation, int k) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] ints : relation) {
            ArrayList<Integer> list = map.getOrDefault(ints[0], new ArrayList<Integer>());
            list.add(ints[1]);
            map.put(ints[0], list);
        }
        int step = 0;
        int ans = 0;
        dfs(map, 0, step, ans, n, k);
        return ans;
    }

    private static void dfs(HashMap<Integer, ArrayList<Integer>> map, int index, int step, int ans, int n, int k) {
        if (step == k) {
            if (index == n - 1)
                ++ans;
            return;
        }
        if (map.containsKey(index)) {
            for (Integer i : map.get(index)) {
                dfs(map, i, step + 1, ans, n, k);
            }
        }
    }
}
