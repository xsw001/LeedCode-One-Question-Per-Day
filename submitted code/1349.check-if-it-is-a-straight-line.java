//
// @lc app=leetcode.cn id=1349 lang=java
//
// [1349] check-if-it-is-a-straight-line
//
class Solution {
    public boolean checkStraightLine(int[][] coordinates) {
        int len = coordinates.length;

        for (int i = 1; i < len - 1; ++i) {
            int y1 = coordinates[i][1] -coordinates[i-1][1];
            int x1 = coordinates[i][0] -coordinates[i-1][0];
            int y2 = coordinates[i+1][1] -coordinates[i][1];         
            int x2 = coordinates[i+1][0] -coordinates[i][0];
            if (y1*x2 != y2*x1) {
                return false;
            }
            
        }
        return true;

    }
}

// @lc code=end