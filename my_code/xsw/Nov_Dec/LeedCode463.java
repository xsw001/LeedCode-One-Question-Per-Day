package xsw.Nov_Dec;

/*
*463. 岛屿的周长
给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。

网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。

岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。
* 计算这个岛屿的周长。
*
*
* 输入:
[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

输出: 16

解释: 它的周长是下面图片中的 16 个黄色的边：
* */
public class LeedCode463 {

    public static void main(String[] args) {
        int[][] grid = {{0, 1, 0, 0},
                {1, 1, 1, 0}, {0, 1, 0, 0},
                {1, 1, 1, 0}};

        int i = islandPerimeter(grid);
        System.out.println(i);
        int j = islandPerimeter1(grid);
        System.out.println(j);
        int k = islandPerimeter2(grid);
        System.out.println(k);
    }

    public static int islandPerimeter(int[][] grid) {
        long start = System.nanoTime();
        //竟然先变成正方形......
        if (grid.length != grid[0].length) {
            int len = Math.max(grid.length, grid[0].length);
            int[][] grids = new int[len][len];

            for (int i = 0; i < grid.length; i++) {
                System.arraycopy(grid[i], 0, grids[i], 0, grid[0].length);
            }
            grid = grids;
        }

        int line = 0;
        int column = 0;
        int total = 0;
        for (int[] g : grid)
            for (int value : g)
                if (value == 1)
                    ++total;

        for (int j = 0; j < grid.length; ++j) {
            for (int i = 0; i < grid[j].length - 1; i++) {
                if (grid[j][i] == grid[j][i + 1] && grid[j][i] == 1)
                    line++;
                if (grid[i][j] == 1 && grid[i + 1][j] == grid[i][j])
                    column++;

            }
        }
        long last = System.nanoTime();
        System.out.println("执行时间为： " + (start - last));
        return total * 4 - (line + column) * 2;
    }

    public static int islandPerimeter1(int[][] grid) {
        long start = System.nanoTime();

        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    if (i == 0 || grid[i - 1][j] == 0) res++;
                    if (i == grid.length - 1 || grid[i + 1][j] == 0) res++;
                    if (j == 0 || grid[i][j - 1] == 0) res++;
                    if (j == grid[0].length - 1 || grid[i][j + 1] == 0) res++;
                }
            }
        }
        long last = System.nanoTime();
        System.out.println("执行时间为： " + (start - last));
        return res;
    }

    //和我的思路一样，但是代码完完全全的虐杀啊
    //要学会逆向思维，真的很重要
    public static int islandPerimeter2(int[][] grid) {
        long start = System.nanoTime();
        int m = 0, n = 0;
        if (grid == null || (m = grid.length) == 0 || (n = grid[0].length) == 0) return 0;

        int count = 0;
        int edge = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) continue;
                count++;

                if (j + 1 < n && grid[i][j + 1] == 1) edge++;

                if (i + 1 < m && grid[i + 1][j] == 1) edge++;
            }
        }
        long last = System.nanoTime();
        System.out.println("执行时间为： " + (start - last));

        return 4 * count - 2 * edge;
    }
}

