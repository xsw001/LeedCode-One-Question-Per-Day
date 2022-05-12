package xsw.April.团队赛;
/*
3. 电动车游城市
通过的用户数2
尝试过的用户数4
用户总通过次数2
用户总提交次数5
题目难度Hard
小明的电动车电量充满时可行驶距离为 cnt，每行驶 1 单位距离消耗 1 单位电量，且花费 1 单位时间。小明想选择电动车作为代步工具。地图上共有 N 个景点，景点编号为 0 ~ N-1。他将地图信息以 [城市 A 编号,城市 B 编号,两城市间距离] 格式整理在在二维数组 paths，表示城市 A、B 间存在双向通路。初始状态，电动车电量为 0。每个城市都设有充电桩，charge[i] 表示第 i 个城市每充 1 单位电量需要花费的单位时间。请返回小明最少需要花费多少单位时间从起点城市 start 抵达终点城市 end。

示例 1：

输入：paths = [[1,3,3],[3,2,1],[2,1,3],[0,1,4],[3,0,5]], cnt = 6, start = 1, end = 0, charge = [2,10,4,1]

输出：43

解释：最佳路线为：1->3->0。
在城市 1 仅充 3 单位电至城市 3，然后在城市 3 充 5 单位电，行驶至城市 5。
充电用时共 3*10 + 5*1= 35
行驶用时 3 + 5 = 8，此时总用时最短 43。
image.png

示例 2：

输入：paths = [[0,4,2],[4,3,5],[3,0,5],[0,1,5],[3,2,4],[1,2,8]], cnt = 8, start = 0, end = 2, charge = [4,1,1,3,2]

输出：38

解释：最佳路线为：0->4->3->2。
城市 0 充电 2 单位，行驶至城市 4 充电 8 单位，行驶至城市 3 充电 1 单位，最终行驶至城市 2。
充电用时 4*2+2*8+3*1 = 27
行驶用时 2+5+4 = 11，总用时最短 38。

提示：

1 <= paths.length <= 200
paths[i].length == 3
2 <= charge.length == n <= 100
0 <= path[i][0],path[i][1],start,end < n
1 <= cnt <= 100
1 <= path[i][2] <= cnt
1 <= charge[i] <= 100
题目保证所有城市相互可以到达
 */

import java.util.*;

public class hard_3 {

    public static int electricCarPlan(int[][] paths, int cnt, int start, int end, int[] charge) {
        final int INF = 1 << 30;

        int n = charge.length;
        List<List<Edge>> g = new ArrayList<List<Edge>>();

        for (int i = 0; i < n; i++) {
            g.add(new ArrayList<Edge>());
        }

        for (int[] path : paths) {
            int from = path[0];
            int to = path[1];
            int dis = path[2];

            g.get(from).add(new Edge(to, dis));
            g.get(to).add(new Edge(from, dis));
        }

        Queue<Heapnode> heap = new PriorityQueue<Heapnode>(11, new Comparator<Heapnode>() {
            public int compare(Heapnode a, Heapnode b) {
                return a.cost - b.cost;
            }
        });

        int[][] d = new int[n][cnt + 1];
        boolean[][] use = new boolean[n][cnt + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= cnt; j++) {
                d[i][j] = INF;
            }
        }

        d[start][0] = 0;
        heap.add(new Heapnode(start, 0, 0));
        while (!heap.isEmpty()) {
            Heapnode h = heap.poll();
            int u = h.num;
            int remain = h.remain;
            if (!use[u][remain]) {
                use[u][remain] = true;
                for (int i = remain + 1; i <= cnt; i++) {
                    if (d[u][remain] + (i - remain) * charge[u] < d[u][i]) {
                        d[u][i] = d[u][remain] + (i - remain) * charge[u];
                        heap.add(new Heapnode(u, d[u][i], i));
                    }
                }

                for (Edge e : g.get(u)) {
                    int v = e.to;
                    int dis = e.dis;
                    if ((remain >= dis) && (d[u][remain] + dis < d[v][remain - dis])) {
                        d[v][remain - dis] = d[u][remain] + dis;
                        heap.add(new Heapnode(v, d[v][remain - dis], remain - dis));
                    }
                }
            }
        }

        return d[end][0];
    }

    static class Heapnode {
        int num;
        int cost;
        int remain;

        public Heapnode(int num, int cost, int remain) {
            this.num = num;
            this.cost = cost;
            this.remain = remain;
        }
    }

    static class Edge {
        int to;
        int dis;

        public Edge(int to, int dis) {
            this.to = to;
            this.dis = dis;
        }
    }

    public static void main(String[] args) {
        int[] data = {3, 2, 3, 8, 8, 2, 3};

    }

}