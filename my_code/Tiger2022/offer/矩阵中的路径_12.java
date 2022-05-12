package Tiger2022.offer;
/*
剑指 Offer 12. 矩阵中的路径
给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。



例如，在下面的 3×4 的矩阵中包含单词 'ABCCED'（单词中的字母已标出）。





示例 1：

输入：board = [['A','B','C','E'],['S','F','C','S'],['A','D','E','E']], word = 'ABCCED'
输出：true
示例 2：

输入：board = [['a','b'],['c','d']], word = 'abcd'
输出：false


提示：

1 <= board.length <= 200
1 <= board[i].length <= 200
board 和 word 仅由大小写英文字母组成


注意：本题与主站 79 题相同：https://leetcode-cn.com/problems/word-search/

通过次数213,873提交次数471,643
 */

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.concurrent.CyclicBarrier;

public class 矩阵中的路径_12 {

    @Test
    public void test() throws Exception {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'E', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCESEEEFS";
        System.out.println(exist(board, word));
    }

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
                    really(i, j, 0, new HashSet<Integer>());
                    if (ans) {
                        return ans;
                    }
                }
            }
        }
        return ans;
    }

    private void really(int i, int j, int k, HashSet<Integer> visted) {
        if (k == s.length() || ans) {
            ans = true;
            return;
        }
        if (i < 0 || i >= arr.length || j < 0 || j >= arr[0].length || visted.contains(i * arr[0].length + j) || arr[i][j] != s.charAt(k))
            return;
        visted.add(i * arr[0].length + j);
        really(i + 1, j, k + 1, visted);
        really(i - 1, j, k + 1, visted);
        really(i, j + 1, k + 1, visted);
        really(i, j - 1, k + 1, visted);
        visted.remove(i * arr[0].length + j);
    }

    class Solution {
        public boolean exist(char[][] board, String word) {
            char[] words = word.toCharArray();
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (dfs(board, words, i, j, 0)) return true;
                }
            }
            return false;
        }

        boolean dfs(char[][] board, char[] word, int i, int j, int k) {
            if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
            if (k == word.length - 1) return true;
            board[i][j] = '\0';
            boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                    dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i, j - 1, k + 1);
            board[i][j] = word[k];
            return res;
        }
    }

    public int countPrimeSetBits(int left, int right) {
        HashSet<Integer> set = new HashSet<>(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29));
        int ans = 0;
        while (left <= right) {
            if (set.contains(Integer.bitCount(left)))
                ++ans;
            ++left;
        }
        return ans;
    }
}
