package dragon2024.march;

import org.junit.Test;

import java.util.*;

/**
 * 2368. 受限条件下可到达节点的数目
 * 现有一棵由 n 个节点组成的无向树，节点编号从 0 到 n - 1 ，共有 n - 1 条边。
 * 给你一个二维整数数组 edges ，长度为 n - 1 ，其中 edges[i] = [ai, bi] 表示树中节点 ai 和 bi 之间存在一条边。另给你一个整数数组 restricted 表示 受限 节点。
 * 在不访问受限节点的前提下，返回你可以从节点 0 到达的 最多 节点数目。
 * 注意，节点 0 不 会标记为受限节点。

 * 示例 1：
 * 输入：n = 7, edges = [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], restricted = [4,5]
 * 输出：4
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,1,2,3] 可以从节点 0 到达。
 * 示例 2：
 *
 *
 * 输入：n = 7, edges = [[0,1],[0,2],[0,5],[0,4],[3,2],[6,5]], restricted = [4,2,1]
 * 输出：3
 * 解释：上图所示正是这棵树。
 * 在不访问受限节点的前提下，只有节点 [0,5,6] 可以从节点 0 到达。
 *
 *
 * 提示：
 *
 * 2 <= n <= 105
 * edges.length == n - 1
 * edges[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * edges 表示一棵有效的树
 * 1 <= restricted.length < n
 * 1 <= restricted[i] < n
 * restricted 中的所有值 互不相同
 *
 * @create 2022/3/24 15:00
 */
public class m02_受限条件下可到达节点的数目2368 {

    @Test
    public void test() throws Exception
    {
        int n = 7;
        int[][] edges = {{0,1},{0,2},{0,5},{0,4},{3,2},{6,5}};
        int[] restricted = {4,2,1};
        System.out.println(reachableNodes(n, edges, restricted));
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        HashSet<Integer> set = new HashSet<>();
        for (int i : restricted) {
            set.add(i);
        }
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] edge : edges) {
            Set<Integer> children = map.getOrDefault(edge[0], new HashSet<>());
            children.add(edge[1]);
            map.put(edge[0], children);
            Set<Integer> children1 = map.getOrDefault(edge[1], new HashSet<>());
            children1.add(edge[0]);
            map.put(edge[1], children1);
        }
        int ans = 1;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(0);
        while(!queue.isEmpty()){
            Integer node = queue.pollFirst();
            if(map.containsKey(node)){
                for (Integer next : map.get(node)) {
                    if(!set.contains(next)){
                        queue.addLast(next);
                        ++ans;
                    }
                }
            }
            set.add(node);
        }
        return ans;
    }

    //官方題解
    class SolutionReachableNodes {
        public int reachableNodes(int n, int[][] edges, int[] restricted) {
            boolean[] isrestricted = new boolean[n];
            for (int x : restricted) {
                isrestricted[x] = true;
            }

            UnionFind uf = new UnionFind(n);
            for (int[] v : edges) {
                if (isrestricted[v[0]] || isrestricted[v[1]]) {
                    continue;
                }
                uf.merge(v[0], v[1]);
            }
            return uf.count();
        }
    }

    class UnionFind {
        private int[] f;
        private int[] rank;

        public UnionFind(int n) {
            f = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                f[i] = i;
            }
        }

        public void merge(int x, int y) {
            int rx = find(x);
            int ry = find(y);
            if (rx != ry) {
                if (rank[rx] > rank[ry]) {
                    f[ry] = rx;
                } else if (rank[rx] < rank[ry]) {
                    f[rx] = ry;
                } else {
                    f[ry] = rx;
                    rank[rx]++;
                }
            }
        }

        public int find(int x) {
            if (x != f[x]) {
                x = find(f[x]);
            }
            return f[x];
        }

        public int count() {
            int cnt = 0;
            int rt = find(0);
            for (int i = 0; i < f.length; i++) {
                if (rt == find(i)) {
                    cnt++;
                }
            }
            return cnt;
        }
    }
}
