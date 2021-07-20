//
// @lc app=leetcode.cn id=1324 lang=java
//
// [1324] where-will-the-ball-fall
//
class Solution {
    public static int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int[] step = helper(grid, i);
            if (step[0] == m)
                result[i] = step[1];
            else
                result[i] = -1;
        }
        if (grid[0][n - 1] == 1) {
            result[n - 1] = -1;
        }
        if (grid[0][0] == -1) {
            result[0] = -1;
        }
        return result;
    }
    private static int[] helper(int[][] grid, int index) {
        int m = grid.length;
        int n = grid[0].length;
        int i = 0;
        while (i < m) {
            if ((index < n - 1 && grid[i][index] == 1 && grid[i][index + 1] == -1) || (index > 0 && grid[i][index] == -1 && grid[i][index - 1] == 1))
                return new int[]{i, index};
            if (grid[i][index] == 1) {
                index++;
            } else if (grid[i][index] == -1){
                index--;
            }
            if(index >= n || index < 0)
                break;
            ++i;
        }
        return new int[]{i, index};
    }
}
// @lc code=end