package xsw.August;
/*
787. K 站中转内最便宜的航班
有 n 个城市通过一些航班连接。给你一个数组 flights ，其中 flights[i] = [fromi, toi, pricei] ，表示该航班都从城市 fromi 开始，以价格 toi 抵达 pricei。

现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到出一条最多经过 k 站中转的路线，使得从 src 到 dst 的 价格最便宜 ，并返回该价格。 如果不存在这样的路线，则输出 -1。



示例 1：

输入:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 1
输出: 200
解释:
城市航班图如下


从城市 0 到城市 2 在 1 站中转以内的最便宜价格是 200，如图中红色所示。
示例 2：

输入:
n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
src = 0, dst = 2, k = 0
输出: 500
解释:
城市航班图如下


从城市 0 到城市 2 在 0 站中转以内的最便宜价格是 500，如图中蓝色所示。


提示：

1 <= n <= 100
0 <= flights.length <= (n * (n - 1) / 2)
flights[i].length == 3
0 <= fromi, toi < n
fromi != toi
1 <= pricei <= 104
航班没有重复，且不存在自环
0 <= src, dst, k < n
src != dst
通过次数20,709提交次数57,749
 */

import java.util.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class K站中转内最便宜的航班_787 {

    public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int[] flight : flights) {
            dp[flight[0]][flight[1]] = flight[2];
        }
        LinkedList<int[]> list = new LinkedList<>();
        list.add(new int[]{src, src, 0});
        int v = 0;
        while (!list.isEmpty() && v <= k) {
            ++v;
            int size = list.size();
            for (int i = 0; i < size; i++) {
                int[] arr = list.poll();
                for (int[] flight : flights) {
                    if (flight[0] == arr[1]) {
                        if (dp[arr[0]][flight[1]] == -1)
                            dp[arr[0]][flight[1]] = arr[2] + flight[2];
                        else
                            dp[arr[0]][flight[1]] = Math.min(dp[arr[0]][flight[1]], arr[2] + flight[2]);

                            list.add(new int[]{arr[0], flight[1], dp[arr[0]][flight[1]]});
                    }
                }
            }
        }
        return dp[src][dst];
    }

    public static void main(String[] args) {
        int[][] data = {{0, 12, 28}, {5, 6, 39}, {8, 6, 59}, {13, 15, 7}, {13, 12, 38}, {10, 12, 35}, {15, 3, 23}, {7, 11, 26}, {9, 4, 65}, {10, 2, 38}, {4, 7, 7}, {14, 15, 31}, {2, 12, 44}, {8, 10, 34}, {13, 6, 29}, {5, 14, 89}, {11, 16, 13}, {7, 3, 46}, {10, 15, 19}, {12, 4, 58}, {13, 16, 11}, {16, 4, 76}, {2, 0, 12}, {15, 0, 22}, {16, 12, 13}, {7, 1, 29}, {7, 14, 100}, {16, 1, 14}, {9, 6, 74}, {11, 1, 73}, {2, 11, 60}, {10, 11, 85}, {2, 5, 49}, {3, 4, 17}, {4, 9, 77}, {16, 3, 47}, {15, 6, 78}, {14, 1, 90}, {10, 5, 95}, {1, 11, 30}, {11, 0, 37}, {10, 4, 86}, {0, 8, 57}, {6, 14, 68}, {16, 8, 3}, {13, 0, 65}, {2, 13, 6}, {5, 13, 5}, {8, 11, 31}, {6, 10, 20}, {6, 2, 33}, {9, 1, 3}, {14, 9, 58}, {12, 3, 19}, {11, 2, 74}, {12, 14, 48}, {16, 11, 100}, {3, 12, 38}, {12, 13, 77}, {10, 9, 99}, {15, 13, 98}, {15, 12, 71}, {1, 4, 28}, {7, 0, 83}, {3, 5, 100}, {8, 9, 14}, {15, 11, 57}, {3, 6, 65}, {1, 3, 45}, {14, 7, 74}, {2, 10, 39}, {4, 8, 73}, {13, 5, 77}, {10, 0, 43}, {12, 9, 92}, {8, 2, 26}, {1, 7, 7}, {9, 12, 10}, {13, 11, 64}, {8, 13, 80}, {6, 12, 74}, {9, 7, 35}, {0, 15, 48}, {3, 7, 87}, {16, 9, 42}, {5, 16, 64}, {4, 5, 65}, {15, 14, 70}, {12, 0, 13}, {16, 14, 52}, {3, 10, 80}, {14, 11, 85}, {15, 2, 77}, {4, 11, 19}, {2, 7, 49}, {10, 7, 78}, {14, 6, 84}, {13, 7, 50}, {11, 6, 75}, {5, 10, 46}, {13, 8, 43}, {9, 10, 49}, {7, 12, 64}, {0, 10, 76}, {5, 9, 77}, {8, 3, 28}, {11, 9, 28}, {12, 16, 87}, {12, 6, 24}, {9, 15, 94}, {5, 7, 77}, {4, 10, 18}, {7, 2, 11}, {9, 5, 41}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(findCheapestPrice(17, data, 3, 14, 13));
        System.out.println(findCheapestPrice1(17, data, 3, 14, 13));
    }

    public static int findCheapestPrice1(int n, int[][] flights, int src, int dst, int k) {
        int[][] graph = new int[n][n];
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        for (int[] flight : flights) {
            graph[flight[0]][flight[1]] = flight[2];
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{k, src, 0});
        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            if (info[0] >= 0 && graph[info[1]].length != 0) {
                int[] tmpDst = graph[info[1]];
                for (int i = 0; i < tmpDst.length; i++) {
                    if (tmpDst[i] == 0) continue;
                    int nextPrice = info[2] + tmpDst[i];
                    if (nextPrice >= result[i]) {
                        continue;
                    }
                    result[i] = nextPrice;
                    queue.offer(new int[]{info[0] - 1, i, nextPrice});
                }
            }
        }
        return result[dst] == Integer.MAX_VALUE ? -1 : result[dst];
    }

    static class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            final int INF = 10000 * 101 + 1;
            int[][] f = new int[k + 2][n];
            for (int i = 0; i < k + 2; ++i) {
                Arrays.fill(f[i], INF);
            }
            f[0][src] = 0;
            for (int t = 1; t <= k + 1; ++t) {
                for (int[] flight : flights) {
                    int j = flight[0], i = flight[1], cost = flight[2];
                    f[t][i] = Math.min(f[t][i], f[t - 1][j] + cost);
                }
            }
            int ans = INF;
            for (int t = 1; t <= k + 1; ++t) {
                ans = Math.min(ans, f[t][dst]);
            }
            return ans == INF ? -1 : ans;
        }
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/solution/k-zhan-zhong-zhuan-nei-zui-bian-yi-de-ha-abzi/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
}