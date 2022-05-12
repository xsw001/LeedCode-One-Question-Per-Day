//
// @lc app=leetcode.cn id=100279 lang=java
//
// [100279] ju-zhen-zhong-de-lu-jing-lcof
//
class Solution {
    char[][] arr;
    String s;
    boolean ans;

    public boolean exist(char[][] board, String word) {
        arr = board;
        s = word;
        ans = false;
        char c = word.charAt(0);
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == c) {
                    really(i, j, 0);
                    if (ans) {
                        return ans;
                    }
                }
            }
        }
        return ans;
    }

    private void really(int i, int j, int k) {
        if (k == s.length() || ans) {
            ans = true;
            return;
        }
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || arr[i][j] != s.charAt(k))
            return;
        arr[i][j] = '\0';
        really(i + 1, j, k + 1);
        really(i - 1, j, k + 1);
        really(i, j + 1, k + 1);
        really(i, j - 1, k + 1);
        arr[i][j] = s.charAt(k);
    }
}
// @lc code=end