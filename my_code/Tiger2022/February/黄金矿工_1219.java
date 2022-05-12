package Tiger2022.February;
/*
1219. 黄金矿工
你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。

为了使收益最大化，矿工需要按以下规则来开采黄金：

每当矿工进入一个单元，就会收集该单元格中的所有黄金。
矿工每次可以从当前位置向上下左右四个方向走。
每个单元格只能被开采（进入）一次。
不得开采（进入）黄金数目为 0 的单元格。
矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。


示例 1：

输入：grid = [[0,6,0],[5,8,7],[0,9,0]]
输出：24
解释：
[[0,6,0],
 [5,8,7],
 [0,9,0]]
一种收集最多黄金的路线是：9 -> 8 -> 7。
示例 2：

输入：grid = [[1,0,7],[2,0,6],[3,4,5],[0,3,0],[9,0,20]]
输出：28
解释：
[[1,0,7],
 [2,0,6],
 [3,4,5],
 [0,3,0],
 [9,0,20]]
一种收集最多黄金的路线是：1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7。


提示：

1 <= grid.length, grid[i].length <= 15
0 <= grid[i][j] <= 100
最多 25 个单元格中有黄金。
通过次数16,416提交次数25,135
 */

import org.junit.Test;

import java.util.ArrayList;

public class 黄金矿工_1219 {

    static class Solution {
        static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] grid;
        int m, n;
        int ans = 0;

        public int getMaximumGold(int[][] grid) {
            this.grid = grid;
            this.m = grid.length;
            this.n = grid[0].length;
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] != 0) {
                        dfs(i, j, 0);
                    }
                }
            }
            return ans;
        }

        public void dfs(int x, int y, int gold) {
            gold += grid[x][y];
            ans = Math.max(ans, gold);

            int rec = grid[x][y];
            grid[x][y] = 0;

            for (int d = 0; d < 4; ++d) {
                int nx = x + dirs[d][0];
                int ny = y + dirs[d][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && grid[nx][ny] > 0) {
                    dfs(nx, ny, gold);
                }
            }

            grid[x][y] = rec;
        }
    }
//
//    作者：LeetCode-Solution
//    链接：https://leetcode-cn.com/problems/path-with-maximum-gold/solution/huang-jin-kuang-gong-by-leetcode-solutio-f9gg/
//    来源：力扣（LeetCode）
//    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

    @Test
    public void test() {
        int[] data = {2, 5, 8, 13, 15, 15, 15, 15, 15, 15, 20};
        ArrayList<String> list = new ArrayList<>();
    }

}