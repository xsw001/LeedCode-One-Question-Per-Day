//
// @lc app=leetcode.cn id=419 lang=java
//
// [419] battleships-in-a-board
//
class Solution {
    public int countBattleships(char[][] board) {
        int asn = 0;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    asn++;
                }
            }
        }
        return asn;
    }
}
// @lc code=end