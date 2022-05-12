package xsw.December21;
/*
419. 甲板上的战舰
给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。

战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。



示例 1：


输入：board = [["X",".",".","X"],[".",".",".","X"],[".",".",".","X"]]
输出：2
示例 2：

输入：board = [["."]]
输出：0


提示：

m == board.length
n == board[i].length
1 <= m, n <= 200
board[i][j] 是 '.' 或 'X'


进阶：你可以实现一次扫描算法，并只使用 O(1) 额外空间，并且不修改 board 的值来解决这个问题吗？

通过次数16,611提交次数21,680
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class 甲板上的战舰_419 {

    public int countBattleships1(char[][] board) {
        HashSet<Integer> visited = new HashSet<>();
        int ans = 0;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited.contains(i * n + j))
                    continue;
                if (board[i][j] == 'X') {
                    ++ans;
                    if (j < n - 1 && board[i][j + 1] == 'X') {
                        int k = j;
                        while (k < n && board[i][k] == 'X') {
                            visited.add(i * n + k);
                            k++;
                        }
                    } else if (i < m - 1 && board[i + 1][j] == 'X') {
                        int k = i;
                        while (k < m && board[k][j] == 'X') {
                            visited.add(k * n + j);
                            k++;
                        }
                    }
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        String[][] data1 = {{"X", ".", ".", "X"}, {".", ".", ".", "X"}, {".", ".", ".", "X"}};
        char[][] data = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(countBattleships(data));
    }

    public int countBattleships(char[][] board) {
        int asn = 0;
        if(board[0][0] == 'X')
            ++asn;
        int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X') {
                    if (i == 0 && j > 0 && board[i][j - 1] != 'X')
                        ++asn;
                    else if (j == 0 && i > 0 && board[i - 1][j] != 'X')
                        ++asn;
                    else if (i > 0 && j > 0 && board[i - 1][j] != 'X' && board[i][j - 1] != 'X')
                        ++asn;
                    /*
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    asn++;
                    */
                }
            }
        }
        return asn;
    }
}