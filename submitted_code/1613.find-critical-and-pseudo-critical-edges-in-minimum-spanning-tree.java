//
// @lc app=leetcode.cn id=1613 lang=java
//
// [1613] find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree
//
class Solution {
      public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        //将每条边对应的编号记录
        Map<int[], Integer> edgeToNum = new HashMap<>();
        int num = 0;
        for (int[] edge : edges) {
            edgeToNum.put(edge, num++);
        }
        UnionFind unionFind = new UnionFind(n);
        //由于edges是稀疏图,所以使用Kruskal算法,先将边从小到大排序
        Arrays.sort(edges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });
        List<Integer> MST = new ArrayList<>();
        int val = 0;//MST的权重
        //先生成一棵MST
        for (int i = 0; i < edges.length; i++) {
            if (unionFind.union(edges[i][0], edges[i][1])) { //没有形成环
                MST.add(i);
                val += edges[i][2];
                if (MST.size() == n - 1) { //已经构成MST了
                    break;
                }
            }
        }
        List<Integer> valEdge = new ArrayList<>(); //保存关键边
        List<Integer> fakeEdge = new ArrayList<>(); //保存伪关键边
        //依次遍历edges中的所有边,确定它们的'身份'
        for (int i = 0; i < edges.length; i++) {
            UnionFind uf = new UnionFind(n);
            int curCount = 0;
            int curVal = 0;
            if (!MST.contains(i)) { //不是MST中的边,直接加入uf,用于判断是否为伪关键边
                uf.union(edges[i][0], edges[i][1]);
                curCount++;
                curVal += edges[i][2];
            }
            for (int j = 0; j < edges.length; j++) {
                if (j == i) { //不属于MST就已经加过了,属于MST不用加(用于判断是否为关键边)
                    continue;
                }
                if (uf.union(edges[j][0], edges[j][1])) {
                    curCount++;
                    curVal += edges[j][2];
                    if (curCount == n - 1) {
                        break;
                    }
                }
            }
            if (MST.contains(i) && (curVal > val || curCount < n - 1)) { 
                //新的MST权值变大或者没有连通所有点,则这条边的是关键边
                valEdge.add(edgeToNum.get(edges[i]));
            } else if (curCount == n - 1 && curVal == val) {
                fakeEdge.add(edgeToNum.get(edges[i]));
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        res.add(valEdge);
        res.add(fakeEdge);
        return res;
    }

    class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;

            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (rank[rootX] == rank[rootY]) {
                parent[rootX] = rootY;
                rank[rootY]++;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
            }
            return true;
        }
    }
}

// @lc code=end