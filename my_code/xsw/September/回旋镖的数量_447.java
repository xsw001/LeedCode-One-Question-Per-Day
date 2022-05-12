package xsw.September;
/*
447. 回旋镖的数量
给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。
回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。

返回平面上所有回旋镖的数量。


示例 1：

输入：points = [[0,0],[1,0],[2,0]]
输出：2
解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
示例 2：

输入：points = [[1,1],[2,2],[3,3]]
输出：2
示例 3：

输入：points = [[1,1]]
输出：0


提示：

n == points.length
1 <= n <= 500
points[i].length == 2
-104 <= xi, yi <= 104
所有点都 互不相同
通过次数23,138提交次数37,620
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class 回旋镖的数量_447 {

    public static int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int[][] dist = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int distance = distance(points[i], points[j]);
                dist[i][j] = distance;
                dist[j][i] = distance;
            }
        }
        int ans = 0;
        for (int[] doubles : dist) {
            ans += numBoomerangs(doubles);
        }
        return ans;
    }

    private static int numBoomerangs(int[] arr) {
        int asn = 0;
        HashMap<Double, Integer> map = new HashMap<>();
        for (double v : arr) {
            map.put(v, map.getOrDefault(v, 0) + 1);
        }
        for (Double key : map.keySet()) {
            Integer val = map.get(key);
            if (val > 1)
                asn += val * (val - 1);
        }
        return asn;
    }

    private static int distance(int[] a, int[] b) {
        return (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
    }

    public static void main(String[] args) {
        int[][] data = {{0, 0}, {1, 0}, {2, 0}};
        ArrayList<String> list = new ArrayList<>();
        System.out.println(numberOfBoomerangs(data));
        System.out.println(numberOfBoomerangs1(data));
    }

    // 更慢了
    public static int numberOfBoomerangs1(int[][] points) {
        int n = points.length;
        HashMap<Integer, HashMap<Double, Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double distance = distance(points[i], points[j]);
                HashMap<Double, Integer> ii = map.getOrDefault(i, new HashMap<>());
                ii.put(distance, ii.getOrDefault(distance, 0) + 1);
                map.put(i, ii);
                HashMap<Double, Integer> jj = map.getOrDefault(j, new HashMap<>());
                jj.put(distance, jj.getOrDefault(distance, 0) + 1);
                map.put(j, jj);
            }
        }
        int ans = 0;
        for (Integer key : map.keySet()) {
            HashMap<Double, Integer> temp = map.get(key);
            for (Double k : temp.keySet()) {
                Integer val = temp.get(k);
                if (val > 1)
                    ans += val * (val - 1);
            }
        }
        return ans;
    }
}