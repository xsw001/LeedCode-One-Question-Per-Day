package xsw.April.个人赛;
/*
4. 变换的迷宫
通过的用户数75
尝试过的用户数337
用户总通过次数77
用户总提交次数1299
题目难度Hard
某解密游戏中，有一个 N*M 的迷宫，迷宫地形会随时间变化而改变，迷宫出口一直位于 (n-1,m-1) 位置。迷宫变化规律记录于 maze 中，maze[i] 表示 i 时刻迷宫的地形状态，"." 表示可通行空地，"#" 表示陷阱。

地形图初始状态记作 maze[0]，此时小力位于起点 (0,0)。此后每一时刻可选择往上、下、左、右其一方向走一步，或者停留在原地。

小力背包有以下两个魔法卷轴（卷轴使用一次后消失）：

临时消除术：将指定位置在下一个时刻变为空地；
永久消除术：将指定位置永久变为空地。
请判断在迷宫变化结束前（含最后时刻），小力能否在不经过任意陷阱的情况下到达迷宫出口呢？

注意： 输入数据保证起点和终点在所有时刻均为空地。

示例 1：

输入：maze = [[".#.","#.."],["...",".#."],[".##",".#."],["..#",".#."]]

输出：true

解释：
maze.gif

示例 2：

输入：maze = [[".#.","..."],["...","..."]]

输出：false

解释：由于时间不够，小力无法到达终点逃出迷宫。

示例 3：

输入：maze = [["...","...","..."],[".##","###","##."],[".##","###","##."],[".##","###","##."],[".##","###","##."],[".##","###","##."],[".##","###","##."]]

输出：false

解释：由于道路不通，小力无法到达终点逃出迷宫。

提示：

1 <= maze.length <= 100
1 <= maze[i].length, maze[i][j].length <= 50
maze[i][j] 仅包含 "."、"#"
 */

import java.util.List;

public class hard_4 {

    public boolean escapeMaze(List<List<String>> maze) {
        return true;
    }

    public static void main(String[] args) {
        int[] data = {2, 2, 1, 9};
    }

    static class Solution {
        int len, row, col;
        List<List<String>> m;
        int[][] fs = new int[][]{{-1, 0}, {1, 0}, {0, 1}, {0, -1}, {0, 0}};
        int xP, yP;
        Boolean[][][][][] memo;

        public boolean escapeMaze(List<List<String>> maze) {
            len = maze.size();
            row = maze.get(0).size();
            col = maze.get(0).get(0).length();
            m = maze;
            memo = new Boolean[len][row][col][2][2];
            return dfs(0, 0, 0, 0, 0);
        }

        private boolean dfs(int u, int i, int j, int use1, int use2) {
            if (i == row - 1 && j == col - 1) {
                return true;
            }
            if (u == len - 1) {
                return false;
            }
            if (memo[u][i][j][use1][use2] != null) {
                return memo[u][i][j][use1][use2];
            }
            for (int[] f : fs) {
                int x = i + f[0];
                int y = j + f[1];
                if (x >= 0 && x < row && y >= 0 && y < col) {
                    if (m.get(u + 1).get(x).charAt(y) == '.' || x == xP && y == yP) {
                        if (dfs(u + 1, x, y, use1, use2)) {
                            return memo[u][i][j][use1][use2] = true;
                        }
                    } else {
                        if (use1 == 0) {
                            if (dfs(u + 1, x, y, 1, use2)) {
                                return memo[u][i][j][use1][use2] = true;
                            }
                        }
                        if (use2 == 0) {
                            xP = x;
                            yP = y;
                            if (dfs(u + 1, x, y, use1, 1)) {
                                return memo[u][i][j][use1][use2] = true;
                            }
                        }
                    }
                }
            }
            return memo[u][i][j][use1][use2] = false;
        }
    }
}