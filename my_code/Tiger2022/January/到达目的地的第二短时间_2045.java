package Tiger2022.January;
/*
2045. 到达目的地的第二短时间
城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。

每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。所有信号灯都 同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。

第二小的值 是 严格大于 最小值的所有值中最小的值。

例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。

注意：

你可以 任意次 穿过任意顶点，包括 1 和 n 。
你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。


示例 1：

        

输入：n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
输出：13
解释：
上面的左图展现了给出的城市交通图。
右图中的蓝色路径是最短时间路径。
花费的时间是：
- 从节点 1 开始，总花费时间=0
- 1 -> 4：3 分钟，总花费时间=3
- 4 -> 5：3 分钟，总花费时间=6
因此需要的最小时间是 6 分钟。

右图中的红色路径是第二短时间路径。
- 从节点 1 开始，总花费时间=0
- 1 -> 3：3 分钟，总花费时间=3
- 3 -> 4：3 分钟，总花费时间=6
- 在节点 4 等待 4 分钟，总花费时间=10
- 4 -> 5：3 分钟，总花费时间=13
因此第二短时间是 13 分钟。
示例 2：



输入：n = 2, edges = [[1,2]], time = 3, change = 2
输出：11
解释：
最短时间路径是 1 -> 2 ，总花费时间 = 3 分钟
最短时间路径是 1 -> 2 -> 1 -> 2 ，总花费时间 = 11 分钟


提示：

2 <= n <= 104
n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
不含重复边
每个节点都可以从其他节点直接或者间接到达
1 <= time, change <= 103
通过次数6,774提交次数14,232
 */

import org.junit.Test;

import java.util.*;

public class 到达目的地的第二短时间_2045 {


    public int secondMinimum(int n, int[][] edges, int time, int change) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], f -> new ArrayList<Integer>()).add(edge[1]);
            map.computeIfAbsent(edge[1], f -> new ArrayList<Integer>()).add(edge[0]);
        }
        boolean[] secondVisited = new boolean[n + 1]; // 访问过一次（不是在同一个时间）
        int[] firstVisitedTime = new int[n + 1]; // 节点被访问的时间
        Arrays.fill(firstVisitedTime, -1);
        int num = -1, total_time = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addLast(1);
        while (!deque.isEmpty()) {
            int size = deque.size();
            total_time += time;
            for (int i = 0; i < size; i++) {
                int index = deque.pollFirst();
                ArrayList<Integer> list = map.get(index);
                for (int t : list) {
                    if (!secondVisited[t]) {// 如果没被访问过
                        deque.add(t);
                        if (firstVisitedTime[t] == -1)//第一次访问
                            firstVisitedTime[t] = total_time;
                        else if (firstVisitedTime[t] != -1 && total_time != firstVisitedTime[t])//不是同一时间被访问的
                            secondVisited[t] = true;
                    }
                    if (t == n) {
                        if (num == -1)
                            num = total_time;
                        else if (total_time > num)
                            return total_time;
                    }
                }
            }
            if ((total_time / change) % 2 == 1)
                total_time = (total_time / change + 1) * change;
        }
        return total_time;
    }

    @Test
    public void test() {
        int[][] data = {{1, 2}, {1, 3}, {1, 4}, {3, 4}, {4, 5}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(secondMinimum(5, data, 3, 5));
    }

    class Solution {
        public int secondMinimum(int n, int[][] edges, int time, int change) {
            List<Integer>[] graph = new List[n + 1];
            for (int i = 0; i <= n; i++) {
                graph[i] = new ArrayList<Integer>();
            }
            for (int[] edge : edges) {
                graph[edge[0]].add(edge[1]);
                graph[edge[1]].add(edge[0]);
            }

            // path[i][0] 表示从 1 到 i 的最短路长度，path[i][1] 表示从 1 到 i 的严格次短路长度
            int[][] path = new int[n + 1][2];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(path[i], Integer.MAX_VALUE);
            }
            path[1][0] = 0;
            Queue<int[]> queue = new ArrayDeque<int[]>();
            queue.offer(new int[]{1, 0});
            while (path[n][1] == Integer.MAX_VALUE) {
                int[] arr = queue.poll();
                int cur = arr[0], len = arr[1];
                for (int next : graph[cur]) {
                    if (len + 1 < path[next][0]) {
                        path[next][0] = len + 1;
                        queue.offer(new int[]{next, len + 1});
                    } else if (len + 1 > path[next][0] && len + 1 < path[next][1]) {
                        path[next][1] = len + 1;
                        queue.offer(new int[]{next, len + 1});
                    }
                }
            }

            int ret = 0;
            for (int i = 0; i < path[n][1]; i++) {
                if (ret % (2 * change) >= change) {
                    ret = ret + (2 * change - ret % (2 * change));
                }
                ret = ret + time;
            }
            return ret;
        }
    }

}