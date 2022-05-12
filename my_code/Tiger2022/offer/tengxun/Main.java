package Tiger2022.offer.tengxun;

import java.util.*;

/*
路径搜索
矩形地图上存在空格子和有障碍物的格子, 判断地图上A,B两点直接是否可达.
地图用二维数组表示:  0表示无障碍物, 1表示有障碍物.  移动路径只允许上,下, 左,右, 不允许斜线.
示例1:


A到B不可达, 输出 No.    (绿色0, 灰色1)
A 0 0 0 0
1 1 1 0 0
0 0 0 0 1
0 0 1 1 0
1 0 0 1 B
示例2:
A到B可达, 输出Yes.
A 0 0 0 0
1 1 1 0 0
0 0 0 0 1
0 0 1 1 0
1 0 0 0 B
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int ax = in.nextInt(), ay = in.nextInt();
        int bx = in.nextInt(), by = in.nextInt();
        int m = in.nextInt(), n = in.nextInt();
        int[][] arr = new int[m][n];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                arr[i][j] = in.nextInt();
            }
        }
        if (canArr(arr, ax, ay, bx, by))
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    public static boolean canArr(int[][] arr, int ax, int ay, int bx, int by) {
        int[][] to = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int m = arr.length, n = arr[0].length;
        int target = bx * n + by;
        HashSet<Integer> vis = new HashSet<>();
        LinkedList<Integer> list = new LinkedList<>();
        list.add(ax * n + ay);
        vis.add(ax * n + ay);
        while (!list.isEmpty()) {
            int num = list.pollFirst();
            if (target == num)
                return true;
            int a = num / n, b = num % n;
            for (int[] t : to) {
                int nx = a + t[0], ny = b + t[1];
                if (nx < 0 || nx >= m || ny < 0 || ny >= n)
                    continue;
                int temp = nx * n + ny;
                if (vis.contains(temp) || arr[nx][ny] == 1)
                    continue;
                if (target == temp)
                    return true;
                list.addLast(temp);
                vis.add(temp);
            }
        }
        return false;
    }

}
