//
// @lc app=leetcode.cn id=2035 lang=java
//
// [2035] count-sub-islands
//
class Solution {
    boolean[][] visited;
    int m, n;
    int count = 0;
    int[][] grid1;
    int[][] grid2;

    boolean dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || visited[i][j] || grid2[i][j] == 0) return true;
        visited[i][j] = true;
        boolean flag = dfs(i - 1, j) & dfs(i + 1, j) & dfs(i, j - 1) & dfs(i, j + 1);
        return flag && grid1[i][j] == 1;
    }

    public int countSubIslands(int[][] grid1, int[][] grid2) {
        this.grid1 = grid1;
        this.grid2 = grid2;
        this.m = grid1.length;
        this.n = grid1[0].length;
        visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid2[i][j] == 1) {
                    if (dfs(i, j)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}

// @lc code=end