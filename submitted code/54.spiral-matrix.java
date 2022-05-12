//
// @lc app=leetcode.cn id=54 lang=java
//
// [54] spiral-matrix
//
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> results = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int i = 0, j = 0;
        int count = m * n;
        int turn = 0;
        while (count > 0) {
            switch (turn) {
                case 0 -> {
                    while (j < n && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i][j++]);
                        --count;
                    }
                    --j;
                    ++i;
                }
                case 1 -> {
                    while (i < m && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i++][j]);
                        --count;
                    }
                    --i;
                    --j;
                }
                case 2 -> {
                    while (j > -1 && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i][j--]);
                        --count;
                    }
                    ++j;
                    --i;
                }
                case 3 -> {
                    while (i > -1 && !visited[i][j]) {
                        visited[i][j] = true;
                        results.add(matrix[i--][j]);
                        --count;
                    }
                    ++j;
                    ++i;
                }
            }
            turn = (turn + 1) % 4;
        }
        return results;
    }
}
// @lc code=end