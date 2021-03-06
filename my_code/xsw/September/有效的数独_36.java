package xsw.September;
/*
36. 有效的数独
请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。

数字 1-9 在每一行只能出现一次。
数字 1-9 在每一列只能出现一次。
数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
数独部分空格内已填入了数字，空白格用 '.' 表示。

注意：

一个有效的数独（部分已被填充）不一定是可解的。
只需要根据以上规则，验证已经填入的数字是否有效即可。


示例 1：


输入：board =
{{"5","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：true
示例 2：

输入：board =
{{"8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]
输出：false
解释：除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。


提示：

board.length == 9
board[i].length == 9
board[i][j] 是一位数字或者 '.'
通过次数178,317提交次数282,953
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class 有效的数独_36 {

//小方块编号和行列的关系为： idx=⌊i/3⌋∗3+⌊j/3⌋。

    public static boolean isValidSudoku(String[][] board) {
        int[] index = {1, 4, 7, 28, 31, 34, 55, 58, 61};
        HashMap<String, HashSet<String>> map = new HashMap<>();
        for (int i = 0; i < 9; i++) {
            map.put("r" + i, new HashSet<String>());
            map.put("c" + i, new HashSet<String>());
        }
        for (int i : index) {
            int row = (i - 1) / 9; //行
            int column = (i - 1) % 9; // 列
            HashSet<String> temp = new HashSet<>();
            for (int a = row; a < row + 3; a++) {
                for (int b = column; b < column + 3; b++) {
                    String c = board[a][b];
                    if (c.equals("."))
                        continue;
                    if (temp.contains(c) || map.get("r" + a).contains(c) || map.get("c" + b).contains(c))
                        return false;
                    temp.add(c);
                    map.get("r" + a).add(c);
                    map.get("c" + b).add(c);
                }
            }
        }
        System.out.println(map);
        return true;
    }

    public static void main(String[] args) {
        String[][] data = {{"5", "3", ".", ".", "7", ".", ".", ".", "."},
                {"6", ".", ".", "1", "9", "5", ".", ".", "."},
                {".", "9", "8", ".", ".", ".", ".", "6", "."},
                {"8", ".", ".", ".", "6", ".", ".", ".", "3"},
                {"4", ".", ".", "8", ".", "3", ".", ".", "1"},
                {"7", ".", ".", ".", "2", ".", ".", ".", "6"},
                {".", "6", ".", ".", ".", ".", "2", "8", "."},
                {".", ".", ".", "4", "1", "9", ".", ".", "5"},
                {".", ".", ".", ".", "8", ".", ".", "7", "9"}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(isValidSudoku(data));
    }
}