//
// @lc app=leetcode.cn id=463 lang=java
//
// [463] island-perimeter
//
class Solution {
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public int islandPerimeter(int[][] grid) {
        int m = 0, n= 0;
        if(grid == null || (m = grid.length) == 0 || (n = grid[0].length) == 0) return 0;

        int count = 0;
        int edge = 0; 
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 0) continue;
                count++;

                if(j+1 < n && grid[i][j+1] == 1)    edge++; 

                if(i+1 < m && grid[i+1][j] == 1)    edge++; 
            }
        }

        return 4 * count - 2 * edge;
    }
}
// @lc code=end