package xsw.Nov_Dec;

/*
547. 省份数量
有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。

省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。

返回矩阵中 省份 的数量。



示例 1：


输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2
示例 2：


输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3


提示：

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] 为 1 或 0
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]
*/

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class LeedCode547图_遍历 {

    static class Solution1 {
        public int findCircleNum(int[][] isConnected) {
            int n = isConnected.length;
            int[] parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isConnected[i][j] == 1)
                        union(i, j, parent);
                }
            }
/*            HashSet<Integer> set = new HashSet<Integer>();
            for (int i = 0; i < n; i++) {
                set.add(find(i,parent));
            }
            return set.size();*/
            int circles = 0;
            for (int i = 0; i < n; i++) {
                if (parent[i] == i) {
                    circles++;
                }
            }
            return circles;
        }

        private void union(int i, int j, int[] parent) {
            if (find(i, parent) == find(j, parent))
                return;
            parent[find(i, parent)] = find(j, parent);
        }

        private int find(int x, int[] parent) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

    }

    public static void main(String[] args) {
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        Solution solution = new Solution();
        int circleNum = solution.findCircleNum(isConnected);
        System.out.println(circleNum);
    }

    //深度优先搜索
    static class Solution2 {
        public int findCircleNum(int[][] isConnected) {
            // int[][] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
            int n = isConnected.length;
            // 定义 boolean 数组标识顶点是否被访问
            boolean[] visited = new boolean[n];
            // 定义 cnt 来累计遍历过的连通域的数量
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                // 若当前顶点 i 未被访问，说明又是一个新的连通域，则遍历新的连通域且cnt+=1.
                if (!visited[i]) {
                    cnt++;
                    dfs(i, isConnected, visited);
                }
            }
            return cnt;
        }

        private void dfs(int i, int[][] isConnected, boolean[] visited) {
            // 对当前顶点 i 进行访问标记
            visited[i] = true;

            // 继续遍历与顶点 i 相邻的顶点（使用 visited 数组防止重复访问）
            for (int j = 0; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1 && !visited[j]) {
                    dfs(j, isConnected, visited);
                }
            }
        }
    }

    static class Solution {
        public int findCircleNum(int[][] isConnected) {
            // int[][] isConnected 是无向图的邻接矩阵，n 为无向图的顶点数量
            int n = isConnected.length;
            // 定义 boolean 数组标识顶点是否被访问
            boolean[] visited = new boolean[n];

            // 定义 cnt 来累计遍历过的连通域的数量
            int cnt = 0;
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                // 若当前顶点 i 未被访问，说明又是一个新的连通域，则bfs新的连通域且cnt+=1.
                if (!visited[i]) {
                    cnt++;
                    queue.offer(i);
                    visited[i] = true;
                    while (!queue.isEmpty()) {
                        int v = queue.poll();
                        for (int w = 0; w < n; w++) {
                            if (isConnected[v][w] == 1 && !visited[w]) {
                                visited[w] = true;
                                queue.offer(w);
                            }
                        }
                    }
                }
            }
            return cnt;
        }
    }
}
