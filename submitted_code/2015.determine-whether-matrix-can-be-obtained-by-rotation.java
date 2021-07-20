//
// @lc app=leetcode.cn id=2015 lang=java
//
// [2015] determine-whether-matrix-can-be-obtained-by-rotation
//
class Solution {
    public static boolean findRotation(int[][] mat, int[][] target) {
        for (int i = 0; i < 4; i++) {
            if(Arrays.deepEquals(mat, target)){
                return true;
            }
            transfer(mat);
        }
        return  false;
    }

    private static void transfer(int[][] mat) {
        int n = mat.length;
        for (int i = 0; i < n/2; i++) {
            int[] temp = mat[i];
            mat[i] = mat[n-i-1];
            mat[n-i-1] = temp;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = mat[i][j];
                mat[i][j] = mat[j][i];
                mat[j][i] = temp;
            }
        }
    }
}
// @lc code=end