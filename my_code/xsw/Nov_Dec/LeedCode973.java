package xsw.Nov_Dec;/*
973. 最接近原点的 K 个点
我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。

（这里，平面上两点之间的距离是欧几里德距离。）

你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。



示例 1：

输入：points = [[1,3],[-2,2]], K = 1
输出：[[-2,2]]
解释：
(1, 3) 和原点之间的距离为 sqrt(10)，
(-2, 2) 和原点之间的距离为 sqrt(8)，
由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
示例 2：

输入：points = [[3,3],[5,-1],[-2,4]], K = 2
输出：[[3,3],[-2,4]]
（答案 [[-2,4],[3,3]] 也会被接受。）
*/

import java.util.Arrays;

public class LeedCode973 {
    //我的想法好傻，既然能给loc[][]排序，为啥不直接给poins排序呢
    public static int[][] kClosest(int[][] points, int K) {
        if (points.length == K)
            return points;
        int[][] loc = new int[points.length][2];
        for (int i = 0; i < points.length; i++) {
            loc[i][0] = points[i][0] * points[i][0] + points[i][1] * points[i][1];
            loc[i][1] = i;
        }
        Arrays.sort(loc, (o1, o2) -> o1[0] - o2[0]);
        int[][] result = new int[K][2];
        for (int i = 0; i < K; i++) {
            result[i] = points[loc[i][1]];
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] points = {{1, 3}, {-2, 2}, {1, 3}, {-2, 2}};
        int K = 1;
        int[][] ints = kClosest1(points, K);
        System.out.println(Arrays.deepToString(ints));
    }

    public static int[][] kClosest1(int[][] points, int K) {
        Arrays.sort(points, (o1, o2) -> (o1[0] * o1[0] + o1[1] * o1[1]) - (o2[0] * o2[0] + o2[1] * o2[1]));
//
//        int[][] result = new int[K][2];
//        for (int i = 0; i < K; i++) {
//            result[i] = points[i];
//        }
//        return result;
        return Arrays.copyOfRange(points, 0, K);
    }

    public int[][] kClosest2(int[][] points, int K) {
        // 使用快速选择（快排变形） 获取 points 数组中距离最小的 K 个点（第 4 个参数是下标，因此是 K - 1）
        return quickSelect(points, 0, points.length - 1, K - 1);
    }

    private int[][] quickSelect(int[][] points, int lo, int hi, int idx) {
        if (lo > hi) {
            return new int[0][0];
        }
        // 快排切分后，j 左边的点距离都小于等于 points[j], j 右边的点的距离都大于等于 points[j]。
        int j = partition(points, lo, hi);
        // 如果 j 正好等于目标idx，说明当前points数组中的[0, idx]就是距离最小的 K 个元素
        if (j == idx) {
            return Arrays.copyOf(points, idx + 1);
        }
        // 否则根据 j 与 idx 的大小关系判断找左段还是右段
        return j < idx ? quickSelect(points, j + 1, hi, idx) : quickSelect(points, lo, j - 1, idx);
    }

    private int partition(int[][] points, int lo, int hi) {
        int[] v = points[lo];
        int dist = v[0] * v[0] + v[1] * v[1];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && points[i][0] * points[i][0] + points[i][1] * points[i][1] < dist) ;
            while (--j >= lo && points[j][0] * points[j][0] + points[j][1] * points[j][1] > dist) ;
            if (i >= j) {
                break;
            }
            int[] tmp = points[i];
            points[i] = points[j];
            points[j] = tmp;
        }
        points[lo] = points[j];
        points[j] = v;
        return j;
    }
}

