package xsw.Nov_Dec;

/*
1232. 缀点成线
在一个 XY 坐标系中有一些点，我们用数组 coordinates 来分别记录它们的坐标，其中 coordinates[i] = [x, y] 表示横坐标为 x、纵坐标为 y 的点。

请你来判断，这些点是否在该坐标系中属于同一条直线上，是则返回 true，否则请返回 false。

示例 1：
输入：coordinates = [[1,2],[2,3],[3,4],[4,5],[5,6],[6,7]]
输出：true
示例 2：
输入：coordinates = [[1,1],[2,2],[3,4],[4,5],[5,6],[7,7]]
输出：false

2 <= coordinates.length <= 1000
coordinates[i].length == 2
-10^4 <= coordinates[i][0], coordinates[i][1] <= 10^4
coordinates 中不含重复的点
*/

import java.util.HashSet;

public class LeedCode1232 {

    public static boolean checkStraightLine(int[][] coordinates) {
        int l = coordinates.length;
        if (l < 2)
            return true;
        int x = coordinates[0][0];
        int y = coordinates[0][1];
        for (int[] coordinate : coordinates) {
            coordinate[0] -= x;
            coordinate[1] -= y;
        }

        int A = coordinates[1][1], B = -coordinates[1][0];
        for (int i = 2; i < l; i++) {
            if (0 != (coordinates[i][0] * A) + (coordinates[i][1] * B))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        HashSet<Integer> set = new HashSet<>();

        System.out.println();
    }

    public boolean checkStraightLine1(int[][] coordinates) {
        int len = coordinates.length;

        for (int i = 1; i < len - 1; ++i) {
            int y1 = coordinates[i][1] - coordinates[i - 1][1];
            int x1 = coordinates[i][0] - coordinates[i - 1][0];
            int y2 = coordinates[i + 1][1] - coordinates[i][1];
            int x2 = coordinates[i + 1][0] - coordinates[i][0];
            if (y1 * x2 != y2 * x1) {
                return false;
            }

        }
        return true;

    }
}
