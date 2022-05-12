package xsw.Nov_Dec;/*
1030. 距离顺序排列矩阵单元格

给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，
其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
示例 1：
    输入：R = 1, C = 2, r0 = 0, c0 = 0
    输出：[[0,0],[0,1]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
示例 2：

    输入：R = 2, C = 2, r0 = 0, c0 = 1
    输出：[[0,1],[0,0],[1,1],[1,0]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
    [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
示例 3：

    输入：R = 2, C = 3, r0 = 1, c0 = 2
    输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
    解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
    其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeedCode1030复杂的不懂 {
    public static int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        int[][] res = new int[C * R][2];
        int i = 0;
        for (int j = 0; j < R; j++) {
            for (int k = 0; k < C; k++) {
                res[i][0] = j;
                res[i][1] = k;
                ++i;
            }
        }
        Arrays.sort(res, (o1, o2) -> {
            int a = (int) (Math.abs(o1[0] - r0) + Math.abs(o1[1] - c0));
            int b = (int) (Math.abs(o2[0] - r0) + Math.abs(o2[1] - c0));
            return a - b;
        });
        return res;
    }

    public static void main(String[] args) {
        int R = 2, C = 3, r0 = 1, c0 = 2;
        int[][] ints = allCellsDistOrder(R, C, r0, c0);
        System.out.println(Arrays.deepToString(ints));
    }

    //方法二：桶排序
    public static int[][] allCellsDistOrder1(int R, int C, int r0, int c0) {
        int maxDist = Math.max(r0, R - 1 - r0) + Math.max(c0, C - 1 - c0);
        List<List<int[]>> bucket = new ArrayList<List<int[]>>();
        for (int i = 0; i <= maxDist; i++) {
            bucket.add(new ArrayList<int[]>());
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                int d = dist(i, j, r0, c0);
                bucket.get(d).add(new int[]{i, j});
            }
        }
        int[][] ret = new int[R * C][];
        int index = 0;
        for (int i = 0; i <= maxDist; i++) {
            for (int[] it : bucket.get(i)) {
                ret[index++] = it;
            }
        }
        return ret;
    }

    public static int dist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
