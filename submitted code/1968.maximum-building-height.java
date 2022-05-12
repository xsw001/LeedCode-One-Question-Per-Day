//
// @lc app=leetcode.cn id=1968 lang=java
//
// [1968] maximum-building-height
//
class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        if (restrictions.length == 0)
            return n - 1;
        Arrays.sort(restrictions, (o1, o2) -> o1[0] - o2[0]);
        restrictions[0][1] = Math.min(restrictions[0][1], restrictions[0][0] - 1);
        int m = restrictions.length;
        for (int i = 1; i < m; i++) {
            restrictions[i][1] = Math.min(restrictions[i][1], restrictions[i - 1][1] + restrictions[i][0] - restrictions[i - 1][0]);
        }
        for (int i = m - 1; i > 0; i--) {
            restrictions[i - 1][1] = Math.min(restrictions[i - 1][1], restrictions[i][1] + restrictions[i][0] - restrictions[i - 1][0]);
        }
        int max = restrictions[m - 1][1] + n - restrictions[m - 1][0];
        for (int i = 0; i < m - 1; i++) {
            //int diff0 = restrictions[i + 1][0] - restrictions[i][0];
            //int diff1 = Math.abs(restrictions[i + 1][1] - restrictions[i][1]);
            //int tmp = Math.max(restrictions[i][1], restrictions[i + 1][1]) + (diff0 - diff1) / 2;
            int tmp = (restrictions[i + 1][0] - restrictions[i][0] + restrictions[i + 1][1] + restrictions[i][1])/ 2;
            max = Math.max(max, tmp);
        }
        return max;
    }
}
// @lc code=end