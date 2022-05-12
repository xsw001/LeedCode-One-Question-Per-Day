package xsw.Nov_Dec;
/*
778. 水位上升的泳池中游泳
在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。

现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。

你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？



示例 1:

输入: [[0,2],[1,3]]
输出: 3
解释:
时间为0时，你位于坐标方格的位置为 (0, 0)。
此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。

等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
示例2:

输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
输出: 16
解释:
 0  1  2  3  4
24 23 22 21  5
12 13 14 15 16
11 17 18 19 20
10  9  8  7  6

最终的路线用加粗进行了标记。
我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的


提示:

2 <= N <= 50.
grid[i][j] 是 [0, ..., N*N - 1] 的排列。
*/

import java.util.*;

public class LeedCode778 {
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
            System.out.println(i);
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

    public static void main(String[] args) {
        int[][] a = {{0, 1, 2, 3, 21}, {24, 23, 22, 4, 5}, {12, 19, 14, 15, 16}, {11, 17, 18, 13, 20}, {10, 9, 8, 7, 6}};
        int swim = swimInWater1(a);
        System.out.println(swim);
    }

    //很强，优先队列
    //所有的能走的点中我们永远只走水位最低的点，每到一个点，我们再记录所有的周围能走的点，并再次选择所有能走的点中水位最低的点。
    public static int swimInWater1(int[][] grid) {
        int m = grid.length, n = grid[0].length, ans = 0;
        PriorityQueue<int[]> pr = new PriorityQueue<>(Comparator.comparingInt(a -> grid[a[0]][a[1]]));
        boolean[][] used = new boolean[m][n];
        pr.add(new int[]{0, 0});
        used[0][0] = true;
        while (!pr.isEmpty()) {
            int[] p = pr.poll();
            System.out.println(grid[p[0]][p[1]]);
            ans = Math.max(ans, grid[p[0]][p[1]]);
            if (p[0] == m - 1 && p[1] == n - 1) {
                return ans;
            }
            for (int[] d : new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}) {
                int x = p[0] + d[0], y = p[1] + d[1];
                if (x < 0 || y < 0 || x >= m || y >= n || used[x][y]) {
                    continue;
                }
                pr.add(new int[]{x, y});
                used[x][y] = true;
            }
        }
        return -1;
    }
}
