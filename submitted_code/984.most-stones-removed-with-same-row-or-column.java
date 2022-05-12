//
// @lc app=leetcode.cn id=984 lang=java
//
// [984] most-stones-removed-with-same-row-or-column
//
class Solution {
    public int removeStones(int[][] stones) {
        int len = stones.length;
        UnionFind unionFind = new UnionFind(20002);
        HashSet<Integer> set = new HashSet<>();
        for(int[] stone : stones){
            unionFind.union(stone[0],stone[1]+ 10001);
        }
        for(int i = 0; i < len; i++){  
            set.add(unionFind.find(stones[i][0]));
        }
        return len-set.size();
    }
    public class UnionFind {
        private final int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY){
                return;
            }
            parent[rootX] = rootY;
        }

        public int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }
    }
}
// @lc code=end