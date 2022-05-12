//
// @lc app=leetcode.cn id=794 lang=java
//
// [794] swim-in-rising-water
//
class Solution {
    public static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        int star = grid[0][0];
        int end = grid[n - 1][n - 1];
        Union union = new Union(n * n);
        int[] index = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                index[grid[i][j]] = i * n + j;
            }
        }
        //System.out.println(Arrays.toString(index));
        int before = -1;
        for (int i = 0; i < n * n; i++) {
            //System.out.println(i);
            int x = index[i] / n;
            int y = index[i] % n;
            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (inArea(newX, newY, n) && grid[newX][newY] <= i) {
                    //System.out.println(index[i] + " " + (newX * n + newY));
                    union.union(index[i], newX * n + newY);
                }

                if (union.isCon(0, n * n - 1)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private static boolean inArea(int x, int y, int N) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }


    static class Union {
        int[] parent;
        int[] rank;
        int time;

        public Union(int n) {
            this.time = 0;
            this.parent = new int[n];
            this.rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            return x == parent[x] ? x : (x = find(parent[x]));
        }

        public boolean union(int a, int b) {
            int pa = find(a), pb = find(b);
            if (pa == pb)
                return false;
            if (rank[pa] < rank[pb]) {
                int temp = pa;
                pa = pb;
                pb = temp;
            }
            parent[pb] = pa;
            rank[pa]++;
            time = Math.max(a, b);
            return true;
        }

        public boolean isCon(int a, int b) {
            return find(a) == find(b);
        }
    }
}
// @lc code=end