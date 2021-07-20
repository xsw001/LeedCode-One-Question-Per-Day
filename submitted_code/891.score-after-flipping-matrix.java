//
// @lc app=leetcode.cn id=891 lang=java
//
// [891] score-after-flipping-matrix
//
class Solution {
    public int matrixScore(int[][] A) {
        int columns = A.length;
        int rows = A[0].length;
        for (int i = 0; i < columns; i++) {
            if (A[i][0] == 0) {
                for (int j = 0; j < rows; j++) {
                    if (A[i][j] == 0)
                        A[i][j] = 1;
                    else
                        A[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(A));
        for (int j = 1; j < rows; j++) {
            int count = 0;
            for (int[] ints : A) {
                if (ints[j] == 1)
                    ++count;
            }
            if (count < columns / 2+1) {
                for (int i = 0; i < columns; i++) {
                    if (A[i][j] == 0)
                        A[i][j] = 1;
                    else
                        A[i][j] = 0;
                }
            }
        }
        System.out.println(Arrays.deepToString(A));
        int score = 0;
        for (int[] a : A) {
            int mi = rows-1;
            for (int i : a) {
                score += i*Math.pow(2,mi--);
            }
        }
        return score;
    }
}
// @lc code=end